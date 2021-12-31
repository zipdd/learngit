package com.chinasofti.postbar.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LinYaTing on 2019/6/5 16:00
 */
public class UserVO implements Serializable{

    private int totalCounts;//总记录
    private List<RegisterDto> regList;
    private Integer nowPage = 1;//当前页
    private int totalPage;//总页数

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<RegisterDto> getRegList() {
        return regList;
    }

    public void setRegList(List<RegisterDto> regList) {
        this.regList = regList;
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
