package com.ispan.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ispan.demo.config.NullCheckUtils;
import com.ispan.demo.config.Result;
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
	
	//透過id找user
	public User findUserById(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	//更新
	public User updateUserById(User user) {
		Optional<User> option = userRepository.findById(user.getId());
		if (option.isEmpty()) {
			
			return null;
		}
		 User getUser = option.get();
		 
		 
		 
//		NullCheckUtils.copyPropertiesNonNull(user, getUser);
		 
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
	
}
