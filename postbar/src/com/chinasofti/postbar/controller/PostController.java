package com.chinasofti.postbar.controller;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.PostDto;
import com.chinasofti.postbar.dto.PostVO;
import com.chinasofti.postbar.service.PostService;
import com.sun.prism.impl.BaseContext;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by LinYaTing on 2019/6/4 21:37
 */
@Controller
@RequestMapping(value = "/postController")
public class PostController extends BasicController{

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/getPostList", method = RequestMethod.POST)
    public void getPostList(String postTitle, int pageIndex, int everyPageDataCount, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute("admin");
        String uuid = (String) session.getAttribute("id");
        PostVO postList = postService.getPostList(postTitle, pageIndex, everyPageDataCount,uuid);
        if (postList == null) {
            json.put("message", "没有文章可查询！");
        }else {
            json.put("message", "");
           // json.admin,json.postList,json.postAllNum,json.allPage,json.pageIndex
            json.put("admin", admin);
            json.put("postList",postList.getPostDtos());
            json.put("postAllNum",postList.getTotalCounts());
            json.put("allPage", postList.getTotalPage());
            json.put("pageIndex", postList.getNowPage());
        }
        writeJson(json.toString(), response);
    }

    //添加文章
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public void addPost(PostDto postDto, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        String postUUID = getUUID();
        HttpSession session = request.getSession();
        String userUUID = (String) session.getAttribute("id");
        postDto.setPostUUID(postUUID);
        postDto.setUserUUID(userUUID);
        postDto.setPostTime(getStringDate(new Date()));
        boolean result = postService.addPost(postDto);
        if (result) {
            json.put("message", "");
        }else {
            json.put("message", "添加文章失败！");
        }
        writeJson(json.toString(), response);
    }

    //删除文章
    public void deletePost(String[] postUUID, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        boolean result = postService.deletePost(postUUID);
        if (result) {
            json.put("message", "");
        }else {
            json.put("message", "删除失败！");
        }
        writeJson(json.toString(), response);
    }
}
