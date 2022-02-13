package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserDetailForm;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	
	@PutMapping("/update")  // Putメソッドにマップ
	public int updateUser(UserDetailForm form) {
		// ユーザーを更新
		userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
		
		// なぜ0を返す？
		return 0;
	}
	
	
	@DeleteMapping("/delete")  // Deleteメソッドにマップ
	public int deleteUse(UserDetailForm form) {
		userService.deleteUserOne(form.getUserId());
		
		return 0;
	}
}
