package com.example.demo.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.repositry.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/** ユーザー登録 */
	@Override
	public void signup(MUser user) {
		// SignupFormが持っていないプロパティをセットする。
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		// パスワード暗号化
		String rawPasswd = user.getPassword();
		user.setPassword(encoder.encode(rawPasswd));
		
		//TODO トランザクション制御は？
		mapper.insertOne(user);
	}

	/** ユーザー取得 */
	@Override
	public List<MUser> getUsers(MUser user) {
		return mapper.findMany(user);
	}

	/** ユーザー取得 */
	@Override
	public MUser getUserOne(String userId) {
		return mapper.findOne(userId);
	}

	@Override
	public void updateUserOne(String userId, String password, String userName) {
		mapper.updateOne(userId, encoder.encode(password), userName);
	}

	@Override
	public void deleteUserOne(String userId) {
		var count = mapper.deleteOne(userId);
	}

	@Override
	public MUser getLoginUser(String userId) {
		return mapper.findLoginUser(userId);
	}

	
}
