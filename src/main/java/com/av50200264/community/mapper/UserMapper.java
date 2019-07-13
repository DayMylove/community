package com.av50200264.community.mapper;


import com.av50200264.community.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Insert("insert into user(id,account_id,name,token,gmt_creat,gmt_modifid) " +
            "values(#{id},#{account_id},#{name},#{token},#{gmt_creat},#{gmt_modifid})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
