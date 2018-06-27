package com.srg.exchange.service;

import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.pojo.Comment;
import com.srg.exchange.vo.CommentVo;
import com.srg.exchange.vo.ViewCommentShowVo;
import com.srg.exchange.vo.ViewCommentVo;

import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/5/30
 */
public interface ICommentService {

    ServerResponse<String> insert(Comment comment);

    ServerResponse<List<CommentVo>> list(String viewId);

    ServerResponse<List<ViewCommentVo>> listViewCommentByUserId(String userId, Integer pageNum);

    List<ViewCommentShowVo> assembleViewCommentVo(List<ViewCommentVo> viewCommentVos);

    boolean isPublish(String userId,Integer commentId);

    ServerResponse<String> delete(Integer commentId);

}
