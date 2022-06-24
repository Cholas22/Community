package com.cholas.community.mapper;

import com.cholas.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}