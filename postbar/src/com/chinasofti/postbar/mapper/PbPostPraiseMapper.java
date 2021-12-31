package com.chinasofti.postbar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.PostPraiseDto;

public interface PbPostPraiseMapper {
	
	@Delete("<script>"
			+"DELETE FROM pb_post_praise WHERE postUUID in"
			+ "<foreach item='item' index='index' collection='postUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deletePostPraise(@Param("postUUID") String[] postUUID);
	
	@Delete("<script>"
			+"DELETE FROM pb_post_praise WHERE userUUID in"
			+ "<foreach item='item' index='index' collection='userUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deletePostPraiseByUserUUID(@Param("userUUID") String[] userUUID);
	
	@Select("SELECT * FROM pb_post_praise WHERE postUUID=#{postUUID}")
	List<PostPraiseDto> selectPraiseByPostUUID(@Param("postUUID") String postUUID);
	
	@Insert("INSERT INTO pb_post_praise (poPrUUID,postUUID,userUUID) VALUES (#{poPrUUID},#{postUUID},#{userUUID})")
	void insertPraise(PostPraiseDto pbPostPraiseDto);
	
	@Select("SELECT count(*) FROM pb_post_praise WHERE postUUID=#{postUUID}")
	int selectPraiseNum(@Param("postUUID") String postUUID);
}
