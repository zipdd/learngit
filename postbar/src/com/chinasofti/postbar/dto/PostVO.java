package com.chinasofti.postbar.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LinYaTing on 2019/6/4 23:29
 */
public class PostVO implements Serializable{

    private int totalCounts;//总记录
    private List<PostDto> postDtos;
    private Integer nowPage = 1;//当前页
    private int totalPage;//总页数

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<PostDto> getPostDtos() {
        return postDtos;
    }

    public void setPostDtos(List<PostDto> postDtos) {
        this.postDtos = postDtos;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
