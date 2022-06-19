package com.cholas.community.exception;

/**
 * @author cholas
 */

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    /**
     * 问题不存在
     */
    QUESTION_NOT_FOUND("问题不存在");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
