package org.xiong.community.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页列表展示信息
 * 包括问题和控制下边的页码展示
 */
public class PageDTO {
    //问题列表
    private List<QuestionDTO> questionDTOList;
    //页码控制
    private boolean hasPrevious;    //<
    private boolean hasFirstPage;   //<<
    private boolean hasNext;    //>
    private boolean hasEndPage; //>>
    private Integer page;   //当前页码
    private List<Integer> pages =new ArrayList<>();    //页码列表
    private Integer totalPage;  //总页码

    //计算页码显示
    public void pageInit(Integer count, Integer page, int size) {
        if (count % size == 0) {
            totalPage = count / size;
        } else {
            totalPage = count / size + 1;
        }

        //控制显示标志 上一页 第一页 下一页  最后一页
        if (totalPage != 1) {
            if (page/size+1 == 1) {
                hasPrevious = false;
            } else {
                hasPrevious = true;
            }
            if (page/size+1 != totalPage.intValue()) {
                hasNext = true;
            } else {
                hasNext = false;
            }

            if(page/size+1>=5){
                hasFirstPage=true;
            }else {
                hasFirstPage=false;
            }
            if(totalPage-page/size+1>=5){
                hasEndPage=true;
            }else {
                hasEndPage=false;
            }

        } else {
            hasFirstPage = false;
            hasEndPage = false;
            hasPrevious=false;
            hasEndPage=false;
        }
        for(int i=1;i<=totalPage;i++){
            pages.add(i);
        }
    }

    public List<QuestionDTO> getQuestionDTOList() {
        return questionDTOList;
    }

    public void setQuestionDTOList(List<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasFirstPage() {
        return hasFirstPage;
    }

    public void setHasFirstPage(boolean hasFirstPage) {
        this.hasFirstPage = hasFirstPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasEndPage() {
        return hasEndPage;
    }

    public void setHasEndPage(boolean hasEndPage) {
        this.hasEndPage = hasEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
