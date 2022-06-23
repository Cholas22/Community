package com.cholas.community.service;

import com.cholas.community.dto.CommentDTO;
import com.cholas.community.enums.CommentTypeEnum;
import com.cholas.community.exception.CustomizeErrorCode;
import com.cholas.community.exception.CustomizeException;
import com.cholas.community.mapper.CommentMapper;
import com.cholas.community.mapper.QuestionExtMapper;
import com.cholas.community.mapper.QuestionMapper;
import com.cholas.community.mapper.UserMapper;
import com.cholas.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

        }


    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum typeEnum) {
        // 查询评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(typeEnum.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if(CollectionUtils.isEmpty(comments)){
            return new ArrayList<>();
        }
        // 获取评论人id
        List<Long> commentators = comments.stream().map(Comment::getCommentator).distinct().collect(Collectors.toList());
        // 获取评论人 转化为map
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(commentators);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, u -> u));

        // comment转化为commentDto
        List<CommentDTO> commentDTOList = comments.stream().map(e -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(e, commentDTO);
            commentDTO.setUser(userMap.get(e.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOList;
    }
}
