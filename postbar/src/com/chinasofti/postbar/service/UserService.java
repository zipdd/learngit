package com.chinasofti.postbar.service;

import com.chinasofti.postbar.dto.RegisterDto;
import com.chinasofti.postbar.dto.UserDto;
import com.chinasofti.postbar.dto.UserVO;

/**
 * Created by LinYaTing on 2019/6/4 13:12
 */
public interface UserService {

    UserDto login(UserDto userDto);

    void updateLoginTime(String userName);

    boolean register(RegisterDto registerDto, UserDto userDto);

    int updatePasswordByUserName(String usrname, String pasword);

    String getPasswordByUserName(String usrname);

    UserVO getUserList(String userName, int nowPage, int pageSize, String id);
}
