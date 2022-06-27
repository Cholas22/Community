package com.cholas.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author cholas
 * @date 2022/6/27 23:25
 * @description TagDTO
 */
@Data
public class TagDTO {
    private String categoryName;
    List<String> tags;
}
