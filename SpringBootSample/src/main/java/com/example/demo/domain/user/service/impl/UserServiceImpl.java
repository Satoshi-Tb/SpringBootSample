package com.example.demo.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.repositry.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	/** ユーザー登録 */
	@Override
	public void signup(MUser user) {
		// SignupFormが持っていないプロパティをセットする。
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		//TODO トランザクション制御は？
		mapper.insertOne(user);
	}

}
