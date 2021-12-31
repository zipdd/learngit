package com.chinasofti.postbar.controller;

import com.chinasofti.postbar.basic.controller.BasicController;
import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.service.AudioService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by LinYaTing on 2019/6/5 22:39
 */
@Controller
@RequestMapping(value = "/audioSetUpController")
public class AudioSetUpController extends BasicController {

    @Autowired
    private AudioService audioService;

    @RequestMapping(value = "/selectAudioSetUp", method = RequestMethod.POST)
    public void getAudioSetUp(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        AudioDto audioDto = audioService.getAudioSetUp(id);
        System.out.println(audioDto.getAuSetPit());
        if (sessionTimeout(request)) {
            json.put("message", "页面过期，登录超时！");
        }else {
            json.put("message", "");
            json.put("auidoDto",audioDto);
        }
        writeJson(json.toString(), response);
    }

    @RequestMapping(value = "/updateAudioSetUp", method = RequestMethod.POST)
    public void updateAudioSetUp(AudioDto audioDto, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        boolean result = audioService.updateAudioSetUp(audioDto);
        if (result) {
            json.put("message", "");
        }else {
            json.put("message", "更新音频失败！");
        }
        writeJson(json.toString(), response);
    }
}
