package com.chinasofti.postbar.controller;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.PostVO;
import com.chinasofti.postbar.service.PostService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by LinYaTing on 2019/6/5 17:38
 */
@Controller
@RequestMapping(value = "/myPostController")
public class MyPostController extends BasicController {

    @Autowired
    private PostService postService;

    /**
     * 查询出我的所有文章
     * @param postTitle
     * @param pageIndex
     * @param everyPageDataCount
     */
    @RequestMapping(value = "/getMyPostList", method = RequestMethod.POST)
    public void getMyPostList(String postTitle, int pageIndex, int everyPageDataCount, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        String userUUID = (String) session.getAttribute("id");
        //调用业务层代码查询出自己的文章
        PostVO myPostList = postService.getMyPostList(postTitle, pageIndex, everyPageDataCount, userUUID);
        //返回json数据给前端
        if (myPostList == null) {
            json.put("message", "系统超时");
        }else {
            json.put("message", "");
            json.put("postList", myPostList.getPostDtos());
            json.put("postAllNum", myPostList.getTotalCounts());
            json.put("allPage", myPostList.getTotalPage());
            json.put("pageIndex", myPostList.getNowPage());
        }
        writeJson(json.toString(), response);
    }
}
