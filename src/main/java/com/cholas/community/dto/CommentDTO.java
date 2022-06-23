package com.cholas.community.dto;

import com.cholas.community.model.User;
import lombok.Data;

/**
 * @author cholas
 * @date 2022/6/23 13:50
 * @description CommentDTO
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer likeCount;
    private String content;
    private User user;
}
