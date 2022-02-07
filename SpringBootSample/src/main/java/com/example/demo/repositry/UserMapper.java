package com.example.demo.repositry;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.user.model.MUser;

/** ORMのDAOクラスのようなもの */
@Mapper
public interface UserMapper {

	/** ユーザー登録 */
	public int insertOne(MUser user);
	
	/** ユーザー取得 */
	public List<MUser> findMany();
}
