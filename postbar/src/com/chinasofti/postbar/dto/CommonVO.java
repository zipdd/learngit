package com.chinasofti.postbar.dto;

import java.util.List;

/**
 * Created by LinYaTing on 2019/6/5 19:02
 */
public class CommonVO {

    private int totalCounts;//总记录
    private List<CommentDto> commentDtoList;
    private Integer nowPage = 1;//当前页
    private int totalPage;//总页数

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDtoList(List<CommentDto> commentDtoList) {
        this.commentDtoList = commentDtoList;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
