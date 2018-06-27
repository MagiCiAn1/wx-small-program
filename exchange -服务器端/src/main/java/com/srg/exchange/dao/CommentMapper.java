package com.srg.exchange.dao;

import com.srg.exchange.pojo.Comment;
import com.srg.exchange.vo.CommentVo;
import com.srg.exchange.vo.ViewCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<CommentVo> selectVoListByViewId(String viewId);

    List<ViewCommentVo> listViewCommentByUserId(@Param(value = "userId") String userId,
                                                @Param(value = "start")Integer start,
                                                @Param(value = "end") Integer end);

    int updateDelById(Integer id);
}