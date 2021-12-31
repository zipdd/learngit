package com.chinasofti.postbar.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chinasofti.postbar.dto.PostDto;


public interface PbPostMapper{
	@Insert("INSERT INTO pb_post (postUUID,userUUID,postTitle,postText,postPageviews,postAudio,postTime) VALUES (#{postUUID},#{userUUID},#{postTitle},#{postText},#{postPageviews},#{postAudio},#{postTime})")
	int insertPost(PostDto postDto);
	
	@Select("<script>"
			+ "SELECT count(*) FROM pb_post  "
			+ "<if test='postTitle!=null and postTitle != \"\" '>"
			+" WHERE postTitle LIKE CONCAT('%',#{postTitle},'%') "
			+ "</if>"
			+ "<if test='(postTitle!=null and postTitle != \"\") and (userUUID!=null and userUUID != \"\") '>"
			+" AND userUUID=#{userUUID} "
			+ "</if>"
			+ "<if test='(postTitle==null or postTitle == \"\") and (userUUID!=null and userUUID != \"\") '>"
			+" WHERE userUUID=#{userUUID} "
			+ "</if>"
			+ "</script>")
	int selectPostAllNum(@Param("postTitle") String postTitle, @Param("userUUID") String userUUID);
	

	@Select("<script>"
			+ "SELECT a.postUUID,c.userName,f.cmTime,IFNULL(f.commentNum,0) as commentNum,a.userUUID,a.postTitle,a.postPageviews,a.postTime FROM (pb_post as a LEFT JOIN (SELECT b.userName,b.userUUID FROM pb_user as b ) as c ON a.userUUID=c.userUUID)  LEFT JOIN (SELECT COUNT(*) AS commentNum,e.postUUID,max(e.cmTime) AS cmTime FROM post_bar.pb_comment as e GROUP BY e.postUUID ) AS f ON f.postUUID=a.postUUID  "
			+ "<if test='postTitle!=null and postTitle != \"\" '>"
			+" WHERE a.postTitle LIKE CONCAT('%',#{postTitle},'%')"
			+ "</if>"
			+ "<if test='(postTitle==null or postTitle == \"\") and (userUUID!=null and userUUID != \"\") '>"
			+" WHERE a.userUUID=#{userUUID}"
			+ "</if>"
			+ "<if test='(postTitle!=null and postTitle != \"\") and (userUUID!=null and userUUID != \"\") '>"
			+" AND a.userUUID=#{userUUID}"
			+ "</if>"
			
			+" ORDER BY f.cmTime DESC"
			+" LIMIT #{startNo},#{pageSize}"
			+ "</script>")
	List<PostDto> selectPostList(@Param("postTitle") String postTitle, @Param("startNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("userUUID") String userUUID);
	
	@Delete("<script>"
			+"DELETE FROM pb_post WHERE postUUID in"
			+ "<foreach item='item' index='index' collection='postUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	int deletePost(@Param("postUUID") String[] postUUID);
	
	@Delete("<script>"
			+"DELETE FROM pb_post WHERE userUUID in"
			+ "<foreach item='item' index='index' collection='userUUID' open='(' separator=',' close=')'>"
	        +       "#{item}"
	        + "</foreach>"
			+"</script>")
	void deletePostByUserUUID(@Param("userUUID") String[] userUUID);
	
	
	@Select("SELECT * FROM pb_post as a WHERE postUUID=#{postUUID}  ")
	PostDto selectPostByPostUUID(@Param("postUUID") String postUUID);
	
	@Update("UPDATE pb_post SET postPageviews=#{postPageviews} WHERE postUUID=#{postUUID}")
	void updatePostPageviews(@Param("postUUID") String postUUID, @Param("postPageviews") int postPageviews);
}
