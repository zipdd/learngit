package com.chinasofti.postbar.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.AudioDto;

public interface PbAudioMapper {
	@Insert("INSERT INTO pb_audio (auSetUUID,userUUID,auSetSpd,auSetPit,auSetVol,auSetVoiPer) VALUES (#{auSetUUID},#{userUUID},#{auSetSpd},#{auSetPit},#{auSetVol},#{auSetVoiPer})")
	void insertAudio(AudioDto audioDto);
	
	@Select("SELECT auSetUUID,userUUID,auSetSpd,auSetPit,auSetVol,auSetVoiPer FROM pb_audio  WHERE userUUID=#{userUUID}  ")
	AudioDto selectAudioByUserUUID(@Param("userUUID") String userUUID);
	
	@Update("UPDATE pb_audio SET auSetSpd = #{auSetSpd},auSetPit=#{auSetPit},auSetVol=#{auSetVol},auSetVoiPer=#{auSetVoiPer} WHERE auSetUUID = #{auSetUUID}")
	int updateAudioByAuSetUUID(AudioDto audioDto);
	
	@Delete("<script>"
			+"DELETE FROM pb_audio WHERE userUUID in"
			+ "<foreach item='item' index='index' collection='userUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deleteAudioByUserUUID(@Param("userUUID") String[] userUUID);
}
