package com.chinasofti.postbar.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chinasofti.postbar.dto.CommentPraiseDto;


public interface PbCommentPraiseMapper {
	@Delete("<script>"
			+"DELETE FROM pb_comment_praise WHERE postUUID in"
			+ "<foreach item='item' index='index' collection='postUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deleteCommentPraiseByPostUUID(@Param("postUUID") String[] postUUID);
	
	@Delete("<script>"
			+"DELETE FROM pb_comment_praise WHERE userUUID in"
			+ "<foreach item='item' index='index' collection='userUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deleteCommentPraiseByUserUUID(@Param("userUUID") String[] userUUID);
	
	@Delete("<script>"
			+"DELETE FROM pb_comment_praise WHERE cmUUID in"
			+ "<foreach item='item' index='index' collection='cmUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deleteCommentPraiseByCmUUID(@Param("cmUUID") String[] cmUUID);
	
	@Select("<script>SELECT COUNT(*) FROM pb_comment_praise WHERE"
			+ "<if test='userUUID!=null and userUUID != \"\" '>"
			+ " userUUID=#{userUUID} AND "
			+ "</if>"
			+ " cmUUID=#{cmUUID} AND postUUID=#{postUUID}</script>")
	int selectCommentNumByParameter(@Param("postUUID") String postUUID, @Param("cmUUID") String cmUUID, @Param("userUUID") String userUUID);
	
	
	
	@Insert("INSERT INTO pb_comment_praise (cmPrUUID,postUUID,userUUID,cmUUID) VALUES (#{cmPrUUID},#{postUUID},#{userUUID},#{cmUUID})")
	void insertPraise(CommentPraiseDto CommentPraiseDto);
	

	
	
	
}
