package org.xiong.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.xiong.community.entity.Comment;

@Mapper
@Component
public interface CommentMapper {

    @Insert("insert into comment(id,parent_Id,type,gmt_creat,gmt_modifid,like_count,content,commentor)" +
            " values(#{id},#{parentId},#{type},#{gmtCreat},#{gmtModifid},#{likeCount},#{content},#{commentor})")
    void insert(Comment comment);
}
