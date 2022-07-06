package com.cholas.community.dto;

import lombok.Data;

/**
 * @author cholas
 * @date 2022/7/6 15:55
 * @description NotificationDTO
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;
}
