package com.sj.spring.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sj.spring.beans.UserBean;

public interface UserMapper {

	@Select("SELECT USER_NAME " + 
			"FROM USER_TABLE " + 
			"WHERE USER_ID = #{user_id}")
	String checkUserIdExist(String user_id);
	
	@Insert("INSERT INTO USER_TABLE " + 
			"(USER_IDX, USER_NAME, USER_ID, USER_PW) " + 
			"VALUES(USER_SEQ.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUserInfo(UserBean joinUserBean);
	
	@Select("SELECT USER_IDX, USER_NAME " + 
			"FROM USER_TABLE " + 
			"WHERE USER_ID = #{user_id} AND USER_PW = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	@Select("SELECT USER_NAME, USER_ID " + 
			"FROM USER_TABLE " + 
			"WHERE USER_IDX = #{user_idx}")
	UserBean getModifyUserInfo(int user_idx);
	
	@Update("UPDATE USER_TABLE " + 
			"SET USER_PW = #{user_pw} " + 
			"WHERE USER_IDX = #{user_idx}")
	void modifyUserInfo(UserBean modifyUserBean);
	
}
