package com.ispan.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ispan.demo.config.Result;
import com.ispan.demo.model.Article;
import com.ispan.demo.model.Follower;
import com.ispan.demo.model.FoodType;
import com.ispan.demo.model.RestaurantList;
import com.ispan.demo.model.User;
import com.ispan.demo.model.UserFavorite;
import com.ispan.demo.service.FollowSrevice;
import com.ispan.demo.service.UserFavoriteService;
import com.ispan.demo.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RestController
@CrossOrigin()
@RequestMapping(path = {"/"})
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserFavoriteService userFavoriteService;
	
	@Autowired
	private FollowSrevice followSrevice;

	// 初始註冊
	@PostMapping("user/insert")
	@ResponseBody
	public Result<User> insertUser(@RequestBody User user) {
		user.onCreate();
		user.onUpdate();

		User insertUser = userService.insertUser(user);
		if (insertUser == null) {
			return Result.error("使用者名字重複");
		}
		return Result.success(insertUser);
	}

	// 登入
	@PostMapping("user/login")
	public Result<String> loginUser(
//			@RequestParam String loginName, @RequestParam String loginPwd,
			@RequestBody  User login
			,HttpSession httpSession
			,HttpServletResponse response) {
		
		User user = userService.checkLogin(login.getUserName(), login.getPassWord());	
		
	    Cookie cookie = new Cookie("userCookie", login.getUserName());
	    cookie.setMaxAge(3600); // 设置 Cookie 过期时间（单位：秒）
	    cookie.setHttpOnly(true); // 设置 HttpOnly 属性，防止 JavaScript 访问 Cookie
	    cookie.setSecure(true); // 设置 Secure 属性，仅在 HTTPS 连接中传输 Cookie
	    cookie.setPath("/"); // 设置 Cookie 的作用路径，根路径下的所有请求都会带上该 Cookie
	    
	    if (user != null) {
			user.setPassWord(null);
			httpSession.setAttribute("user", user);
			response.addCookie(cookie);
			return Result.success("login success");
		}
	    return Result.error("no Login");
//
//		if (user != null) {
//			user.setPassWord(null);
//			httpSession.setAttribute("user", user);
//			System.out.println("login user:"+user);
//			return Result.success("login success");
//		}
	    
	    
	}

	// 登出
	@PostMapping("user/logout")
	public Result<String> logoutUser(HttpSession httpSession) {
		httpSession.invalidate();

		return Result.success("logout ok");

	}

	// 基本資料
//	@GetMapping("user/{id}")
//	public Result<User> userDetial(@PathVariable Integer id) {
//		User user = userService.findUserById(id);
//
//		return Result.success(user);
//	}
	@GetMapping("user/detial")
	public Result<User> userDetial(HttpSession session,@CookieValue("JSESSIONID") String JSESSIONID) {
		System.out.println(JSESSIONID);
		User user = (User) session.getAttribute("user");
		    System.out.println("User: " + user);
		if(user!=null) { 
	
		
			return Result.success(user);
			
		}
		return Result.error("User details not found in session");
	}

	// 更新
//	@Transactional
//	@PutMapping("user/modify/{id}")
//	public Result<String> modifyUser(@PathVariable Integer id, @RequestBody User user) {
//
//		User updateUserById = userService.updateUserById(user, id);
//
//		if (updateUserById == null) {
//			return Result.error("更新失敗");
//		}
//		return Result.success("更新成功");
//
//	}
	
	
	@PutMapping("user/modify")
	public Result<String> modifyUser(HttpSession session,@RequestBody User newUser) {
		System.out.println(newUser);
		User user = (User) session.getAttribute("user");
		newUser.setId(user.getId());
		User updateUserById = userService.updateUserById(newUser);

		if (updateUserById == null) {
		
			return Result.error("更新失敗");
		}
		session.setAttribute("user", newUser);
		return Result.success("更新成功");

	}
	

	// 刪除使用者
	@DeleteMapping("user/delete")
	public Result<String> deleteUserById(@RequestParam Integer id) {
		User user = userService.findUserById(id);
		if (user == null) {
			return Result.error("沒有資料");

		}
		userService.deleteUserById(id);
		return Result.success("刪除成功");
	}

	// 使用者禁用
	@PostMapping("user/ban")
	public Result<String> banUserById(@RequestParam Integer id) {
		User user = userService.findUserById(id);
		// 假設ViolateCount達5次就禁用
		if (user.getViolateCount() >= 5) {

			if (user.getStatus()) {
				return Result.error("已被禁用");
			}
			user.setStatus(true);
			userService.saveUser(user);
			return Result.success("禁用成功");
		} else {
			return Result.error("未達禁用標準");
		}

	}

	// 最愛列表
	@GetMapping("user/favorite")
	public Result<List<UserFavorite>> showUserFavorite(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<UserFavorite> favorite = userFavoriteService.findByUserId(user.getId());
		if (favorite == null) {
			return Result.error("沒有favorite");
		}

		return Result.success(favorite);
	}

	// 以articleId查詢最愛文章
	@GetMapping("user/favorite/article")
	public Result<List<UserFavorite>> showUserFavoriteArticle(HttpSession session,@RequestParam Integer articleId) {
		User user = (User) session.getAttribute("user");
		List<UserFavorite> favorite = userFavoriteService.findByUserId(user.getId());
		if (favorite.isEmpty()) {
			return Result.error("沒有 favorite article");
		}
		boolean articleFound = favorite.stream()
				.anyMatch(fav -> fav.getArticleId() != null && fav.getArticleId().equals(articleId));
		if (!articleFound) {
			return Result.error("指定的文章 ID 不存在於使用者的收藏中");
		}
		List<UserFavorite> findByArticleId = userFavoriteService.findByArticleId(articleId);
		return Result.success(findByArticleId);
	}

	// 查詢所有最愛文章
