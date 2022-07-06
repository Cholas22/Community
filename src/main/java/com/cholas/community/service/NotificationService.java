package com.cholas.community.service;

import com.cholas.community.dto.NotificationDTO;
import com.cholas.community.dto.PaginationDTO;
import com.cholas.community.enums.NotificationStatusEnum;
import com.cholas.community.enums.NotificationTypeEnum;
import com.cholas.community.exception.CustomizeErrorCode;
import com.cholas.community.exception.CustomizeException;
import com.cholas.community.mapper.NotificationMapper;
import com.cholas.community.model.Notification;
import com.cholas.community.model.NotificationExample;
import com.cholas.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author cholas
 * @date 2022/7/6 15:58
 * @description NotificationService
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        Integer totalPage;
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(example);


        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (totalPage < 1) {
            totalPage = 1;
        }
        if ((page < 1)) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        int offset = size * (page - 1);
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        notificationExample.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds(offset, size));

        if(CollectionUtils.isEmpty(notifications)){
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO dto = new NotificationDTO();
            BeanUtils.copyProperties(notification,dto);
            dto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(dto);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    public Long unreadCount(Long id) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(null == notification){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!Objects.equals(notification.getReceiver(),user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKeySelective(notification);

        NotificationDTO dto = new NotificationDTO();
        BeanUtils.copyProperties(notification,dto);
        dto.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return dto;
    }
}
