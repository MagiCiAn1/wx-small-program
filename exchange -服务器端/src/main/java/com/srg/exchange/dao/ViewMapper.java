package com.srg.exchange.dao;

import com.srg.exchange.pojo.View;
import com.srg.exchange.vo.ViewVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(View record);

    int insertSelective(View record);

    View selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(View record);

    int updateByPrimaryKey(View record);

    List<ViewVo> selectList(@Param(value = "start") Integer start,@Param(value = "end") Integer end);

    int selectIdByUserId(String userId);

    ViewVo selectViewVoByViewId(Integer viewId);

    List<ViewVo> selectListByClassify(@Param(value = "classify") Integer classify,@Param(value = "start") Integer start,@Param(value = "end") Integer end);

    List<ViewVo> selectListByUserId(@Param(value = "userId") String userId,@Param(value = "start") Integer start,@Param(value = "end") Integer end);

    int updateDelByViewId(Integer viewId);
}