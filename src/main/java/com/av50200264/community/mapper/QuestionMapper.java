package com.av50200264.community.mapper;

import com.av50200264.community.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface QuestionMapper {

    @Insert("insert into question(id,title,description,gmt_creat,gmt_modifid,creator,comment_count,view_count,like_count,tags) " +
            "values(#{id},#{title},#{description},#{gmt_creat},#{gmt_modifid},#{creator},#{comment_count},#{view_count},#{like_count},#{tags})")
    public void creat(Question question);
}