//	@GetMapping("user/favorite/articles")
//	public Result<List<Integer>> showUserFavoriteArticles(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		List<Integer> favoriteArticleIds = userFavoriteService.findArticleIdsByUserId(user.getId());
//
//		if (favoriteArticleIds.isEmpty()) {
//			return Result.error("没有 favorite articles");
//		}
//
//		return Result.success(favoriteArticleIds);
//	}
	@GetMapping("user/favorite/articles")
	public Result<List<Article>> findFavoriteArticleByUserId(HttpSession session){
		User user = (User) session.getAttribute("user");
		List<Article> favoriteArticleIds = userService.findFavoriteArticleByUserId(user.getId());
		return Result.success(favoriteArticleIds);
	}

	// 以restaurantId查詢最愛餐廳
	@GetMapping("user/favorite/restaurant")
	public Result<List<UserFavorite>> showUserFavoriteRestaurant(@RequestParam Integer userId,
			@RequestParam Integer restaurantId) {
		List<UserFavorite> favorite = userFavoriteService.findByUserId(userId);
		if (favorite.isEmpty()) {
			return Result.error("沒有 favorite restaurant");
		}
		boolean restaurantFound = favorite.stream()
				.anyMatch(fav -> fav.getRestaurantId() != null && fav.getRestaurantId().equals(restaurantId));
		if (!restaurantFound) {
			return Result.error("指定的餐廳 ID 不存在於使用者的收藏中");
		}
		List<UserFavorite> findByRestaurantId = userFavoriteService.findByRestaurantId(restaurantId);
		return Result.success(findByRestaurantId);
	}

	// 查詢所有最愛餐廳
//	@GetMapping("user/favorite/restaurants")
//	public Result<List<Integer>> showUserFavoriteRestaurants(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		List<Integer> favoriteRestaurants = userFavoriteService.findRestaurantIdsByUserId(user.getId());
//
//		if (favoriteRestaurants.isEmpty()) {
//			return Result.error("没有 favorite restaurants");
//		}
//		return Result.success(favoriteRestaurants);
//	}
	
	@GetMapping("user/favorite/restaurants")
	public Result<List<RestaurantList>> findFavoriteRestaurantListByUserId(HttpSession session){
		User user = (User) session.getAttribute("user");
	List<RestaurantList> findFavoriteRestaurantListByUserId = userService.findFavoriteRestaurantListByUserId(user.getId());
		return Result.success(findFavoriteRestaurantListByUserId);
	}

	// 以foodId查詢最愛食物
	@GetMapping("user/favorite/food")
	public Result<List<UserFavorite>> showUserFavoriteFood(@RequestParam Integer userId, @RequestParam Integer foodId) {
		List<UserFavorite> favorite = userFavoriteService.findByUserId(userId);
		if (favorite.isEmpty()) {
			return Result.error("沒有 favorite food");
		}
		boolean restaurantFound = favorite.stream()
				.anyMatch(fav -> fav.getFoodId() != null && fav.getFoodId().equals(foodId));
		if (!restaurantFound) {
			return Result.error("指定的餐廳 ID 不存在於使用者的收藏中");
		}
		List<UserFavorite> findByFoodId = userFavoriteService.findByFoodId(foodId);
		return Result.success(findByFoodId);
	}

	// 查詢所有最愛食物
