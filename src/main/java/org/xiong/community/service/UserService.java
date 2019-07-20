package org.xiong.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiong.community.entity.User;
import org.xiong.community.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void creatOrUpdateUser(User user) {
        //判断用户是否已经在数据库中，是则更新数据库信息,否则就插入信息
        if(userMapper.isExistUser(String.valueOf(user.getAccount_id()))!=0){
            userMapper.updateUserInfo(user);
        }else {
            user.setGmt_modifid(System.currentTimeMillis());
            userMapper.insert(user);
        }
    }
}
