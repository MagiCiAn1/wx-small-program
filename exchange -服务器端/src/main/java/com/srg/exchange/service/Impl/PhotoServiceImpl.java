package com.srg.exchange.service.Impl;

import com.srg.exchange.dao.PhotoMapper;
import com.srg.exchange.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/5/31
 */
@Service
public class PhotoServiceImpl implements IPhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    public List<String> selectList(Integer viewId){
        return photoMapper.selectList(viewId);
    }
}
