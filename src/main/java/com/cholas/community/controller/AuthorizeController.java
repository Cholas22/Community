package com.cholas.community.controller;

import com.cholas.community.dto.AccessTokenDTO;
import com.cholas.community.dto.GithubUser;
import com.cholas.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("cac3b05d57657b5636a1");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret("b4f238bedf61c870a0fb87e1d5223d87209db8c9");
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
