package com.cholas.community.mapper;

import com.cholas.community.model.Question;

import java.util.List;

/**
 * @author cholas
 */
public interface QuestionExtMapper {
    int incView(Question row);

    int incCommentCount(Question question);

    List<Question> selectRelated(Question question);
}