package com.srg.exchange.service;

import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.pojo.View;
import com.srg.exchange.vo.ViewShowVo;
import com.srg.exchange.vo.ViewVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/5/7
 */
public interface IViewService {

    List<ViewVo> index(Integer pageNum);

    Integer insert(View view);

    String upload(MultipartFile file, String path);

    ServerResponse<String> insertPhoto(Integer viewId,String url);

    ServerResponse<ViewVo> detail(Integer viewId);

    List<ViewShowVo> assembleViewShowVo (List<ViewVo> viewVoList);

    List<ViewVo> listByClassify(Integer classify, Integer pageNum);

    List<ViewVo> listByUserId(String userId, Integer pageNum);

    boolean isPublish(Integer viewId,String userId);

    ServerResponse<String> deleteView(Integer viewId);
}
