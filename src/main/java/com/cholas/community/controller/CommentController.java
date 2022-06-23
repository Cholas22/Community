package com.cholas.community.controller;

import com.cholas.community.dto.CommentCreateDTO;
import com.cholas.community.dto.CommentDTO;
import com.cholas.community.dto.ResultDTO;
import com.cholas.community.enums.CommentTypeEnum;
import com.cholas.community.exception.CustomizeErrorCode;
import com.cholas.community.model.Comment;
import com.cholas.community.model.User;
import com.cholas.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author cholas
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comment(@PathVariable(name = "id") Long id){
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(comments);
    }


}
