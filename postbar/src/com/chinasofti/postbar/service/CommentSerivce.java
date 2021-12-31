package com.chinasofti.postbar.service;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.CommonVO;

/**
 * Created by LinYaTing on 2019/6/5 18:26
 */
public interface CommentSerivce {
    CommonVO getMyComments(int pageIndex, int everyPageDataCount, String userUUID);

    String getCmTextByCmUUID(String cmUUID);

    boolean editComment(CommentDto commentDto);
}
