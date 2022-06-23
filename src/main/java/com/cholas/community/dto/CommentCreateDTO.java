package com.cholas.community.dto;

import lombok.Data;

/**
 * @author cholas
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String content;
}
