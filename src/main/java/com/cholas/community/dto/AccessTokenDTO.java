package com.cholas.community.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    private String client_id;
    private String clientSecret;
    private String code;
    private String redirectUrl;
    private String state;

}
