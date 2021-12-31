package com.chinasofti.postbar.controller;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.service.UserService;
import com.chinasofti.postbar.util.MD5Util;
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
 * Created by LinYaTing on 2019/6/4 13:09
 */
@Controller
@RequestMapping(value = "/loginController")
public class LoginController extends BasicController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(UserDto userDto, HttpSession session, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        String pwd = MD5Util.encrypt(userDto.getPassword());
        userDto.setPassword(pwd);
        UserDto newUser = userService.login(userDto);
        if (userDto == null) {
            json.put("message", "用户名或者密码为空");
        }else {
            json.put("message", "");
            session.setAttribute("id", newUser.getUserUUID());
            session.setAttribute("usrname", newUser.getUserName());
            session.setAttribute("admin", newUser.getAdmin());
            userService.updateLoginTime(newUser.getUserName());
        }
        writeJson(json.toString(), response);
    }

    @RequestMapping(value = "/getAdmin", method = RequestMethod.POST)
    public void getAdmin(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute("admin");
        if (admin == null) {
            json.put("message", "还没登陆！");
        }else {
            json.put("message","");
            json.put("admin", admin);
        }
        writeJson(json.toString(), response);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        session.invalidate();
        writeJson(json.toString(), response);
    }

    @RequestMapping(value = "/addRegister")
    public void register(RegisterDto registerDto, UserDto userDto,HttpServletResponse response) {
        JSONObject json = new JSONObject();
        String regUUID = getUUID();
        String userUUID = getUUID();
        registerDto.setRegUUID(regUUID);
        registerDto.setUserUUID(userUUID);
        userDto.setUserUUID(userUUID);
        userDto.setPassword(MD5Util.encrypt(userDto.getPassword()));
        if (registerDto.getRegSex().equals("男")) {
            registerDto.setRegSex("1");
        }else {
            registerDto.setRegSex("0");
        }
        registerDto.setRegTime(getStringDate(new Date()));
        boolean result = userService.register(registerDto, userDto);
        if (!result) {
            json.put("message", "注册失败！");
        }else {
            json.put("message", "");
        }
        writeJson(json.toString(), response);
    }

    @RequestMapping(value = "/editPassword", method = RequestMethod.POST)
    public void editPassword(String oldPassword, String newPassword,HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        String usrname = (String) session.getAttribute("usrname");
        String oPassword = userService.getPasswordByUserName(usrname);
        if (oPassword.equals(MD5Util.encrypt(oldPassword))) {
            String password = MD5Util.encrypt(newPassword);
            int result = userService.updatePasswordByUserName(usrname, password);
            if (result == 1) {
                json.put("message", "");
                json.put("usrname",usrname);
            }else {
                json.put("message","更新密码不成功！");
            }
        }else {
            json.put("message", "旧密码不正确");
        }

        writeJson(json.toString(), response);
    }
}
