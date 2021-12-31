package com.chinasofti.postbar.mapper;


import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.UserDto;

public interface PbUserMapper{



	@Insert("INSERT INTO pb_user (userUUID,userName,password,loginTime,admin) VALUES (#{userUUID},#{userName},#{password},#{loginTime},#{admin})")
	int  insertUser(UserDto userDto);


	@Select("SELECT count(*) FROM pb_user WHERE userName=#{userName}")
	int selectUserNumByUserName(@Param("userName") String userName);
	
	
	@Select("SELECT * FROM pb_user WHERE userName=#{userName} and password=#{password}")
	UserDto selectUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
	
	@Update("UPDATE pb_user SET loginTime=#{loginTime} WHERE userName=#{userName}")
	void updateLoginTimeByUserName(@Param("userName") String userName, @Param("loginTime") Date loginTime);
	
	@Select("SELECT * FROM pb_user WHERE userUUID=#{userUUID}")
	UserDto selectUserByUserUUID(@Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_user SET userName=#{userName} WHERE userUUID=#{userUUID}")
	void updateUserNameByUserUUID(@Param("userName") String userName, @Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_user SET password=#{password} WHERE userName=#{userName}")
	int updatePasswordByUserName(@Param("userName") String userName, @Param("password") String password);

	@Select("<script>"
			+ "SELECT count(*) FROM pb_user  WHERE "
			+ "<if test='userName!=null and userName != \"\" '>"
			+" userName LIKE CONCAT('%',#{userName},'%')  AND "
			+ "</if>"
			+ " userUUID != #{userUUID}"
			+ "</script>")
	int selectUserAllNum(@Param("userName") String userName, @Param("userUUID") String userUUID);
	
	@Delete("<script>"
			+"DELETE FROM pb_user WHERE userUUID in"
			+ "<foreach item='item' index='index' collection='userUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deleteUserByUserUUID(@Param("userUUID") String[] userUUID);
	
	@Update("<script>"
			+ "UPDATE pb_user SET userName=#{userName},admin=#{admin}"
			+ "<if test='password!=null and password != \"\" '>"
			+ ",password=#{password}"
			+ "</if>"
			+ " WHERE userUUID=#{userUUID}"
			+ "</script>")
	void updateUserByUserUUID(UserDto userDto);

	@Select("SELECT password FROM pb_user WHERE userName=#{userName}")
	String getPassword(@Param("userName")String userName);
}
