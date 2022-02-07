package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserDetailForm;

@Controller
@RequestMapping("/user")
public class UserDetailController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/** ユーザー詳細画面を表示 */
	@GetMapping("/detail/{userId:.+}")
	public String getUserOne(Model model, @PathVariable("userId") String userId) {
		
		// ユーザーを1件取得
		var user = userService.getUserOne(userId);
		user.setPassword(null);
		
		// MUserをFormに変換
		var form = modelMapper.map(user, UserDetailForm.class);
		
		model.addAttribute("userDetailForm", form);
		
		// ユーザー詳細画面を表示
		return "user/detail";
	}
}
