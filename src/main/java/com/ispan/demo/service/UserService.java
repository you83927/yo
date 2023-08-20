package com.ispan.demo.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ispan.demo.config.NullCheckUtils;
import com.ispan.demo.model.Article;
import com.ispan.demo.model.FoodType;
import com.ispan.demo.model.RestaurantList;
import com.ispan.demo.model.User;
import com.ispan.demo.model.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	// 新增
	public User insertUser(User user) {
		boolean existsByUserName = userRepository.existsByUserName(user.getUserName());
		if(existsByUserName) {
			return null;
		}
		return userRepository.save(user);
			
	}
	
	//找所有user
		public List<User> findAllUser() {
			List<User> list = userRepository.findAll();
			if (list.isEmpty()) {
				return null;
			}
			return list;
		}
	
	//透過id找user
	public User findUserById(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	//透過userName找user
	public List<User> findUserByUserName(String userName) {
		 List<User> list = userRepository.findByUserNames(userName);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	
	//透過userName找user 分頁查詢
	public Page<User> findUserByUserNamePage(String userName,int pageNumber, int pageSize) {
		 PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("userName").ascending());
		Page<User> userNamesPage = userRepository.findByUserNamesPage(userName,pageRequest);

		return userNamesPage;
	}
	
	//在follower頁面透過userName找user
	public List<User> findFollowingUsersByUserName(String userName) {
		 List<User> list = userRepository.findFollowingUsersByUserName(userName);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	
	public User findFollowingUsersByFollowing(Integer following) {
		User user = userRepository.findFollowingUsersByFollowing(following);
		if (user==null) {
			return null;
		}
		return user;
	}
	

	//更新
	public User updateUserById(User user) {
		Optional<User> option = userRepository.findById(user.getId());
		
		if (option.isEmpty()) {
			
			return null;
		}
		 User getUser = option.get();
		 
		 
		NullCheckUtils.copyPropertiesNonNull(user, getUser);
		System.out.println(getUser.getPhoto().length);
		if(getUser.getPhoto() != null && getUser.getPhoto().length==0) {
			getUser.setPhoto(null);
		}
		
		if(getUser.getNickName() != null && getUser.getNickName()=="") {
			getUser.setNickName(null);
		}
		
		if(getUser.getEmail() != null && getUser.getEmail()=="") {
			getUser.setEmail(null);
		}
		
		if(getUser.getGender() != null && getUser.getGender()==0) {
			getUser.setGender(null);
		}
		if(getUser.getBirthday() != null && getUser.getBirthday().isEqual(LocalDate.of(0001, 01, 01))) {
			getUser.setBirthday(null);
		}
		
		 userRepository.save(getUser);
		return getUser;
	}

	//刪除
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
		
	}

	//檢查登入
	public User checkLogin(String userName, String pwd) {
		User user = userRepository.findByUserName(userName);

		if (user!=null &&user.getUserName().equals(userName) && user.getPassWord().equals(pwd)) {
			return user;
		}
		return null;
	}
	
	//檢查登出
	public User checkLogout(String userName, String pwd) {
		User user = userRepository.findByUserName(userName);

		if (user!=null &&user.getUserName().equals(userName) && user.getPassWord().equals(pwd)) {
			return null;
		}
		return user;
	}
	
	
	
	public User uploadPhoto(Integer userId, MultipartFile file) {
        if (file.isEmpty()) {
        	return null;
//            return Result.error("上傳的照片為空");
        }

        try {
            // 根據 userId 查找使用者
            User user = userRepository.findById(userId).orElse(null);
//            if (user == null) {
//                return "找不到指定的使用者";
//            }

            // 檔案名稱
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // 檔案類型
            String fileType = file.getContentType();

            // 檔案大小限制（根據需求自行調整）
//            if (file.getSize() > 5 * 1024 * 1024) { // 5 MB
//                return "檔案大小超過限制";
//            }

            // 進行實際的儲存操作
            user.setPhoto(file.getBytes()); // 儲存照片內容至使用者物件
            

            return userRepository.save(user);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
//            return "照片上傳失敗：" + e.getMessage();
        }
	}
	
//	public User getUserWithFavoriteArticles(Integer userId) {
//        return userRepository.findUserWithFavoriteArticles(userId);
//    }
	
	public List<Article> findFavoriteArticleByUserId(Integer userId){
		List<Article> list = userRepository.findFavoriteArticleByUserId(userId);
		return list;
	}
	
	
	public List<RestaurantList> findFavoriteRestaurantListByUserId(Integer userId){
		List<RestaurantList> list = userRepository.findFavoriteRestaurantListByUserId(userId);
		return list;
	}
	
	public List<FoodType> findFavoriteFoodTypeByUserId(Integer userId){
		List<FoodType> list = userRepository.findFavoriteFoodTypeByUserId(userId);
		return list;
	}
	
	public Page<User> findFollowingUsersByUserId(Integer userId,int pageNumber, int pageSize){
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("userName").ascending());
		Page<User> list = userRepository.findFollowingUsersByUserId(userId,pageRequest);
		for(User user:list) {
			System.out.println(user.getPhoto());
		}
		return list;
	}
	
	   public Page<User> getUsersByPage(int pageNumber, int pageSize) {
	        // 創建分頁請求，指定頁數和每頁顯示的資料數量
	        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("userName").ascending());
	      
	        // 進行分頁查詢
	        return userRepository.findAll(pageRequest);
	    }
	
}