//	@GetMapping("user/favorite/foods")
//	public Result<List<Integer>> showUserFavoriteFoods(HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		List<Integer> favoriteFoods = userFavoriteService.findFoodIdsByUserId(user.getId());
//
//		if (favoriteFoods.isEmpty()) {
//			return Result.error("没有 favorite foods");
//		}
//		return Result.success(favoriteFoods);
//	}
//	
	@GetMapping("user/favorite/foods")
	public Result<List<FoodType>> findFavoriteFoodTypeByUserId(HttpSession session){
		User user = (User) session.getAttribute("user");
	List<FoodType> findFavoriteFoodTypeByUserId = userService.findFavoriteFoodTypeByUserId(user.getId());
		return Result.success(findFavoriteFoodTypeByUserId);
	}
 
	// 新增最愛的文章
	@PostMapping("user/favorite/insert/article")
	public Result<UserFavorite> insertUserFavoriteArticle(@RequestBody UserFavorite userFavorite) {
		UserFavorite insertUserFavorite = userFavoriteService.insertUserFavorite(userFavorite);
		if (insertUserFavorite == null) {
			return Result.error("已加入最愛的文章");
		}
		return Result.success(insertUserFavorite);
	}

	// 新增最愛的餐廳
	@PostMapping("user/favorite/insert/restaurant")
	public Result<UserFavorite> insertUserFavoriteRestaurant(@RequestBody UserFavorite userFavorite) {
		UserFavorite insertUserFavorite = userFavoriteService.insertUserFavorite(userFavorite);
		if (insertUserFavorite == null) {
			return Result.error("已加入最愛的餐廳");
		}
		return Result.success(insertUserFavorite);
	}

	// 新增最愛的食物
	@PostMapping("user/favorite/insert/food")
	public Result<UserFavorite> insertUserFavoriteFood(@RequestBody UserFavorite userFavorite) {
		UserFavorite insertUserFavorite = userFavoriteService.insertUserFavorite(userFavorite);
		if (insertUserFavorite == null) {
			return Result.error("已加入最愛的食物");
		}
		return Result.success(insertUserFavorite);
	}

	// 刪除最愛的文章
	@Transactional
	@DeleteMapping("user/favorite/delete/article")
	public Result<String> deleteUserFavoriteArticle(@RequestParam Integer userId, @RequestParam Integer articleId) {
		List<UserFavorite> findByUserId = userFavoriteService.findByUserId(userId);
		if (findByUserId.isEmpty()) {
			return Result.error("沒有資料");
		}
		boolean articleFound = findByUserId.stream().filter(fav -> fav.getArticleId() != null)
				.anyMatch(fav -> fav.getArticleId().equals(articleId));
		if (!articleFound) {
			return Result.error("指定的文章 ID 不存在於使用者的收藏中");
		}
		userFavoriteService.deleteUserByArticleId(articleId);
		return Result.success("刪除成功");
	}

	// 刪除最愛的餐廳
	@Transactional
	@DeleteMapping("user/favorite/delete/restaurant")
	public Result<String> deleteUserFavoriteRestaurant(@RequestParam Integer userId,
			@RequestParam Integer restaurantId) {
		List<UserFavorite> findByUserId = userFavoriteService.findByUserId(userId);
		if (findByUserId.isEmpty()) {
			return Result.error("沒有資料");
		}
		boolean restaurantFound = findByUserId.stream().filter(fav -> fav.getRestaurantId() != null)
				.anyMatch(fav -> fav.getRestaurantId().equals(restaurantId));
		if (!restaurantFound) {
			return Result.error("指定的餐廳 ID 不存在於使用者的收藏中");
		}
		userFavoriteService.deleteUserByRestaurantId(restaurantId);
		return Result.success("刪除成功");
	}

	// 刪除最愛的食物
	@Transactional
	@DeleteMapping("user/favorite/delete/food")
	public Result<String> deleteUserFavorite(@RequestParam Integer userId, @RequestParam Integer foodId) {
		List<UserFavorite> findByUserId = userFavoriteService.findByUserId(userId);
		if (findByUserId.isEmpty()) {
			return Result.error("沒有資料");
		}
		boolean foodFound = findByUserId.stream().filter(fav -> fav.getFoodId() != null)
				.anyMatch(fav -> fav.getFoodId().equals(foodId));
		if (!foodFound) {
			return Result.error("指定的食物 ID 不存在於使用者的收藏中");
		}
		userFavoriteService.deleteUserByFoodId(foodId);
		return Result.success("刪除成功");
	}
	
	//追蹤列表
	@GetMapping("user/follow")
	public Result<List<Follower>> findFollow(HttpSession session){
		User user = (User)session.getAttribute("user");
		List<Follower> list = followSrevice.findByUserId(user.getId());
		if(list==null) {
			return Result.error("沒有追蹤");
		}
		return Result.success(list);
	}
	
	//照片上傳
	@PostMapping("user/photo/{id}")
	public String uploadUserPhoto(@PathVariable Integer id,@RequestParam("file") MultipartFile file){
		 Result<String> result = userService.uploadPhoto(id, file);
		 if(result.getCode()==0) {
			 return result.getMsg();
		 }

	        return result.getData();
	}
	
//    @GetMapping("user/{userId}")
//    public Result<User> getUserWithFavoriteArticles(@PathVariable Integer userId) {
//        User user = userService.getUserWithFavoriteArticles(userId);
//        if (user != null) {
//            return Result.success(user);
//        } else {
//            return Result.error("X");
//        }
//    }
	
}
