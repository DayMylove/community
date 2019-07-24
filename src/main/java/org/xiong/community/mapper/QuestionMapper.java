package org.xiong.community.mapper;

import org.apache.ibatis.annotations.*;
import org.xiong.community.entity.Question;
import org.springframework.stereotype.Component;
import org.xiong.community.entity.User;

import java.util.List;

@Mapper
@Component
public interface QuestionMapper {

    @Insert("insert into question(id,title,description,gmt_creat,gmt_modifid,creator,comment_count,view_count,like_count,tags) " +
            "values(#{id},#{title},#{description},#{gmt_creat},#{gmt_modifid},#{creator},#{comment_count},#{view_count},#{like_count},#{tags})")
    void creat(Question question);

    @Select("select * from question limit #{page} , #{size}")
    List<Question> getIndexAllList(@Param("page") Integer page,@Param("size") int size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{usrId} limit #{page} , #{size}")
    List<Question> list(@Param("usrId") Integer usrId,@Param("page") Integer page,@Param("size") Integer size);

    @Select("select * from question where id=#{questionId}")
    Question getByid(Integer questionId);

    @Select("select * from user where id = ( select creator from question where id=#{questionId} )")
    User getUserByQuestion(Integer questionId);

    @Select("select count(*) from question where id=#{id}")
    int isExist(int id);

    @Update("update question set title=#{title},description=#{description},tags=#{tags},gmt_modifid=#{gmt_modifid} where id=#{id}")
    int update(Question question);

    @Update("update question set view_count=view_count+1 where id=#{questionId}")
    void addView(Integer questionId);

    @Update("update question set comment_count=comment_count+1 where id=#{id}")
    void increaseCommentCount(Question question);
}
