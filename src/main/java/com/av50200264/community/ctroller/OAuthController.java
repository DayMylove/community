package com.av50200264.community.ctroller;

import com.av50200264.community.dto.AccessTokenDTO;
import com.av50200264.community.dto.GithubUser;
import com.av50200264.community.entity.User;
import com.av50200264.community.mapper.UserMapper;
import com.av50200264.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class OAuthController {

    @Autowired
    private Githubprovider githubprovider;
    @Autowired
    private UserMapper userMapper;

    @Value("${github.Client.id}")
    private String Client_id;
    @Value("${github.Client.secret}")
    private String Client_secret;
    @Value("${github.Redirect_uri}")
    private String Redirect_uri;

    @RequestMapping("/callback")
    public String Callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githubprovider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubprovider.getUser(accessToken);
        if(githubUser!=null){
            //登录成功
            request.getSession().setAttribute("user",githubUser);
            User user=new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmt_creat(System.currentTimeMillis());
            user.setGmt_modifid(user.getGmt_creat());
            userMapper.insert(user);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}
