package com.chinasofti.postbar.service.impl;

import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostVO;
import com.chinasofti.postbar.mapper.PbPostMapper;
import com.chinasofti.postbar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by LinYaTing on 2019/6/4 22:17
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PbPostMapper postMapper;

        public PostVO getPostList(String postTitle, int nowPage, int pageSize,String uuid) {
        PostVO postVO = new PostVO();
//        Page<PostDto> page = new Page<>(nowPage, pageSize);
//        EntityWrapper<PostDto> entityWrapper = new EntityWrapper<>();
//        if (postTitle != null) {
//            entityWrapper.like("postTitle", "%" + postTitle + "%");
//        }
//        List<PostDto> postList = postMapper.selectPage(page, entityWrapper);
//        //总记录
//        Integer totalCounts = postMapper.selectCount(entityWrapper);
//        //总页数
//        int totalPages = (totalCounts / pageSize) + 1;
//        postVO.setPostDtos(postList);
//        postVO.setNowPage(nowPage);
//        postVO.setTotalCounts(totalCounts);
//        postVO.setTotalPage(totalCounts);
//        return postVO;
            List<PostDto> postDtos = null;
            int startNo = nowPage * pageSize;
            int totalCounts = 0;
            if (!postTitle.equals("")) {//模糊查询所有
                postDtos = postMapper.selectPostList(postTitle, startNo, pageSize, null);
                totalCounts = postMapper.selectPostAllNum(postTitle, null);
            }else {//查询所有
                postDtos = postMapper.selectPostList(null, nowPage * pageSize, pageSize, null);
                totalCounts = postMapper.selectPostAllNum(null, null);
            }
            int totalPages = (totalCounts / pageSize) + 1;
            postVO.setNowPage(nowPage);
            postVO.setTotalPage(totalPages);
            postVO.setTotalCounts(totalCounts);
            postVO.setPostDtos(postDtos);
            return postVO;
        }

    @Override
    public boolean addPost(PostDto postDto) {
        int row = postMapper.insertPost(postDto);
        if (row >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePost(String[] postUUID) {
        int row = postMapper.deletePost(postUUID);
        if (row >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public PostVO getMyPostList(String postTitle, int nowPage, int pageSize, String userUUID) {
        PostVO postVO = new PostVO();
        List<PostDto> postDtos = null;
        int startNo = nowPage * pageSize;
        int totalCounts = 0;
        if (!postTitle.equals("")) {//模糊查询所有
            postDtos = postMapper.selectPostList(postTitle, startNo, pageSize, userUUID);
            totalCounts = postMapper.selectPostAllNum(postTitle, userUUID);
        }else {//查询所有
            postDtos = postMapper.selectPostList(null, nowPage * pageSize, pageSize, userUUID);
            totalCounts = postMapper.selectPostAllNum(null, userUUID);
        }
        int totalPages = (totalCounts / pageSize) + 1;
        postVO.setNowPage(nowPage);
        postVO.setTotalPage(totalPages);
        postVO.setTotalCounts(totalCounts);
        postVO.setPostDtos(postDtos);
        return postVO;
    }
}
