package org.xiong.community.mapper;

import org.xiong.community.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    @Insert("insert into question(id,title,description,gmt_creat,gmt_modifid,creator,comment_count,view_count,like_count,tags) " +
            "values(#{id},#{title},#{description},#{gmt_creat},#{gmt_modifid},#{creator},#{comment_count},#{view_count},#{like_count},#{tags})")
    void creat(Question question);

    @Select("select * from question")
    List<Question> getIndexAllList();
}
