package com.cholas.community.enums;

/**
 * @author cholas
 */
public enum CommentTypeEnum {
    /**
     *
     */
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum typeEnum : CommentTypeEnum.values()) {
            if(type.equals(typeEnum.getType())){
                return true;
            }
        }
        return false;
    }
}
