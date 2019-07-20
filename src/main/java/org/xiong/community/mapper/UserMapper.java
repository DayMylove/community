package org.xiong.community.mapper;


import org.xiong.community.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(id,account_id,name,token,gmt_creat,gmt_modifid,avatar) " +
            "values(#{id},#{account_id},#{name},#{token},#{gmt_creat},#{gmt_modifid},#{avatar})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findUserbyId(@Param("id") int id);

    @Select("select count(*) from user where account_id=#{id}")
    int isExistUser(@Param("id") String id);

    @Update("update user set name=#{name},token=#{token}," +
            "gmt_modifid=#{gmt_modifid},avatar=#{avatar} where account_id=#{account_id}")
    void updateUserInfo(User user);
}
