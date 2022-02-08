package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserListForm;

@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/** ユーザー一覧画面を表示 */
	@RequestMapping(path = "/list", method = { GET, POST })
	public String getUserList(Model model, @ModelAttribute UserListForm form) {
		var user = modelMapper.map(form, MUser.class);

		var userList = userService.getUsers(user);
		
		model.addAttribute("userList", userList);
		return "user/list";
	}
}
