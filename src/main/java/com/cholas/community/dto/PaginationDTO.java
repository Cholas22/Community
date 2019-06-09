package com.cholas.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showNext;
    private boolean showEnd;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i >= 1) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //showPrevious;前一页
        if (page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        //showNext;下一页
        if (page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }
        //showFirst;第一页
        if (pages.contains(1)) {
            showFirst = false;
        } else {
            showFirst = true;
        }
        //showEnd;最后一页
        if (pages.contains(totalPage)) {
            showEnd = false;
        } else {
            showEnd = true;
        }
    }
}
