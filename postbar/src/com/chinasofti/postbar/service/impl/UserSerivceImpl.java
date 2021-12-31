package com.chinasofti.postbar.service.impl;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.dto.UserVO;
import com.chinasofti.postbar.mapper.PbRegisterMapper;
import com.chinasofti.postbar.mapper.PbUserMapper;
import com.chinasofti.postbar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by LinYaTing on 2019/6/4 13:12
 */
@Service
public class UserSerivceImpl implements UserService{

    @Autowired
    private PbUserMapper userMapper;

    @Autowired
    private PbRegisterMapper registerMapper;

    @Override
    public UserDto login(UserDto userDto) {
        return userMapper.selectUserByUserNameAndPassword(userDto.getUserName(), userDto.getPassword());
    }

    @Override
    public void updateLoginTime(String userName) {
        userMapper.updateLoginTimeByUserName(userName,new Date());
    }

    @Override
    public boolean register(RegisterDto registerDto, UserDto userDto) {
        int reg = registerMapper.insertRegister(registerDto);
        int user = userMapper.insertUser(userDto);
        if (user == 1 && reg == 1) {
            return true;
        }
        return false;
    }

    @Override
    public int updatePasswordByUserName(String usrname, String newPasword) {
        int row = userMapper.updatePasswordByUserName(usrname, newPasword);
        return row;
    }

    @Override
    public String getPasswordByUserName(String usrname) {
        String password = userMapper.getPassword(usrname);
        return password;
    }

    @Override
    public UserVO getUserList(String userName, int nowPage, int pageSize, String id) {
        List<RegisterDto> regList = null;
        UserVO userVO = new UserVO();
        int totalCount = 0;
        if (userName != null) {
            regList = registerMapper.selectRegList(userName, nowPage * pageSize, pageSize, id);
            totalCount = userMapper.selectUserAllNum(userName, null);
        }else {
            regList = registerMapper.selectRegList(null, nowPage * pageSize, pageSize, id);
            totalCount = userMapper.selectUserAllNum(null, null);
        }
        int totalPage = (totalCount / pageSize) + 1;
        userVO.setRegList(regList);
        userVO.setNowPage(nowPage);
        userVO.setTotalCounts(totalCount);
        userVO.setTotalPage(totalPage);
        return userVO;
    }

}
