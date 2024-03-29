package com.cholas.community.exception;

/**
 * @author cholas
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    /**
     * 问题不存在
     */
    QUESTION_NOT_FOUND(2001,"问题不存在"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论"),
    NO_LOGIN(2003,"当前操作需登录后操作，请登录后重置"),
    SYS_ERROR(2004,"服务器报错，稍后再试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"评论不存在"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    NOTIFICATION_NOT_FOUND(2008,"提醒不存在"),
    READ_NOTIFICATION_FAIL(2009,"读取提醒失败")
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
