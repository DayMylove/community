package com.av50200264.community.ctroller;

import com.av50200264.community.dto.AccessTokenDTO;
import com.av50200264.community.dto.GithubUser;
import com.av50200264.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {

    @Autowired
    private Githubprovider githubprovider;

    @Value("${github.Client.id}")
    private String Client_id;
    @Value("${github.Client.secret}")
    private String Client_secret;
    @Value("${github.Redirect_uri}")
    private String Redirect_uri;

    @RequestMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githubprovider.getAccessToken(accessTokenDTO);
        GithubUser user = githubprovider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
