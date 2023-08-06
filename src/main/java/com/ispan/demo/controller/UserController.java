package com.ispan.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.demo.config.Result;
import com.ispan.demo.model.User;
import com.ispan.demo.model.UserFavorite;
import com.ispan.demo.service.UserFavoriteSrevice;
import com.ispan.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFavoriteSrevice userFavoriteSrevice; 

	//初始註冊
	@PostMapping("/user/insert")
	@ResponseBody
	public Result<User> insertUser(
//			@RequestParam("UserName")String userName,@RequestParam("UserPwd")String pwd
//			,@RequestParam("identity")Integer identity,@RequestParam("modifiedBy")String modifiedBy
//			,@RequestParam("status")Boolean status,@RequestParam("violateCount")Integer violateCount,Model model
			@RequestBody User user
			) {
//		user.setUserName(userName);
//		user.setPassWord(pwd);
//		user.setIdentity(identity);
//		user.setModifiedBy(modifiedBy);
//		user.setStatus(status);
//		user.setViolateCount(violateCount);
		user.onCreate();
		user.onUpdate();
	
		User insertUser = userService.insertUser(user);
		if (insertUser==null) {
			return Result.error("使用者名字重複");
		}
		return Result.success(insertUser);
	}
	
	//登入
	@PostMapping("user/login")
	public Result<String> loginUser(@RequestParam String loginName,@RequestParam String loginPwd,HttpSession httpSession){
		User user = userService.checkLogin(loginName, loginPwd);
		
		if(user!=null) {
			httpSession.setAttribute("loginName", user);
			return Result.success("ok");
		}
		return Result.error("no");
	}
	
	//更新
	@Transactional
	@PutMapping("user/modify/{id}")
	public Result<String> modifyUser(//@RequestParam Integer id,@RequestParam String newName
//			,@RequestParam String nickName,@RequestParam byte[] photo,@RequestParam Integer gender,@RequestParam String email,@RequestParam LocalDate birthday
			@PathVariable Integer id, @RequestBody User user	
			){
		
		User updateUserById = userService.updateUserById(user,id);
		
		if(updateUserById==null) {
			return Result.error("更新失敗") ;
		}
		return Result.success("更新成功");
		
	}
	

	
	//刪除使用者
	@DeleteMapping("user/delete")
	public Result<String> deleteUserById(@RequestParam Integer id){
		User user = userService.findUserById(id);
		if(user==null) {
			return Result.error("沒有資料");
			
		}
		userService.deleteUserById(id);
		return Result.success("刪除成功");
	}
	
	@PostMapping("user/ban")
	public Result<String> banUserById(@RequestParam Integer id){
		User user = userService.findUserById(id);
		//假設ViolateCount達10次就禁用
		if(user.getViolateCount()>=10) {
			
		if(user.getStatus()) {
			return Result.error("已被禁用");
		}
		user.setStatus(true);
        userService.saveUser(user);
        return Result.success("禁用成功");
    } else {
        return Result.error("未達禁用標準");
    }
		
	}
	
//	public Result<UserFavorite> insertUserFavorite(){
//		
//	}
	
	//使用者最愛
	@PostMapping("user/userFavorite")
	public Result<UserFavorite> showUserFavorite(@RequestParam Integer favoriteId){
		UserFavorite favorite = userFavoriteSrevice.findById(favoriteId);
		if(favorite==null) {
			return Result.error("沒有最愛");
		}
	
		return Result.success(favorite);
	}
	
}
