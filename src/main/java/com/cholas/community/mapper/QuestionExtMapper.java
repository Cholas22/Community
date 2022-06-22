package com.cholas.community.mapper;

import com.cholas.community.model.Question;

/**
 * @author cholas
 */
public interface QuestionExtMapper {
    int incView(Question row);

    int incCommentCount(Question question);
}