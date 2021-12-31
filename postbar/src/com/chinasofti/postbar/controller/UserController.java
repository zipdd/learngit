package com.chinasofti.postbar.controller;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.UserVO;
import com.chinasofti.postbar.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by LinYaTing on 2019/6/5 15:38
 */
@Controller
@RequestMapping(value = "/userManageController")
public class UserController extends BasicController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public void getUserList(String userName, int pageIndex, int everyPageDataCount, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        UserVO userVO = userService.getUserList(userName, pageIndex, everyPageDataCount, id);
        if (userVO == null) {
            json.put("message", "没有用户！");
        }else {
            json.put("message", "");
            json.put("registerList", userVO.getRegList());
            json.put("userAllNum", userVO.getTotalCounts());
            json.put("allPage", userVO.getTotalPage());
            json.put("pageIndex", userVO.getNowPage());
        }
        writeJson(json.toString(), response);
    }
}
