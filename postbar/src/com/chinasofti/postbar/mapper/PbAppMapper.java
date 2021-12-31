package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.AppDto;

public interface PbAppMapper {
	@Select("select id,appID,apiKey,secretKey from pb_app")
	AppDto selectApp();
}
