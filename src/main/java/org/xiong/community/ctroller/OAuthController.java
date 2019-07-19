package org.xiong.community.ctroller;

import org.xiong.community.dto.AccessTokenDTO;
import org.xiong.community.dto.GithubUser;
import org.xiong.community.entity.User;
import org.xiong.community.mapper.UserMapper;
import org.xiong.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                           HttpServletRequest request,
                           HttpServletResponse response) {
        //github返回的信息
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(Client_id);
        accessTokenDTO.setClient_secret(Client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(Redirect_uri);
        accessTokenDTO.setState(state);
        //返回用户github令牌
        String accessToken = githubprovider.getAccessToken(accessTokenDTO);
        //使用github令牌获取用户信息
        GithubUser githubUser = githubprovider.getUser(accessToken);
        if(githubUser!=null){
            //登录成功
            request.getSession().setAttribute("user",githubUser);
            String token = UUID.randomUUID().toString();
            //保存github信息到用户实体并存入数据库
            User user = new User();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setBio(githubUser.getBio());
            user.setAvatar(githubUser.getAvatar());
            user.setGmt_creat(System.currentTimeMillis());
            user.setGmt_modifid(user.getGmt_creat());
            //判断用户是否已经在数据库中，是则更新数据库信息,否则就插入信息
            if(userMapper.isExistUser(String.valueOf(githubUser.getId()))){
//                userMapper.updateUserInfo(user);
            }else {
                userMapper.insert(user);
            }
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }
}
