package com.cholas.community.enums;

/**
 * @author cholas
 * @date 2022/7/4 16:18
 * @description NotificationTypeEnum
 */
public enum NotificationTypeEnum {

    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论")
    ;

    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }

    public static String nameOfType(int type){
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if(type == value.getType()){
                return value.getName();
            }
        }
        return "";
    }
}
