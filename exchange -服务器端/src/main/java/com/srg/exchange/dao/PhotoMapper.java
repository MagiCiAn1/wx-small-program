package com.srg.exchange.dao;

import com.srg.exchange.pojo.Photo;
import com.srg.exchange.vo.PhotoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PhotoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKey(Photo record);

    List<String> selectList(Integer viewId);
}