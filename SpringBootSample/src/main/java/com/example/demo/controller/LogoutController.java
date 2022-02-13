package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LogoutController {

	// Spring Securityによってログアウト処理が実施されるため、本コントローラは不要になる。
//	@PostMapping("/logout")
//	public String postLogout() {
//		return "redirect:/login";
//	}
}
