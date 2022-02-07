package com.example.demo.controller;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.service.UserApplicationService;
import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

	@Autowired
	private UserApplicationService userApplicationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/** ユーザー登録画面を表示 */
	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		
		var genderMap = userApplicationService.getGenderMap(locale);
		
		// @ModelAttributeを指定すると、自動でモデルに登録してくれる
		// 以下のイメージ
		// model.addAttribute("signupForm", form);
		model.addAttribute("genderMap", genderMap);
		
		return "user/signup";
	}
	
	/** ユーザー登録処理 */
	@PostMapping("/signup")
	public String postSignup(Model model, Locale locale, @ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult) {
		
		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			// NG:ユーザー登録画面に戻ります
			return getSignup(model, locale, form);
		}
		
		log.info(form.toString());
		
		// form を MUserクラスに変換
		// SignupFormと同一プロパティが、MUserにセットされる
		// Service層に、フォームクラスを直接渡さない
		// 理由：1.画面変更（＝フォームクラス変更）がサービス層に影響を与えないようにするため。
		// 理由：2.他の画面からもサービスが利用可能にするため。
		MUser user = modelMapper.map(form, MUser.class);
		
		// ユーザー登録
		userService.signup(user);
		
		// ログイン画面にリダイレクト
		return "redirect:/login";
	}
}
