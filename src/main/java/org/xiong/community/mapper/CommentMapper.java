package org.xiong.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.xiong.community.entity.Comment;

@Mapper
@Component
public interface CommentMapper {

    @Insert("insert into comment(id,parent_Id,type,gmt_creat,gmt_modifid,like_count,content,commentor)" +
            " values(#{id},#{parentId},#{type},#{gmtCreat},#{gmtModifid},#{likeCount},#{content},#{commentor})")
    void insert(Comment comment);

    @Select("select * from comment where id=#{parentId}")
    Comment selectByParentId(@Param("parentId") Integer parentId);
}
