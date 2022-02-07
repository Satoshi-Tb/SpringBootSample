package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;
	
	/** ユーザー一覧画面を表示 */
	@GetMapping("/list")
	public String getUserList(Model model) {
		var userList = userService.getUsers();
		
		model.addAttribute("userList", userList);
		return "user/list";
	}
}
