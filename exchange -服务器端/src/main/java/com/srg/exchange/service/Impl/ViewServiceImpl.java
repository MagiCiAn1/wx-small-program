package com.srg.exchange.service.Impl;

import com.google.common.collect.Lists;
import com.srg.exchange.common.Const;
import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.common.storage.QiniuStorage;
import com.srg.exchange.dao.PhotoMapper;
import com.srg.exchange.dao.ViewMapper;
import com.srg.exchange.pojo.Photo;
import com.srg.exchange.pojo.View;
import com.srg.exchange.service.IPhotoService;
import com.srg.exchange.service.IViewService;
import com.srg.exchange.util.CommonUtil;
import com.srg.exchange.util.FileUtil;
import com.srg.exchange.vo.ViewShowVo;
import com.srg.exchange.vo.ViewVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by srg
 *
 * @date 2018/5/7
 */
@Service
public class ViewServiceImpl implements IViewService {

    private Logger logger = LoggerFactory.getLogger(ViewServiceImpl.class);

    @Autowired
    private ViewMapper viewMapper;

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private IPhotoService iPhotoService;

    @Override
    public List<ViewVo> index(Integer pageNum) {
        int start = pageNum * Const.PAGE_SIZE;
        System.out.println(start);
        int end = (pageNum + 1) * Const.PAGE_SIZE - 1;
        List<ViewVo> viewList = viewMapper.selectList(start,end);
        return viewList;
    }

    public Integer insert(View view){
        int resultCount = viewMapper.insert(view);
        if(resultCount > 0){
            int id = viewMapper.selectIdByUserId(view.getUserId());
            return id;
        }
        return -1;
    }

    public ServerResponse<String> insertPhoto(Integer viewId,String url){
        Photo photo = new Photo(null,viewId,url,null,null);
        int result = photoMapper.insert(photo);
        if(result > 0)
            return ServerResponse.createBySuccessMessage("发表成功");
        return ServerResponse.createByErrorMessage("发表失败");
    }

    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        String key;
        try {
            file.transferTo(targetFile);
            //文件上传成功
            byte[] buff = FileUtil.getFileBytes(targetFile);
            key = QiniuStorage.uploadImage(buff);

            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return QiniuStorage.getUrl(key);
    }

    public ServerResponse<ViewVo> detail(Integer viewId){
        ViewVo viewVo =  viewMapper.selectViewVoByViewId(viewId);
        if(viewVo == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(viewVo);
    }

    public List<ViewShowVo> assembleViewShowVo (List<ViewVo> viewVoList){
        List<ViewShowVo> viewShowVoList = new ArrayList<>();
        for(ViewVo item : viewVoList){
            ViewShowVo viewShowVo = new ViewShowVo(item);
            List<String> stringList = iPhotoService.selectList(viewShowVo.getId());
            viewShowVo.setPhotoUrl(stringList);
            viewShowVoList.add(viewShowVo);
        }
        return viewShowVoList;
    }

    public List<ViewVo> listByClassify(Integer classify, Integer pageNum) {
        int start = pageNum * Const.PAGE_SIZE;
        System.out.println(start);
        int end = (pageNum + 1) * Const.PAGE_SIZE - 1;
        List<ViewVo> viewList = viewMapper.selectListByClassify(classify,start,end);
        return viewList;
    }

    public List<ViewVo> listByUserId(String userId, Integer pageNum){
        int start = pageNum * Const.PAGE_SIZE;
        System.out.println(start);
        int end = (pageNum + 1) * Const.PAGE_SIZE - 1;
        List<ViewVo> viewList = viewMapper.selectListByUserId(userId,start,end);
        return viewList;
    }

    public boolean isPublish(Integer viewId,String userId){
        boolean flag = false;
        View view = viewMapper.selectByPrimaryKey(viewId);
        if(view.getUserId().equals(userId)){
            flag = true;
        }
        return flag;
    }

    public ServerResponse<String> deleteView(Integer viewId){
        int result = viewMapper.updateDelByViewId(viewId);
        if(result == 0){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccess("删除成功");
    }
}
