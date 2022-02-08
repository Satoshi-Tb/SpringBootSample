package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.UserDetailForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserDetailController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// UserIdがメールアドレス形式のため、正規表現を指定。パラメータ名:正規表現
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
	
	@PostMapping(value = "/detail", params = "update")
	public String updateUser(Model model, @Validated UserDetailForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "user/detail";
		}
		
		userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());


		return "redirect:/user/list";
	}
	
	@PostMapping(value = "/detail", params = "delete")
	public String deleteUser(Model model, UserDetailForm form) {
		
		userService.deleteUserOne(form.getUserId());
		
		return "redirect:/user/list";
	}
}
