package com.example.demo.domain.user.service.impl;

import java.util.List;

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

	/** ユーザー取得 */
	@Override
	public List<MUser> getUsers() {
		return mapper.findMany();
	}

	/** ユーザー取得 */
	@Override
	public MUser getUserOne(String userId) {
		return mapper.findOne(userId);
	}

	@Override
	public void updateUserOne(String userId, String password, String userName) {
		mapper.updateOne(userId, password, userName);
	}

	@Override
	public void deleteUserOne(String userId) {
		var count = mapper.deleteOne(userId);
	}

}
