package com.chinasofti.postbar.controller;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.CommonVO;
import com.chinasofti.postbar.service.CommentSerivce;
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
 * Created by LinYaTing on 2019/6/5 18:22
 */
@Controller
@RequestMapping(value = "/myCommentController")
public class CommentController extends BasicController {

    @Autowired
    private CommentSerivce commentSerivce;

    /**
     * 查询出自己所有的评论
     * @param pageIndex
     * @param everyPageDataCount
     */
    @RequestMapping(value = "/selectMyCommentByUserUUID", method = RequestMethod.POST)
    public void getMyComments(int pageIndex, int  everyPageDataCount, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        String userUUID = (String) session.getAttribute("id");
        CommonVO commonVO = commentSerivce.getMyComments(pageIndex, everyPageDataCount, userUUID);
        if (sessionTimeout(request)) {
            json.put("message", "登陆超时");
        }else {
            json.put("message","");
            json.put("myCommentlist", commonVO.getCommentDtoList());
            json.put("postAllNum", commonVO.getTotalCounts());
            json.put("allPage", commonVO.getTotalPage());
            json.put("pageIndex", commonVO.getNowPage());
        }
        writeJson(json.toString(), response);
    }

    @RequestMapping(value = "/getCommentByCmUUID", method = RequestMethod.POST)
    public void getCommentByCmUUID(String cmUUID, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        String cmText = commentSerivce.getCmTextByCmUUID(cmUUID);
        if (sessionTimeout(request)) {
            json.put("message", "页面过期，登录超时！");
        }else {
            json.put("message", "");
            json.put("cmText", cmText);
        }
        writeJson(json.toString(), response);
    }

    @RequestMapping(value = "/editCom", method = RequestMethod.POST)
    public void editComment(CommentDto commentDto, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        commentDto.setCmTime(getStringDate(new Date()));
        boolean result = commentSerivce.editComment(commentDto);
        if (result) {
            json.put("message", "");
        }else  {
            json.put("message", "评论信息更新失败！");
        }
        writeJson(json.toString(), response);
    }
}
