package com.cholas.community.enums;

/**
 * @author cholas
 * @date 2022/7/4 17:24
 * @description NotificationStatusEnum
 */
public enum NotificationStatusEnum {

    UNREAD(0),
    READ(1)
    ;

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
