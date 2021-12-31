package com.chinasofti.postbar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.RegisterDto;


public interface PbRegisterMapper {
	@Insert("INSERT INTO pb_register (regUUID,userUUID,regSex,regAge,regEmial,regPhoto,regTime) VALUES (#{regUUID},#{userUUID},#{regSex},#{regAge},#{regEmial},#{regPhoto},#{regTime})")
	int insertRegister(RegisterDto registerDto);
	
	@Select("SELECT * FROM pb_register WHERE userUUID=#{userUUID}")
	RegisterDto selectRegister(@Param("userUUID") String userUUID);
    
	@Select("SELECT a.regUUID,a.regAge,a.regEmial,a.regSex,a.regUUID,a.userUUID,b.userName FROM pb_register a,pb_user b WHERE a.userUUID=b.userUUID AND a.userUUID=#{userUUID}")
	RegisterDto selectRegisterAndUserName(@Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_register SET regSex=#{regSex},regAge=#{regAge},regEmial=#{regEmial} WHERE regUUID=#{regUUID} ")
	void upDateRegisterByRegUUID(RegisterDto registerDto);
	
	@Select("SELECT regPhoto FROM pb_register WHERE userUUID=#{userUUID}")
	String selectRegPhotoByUserUUID(@Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_register SET regPhoto=#{regPhoto} WHERE userUUID=#{userUUID} ")
	void upDateRegPhotoByRegUUID(RegisterDto registerDto);
	
	@Select("<script>"
			+ "SELECT b.regUUID,b.userUUID,b.regSex,b.regAge,b.regEmial,b.regTime,a.userName,a.loginTime,a.admin FROM pb_register b,pb_user a WHERE a.userUUID=b.userUUID"
			+ "<if test='userName!=null and userName != \"\" '>"
			+" AND a.userName LIKE CONCAT('%',#{userName},'%')"
			+ "</if>"
			+" AND a.userUUID != #{userUUID}"
			+" ORDER BY b.regTime DESC"
			+" LIMIT #{startNo},#{pageSize}"
			+ "</script>")
	List<RegisterDto> selectRegList(@Param("userName") String userName, @Param("startNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("userUUID") String userUUID);
	
	
	@Delete("<script>"
			+"DELETE FROM pb_register WHERE userUUID in"
			+ "<foreach item='item' index='index' collection='userUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deleteRegByUserUUID(@Param("userUUID") String[] userUUID);
	
	
	@Update("<script>"
			+ "UPDATE pb_register SET regSex=#{regSex},regAge=#{regAge},regEmial=#{regEmial}"
			+ " WHERE userUUID=#{userUUID}"
			+ "</script>")
	void updateRegByUserUUID(RegisterDto registerDto);
}
