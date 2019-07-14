package org.xiong.community.provider;

import com.alibaba.fastjson.JSON;
import org.xiong.community.dto.AccessTokenDTO;
import org.xiong.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录时需要提供的用户信息
 */
@Component
public class Githubprovider {

    /**
     * 获得返回服务器回调的用户token
     *
     * @param accessTokenDTO
     * @return 用户token令牌
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String[] split = str.split("&");
            String token=split[0].split("=")[1];
//            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户token令牌访问github得到的用户信息
     *
     * @return 用户信息json
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String userJson = response.body().string();
            GithubUser githubUser = JSON.parseObject(userJson, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
