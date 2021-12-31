package com.chinasofti.postbar.service.impl;

import com.chinasofti.postbar.dto.CommentDto;
import com.chinasofti.postbar.dto.CommonVO;
import com.chinasofti.postbar.mapper.PbCommentMapper;
import com.chinasofti.postbar.service.CommentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LinYaTing on 2019/6/5 18:27
 */
@Service
public class CommentSerivceImpl implements CommentSerivce{

    @Autowired
    private PbCommentMapper commentMapper;

    @Override
    public CommonVO getMyComments(int pageIndex, int everyPageDataCount, String userUUID) {
        CommonVO commonVO = new CommonVO();
        List<CommentDto> commentList = commentMapper.selectMyCommentByUserUUID(userUUID, pageIndex * everyPageDataCount, everyPageDataCount);
        int totalCount = commentMapper.selectMyCommentNumByUserUUID(userUUID);
        int totalPage = (totalCount / everyPageDataCount) + 1;
        commonVO.setCommentDtoList(commentList);
        commonVO.setNowPage(pageIndex);
        commonVO.setTotalCounts(totalCount);
        commonVO.setTotalPage(totalPage);
        return commonVO;
    }

    @Override
    public String getCmTextByCmUUID(String cmUUID) {
        String cmText = commentMapper.selectCmTextByCmUUID(cmUUID);
        return cmText;
    }

    @Override
    public boolean editComment(CommentDto commentDto) {
        int row = commentMapper.updateCmByCmUUID(commentDto);
        if (row >= 1) {
            return true;
        }
        return false;
    }
}
