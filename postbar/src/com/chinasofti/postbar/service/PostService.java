package com.chinasofti.postbar.service;

import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostVO;

import java.util.List;

/**
 * Created by LinYaTing on 2019/6/4 22:17
 */
public interface PostService {

    PostVO getPostList(String postTitle, int nowPage, int pageSize,String uuid);

    boolean addPost(PostDto postDto);

    boolean deletePost(String[] postUUID);

    PostVO getMyPostList(String postTitle, int nowPage, int pageSize, String userUUID);
}
