package com.srg.exchange.service.Impl;

import com.srg.exchange.common.Const;
import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.dao.CommentMapper;
import com.srg.exchange.dao.PhotoMapper;
import com.srg.exchange.pojo.Comment;
import com.srg.exchange.service.ICommentService;
import com.srg.exchange.vo.CommentVo;
import com.srg.exchange.vo.ViewCommentShowVo;
import com.srg.exchange.vo.ViewCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/5/30
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PhotoMapper photoMapper;

    public ServerResponse<String> insert(Comment comment){
        int result = commentMapper.insert(comment);
        if(result != 0){
            return ServerResponse.createBySuccess("评论成功");
        }
        return ServerResponse.createByErrorMessage("评论失败");
    }

    public ServerResponse<List<CommentVo>> list(String viewId){
        List<CommentVo> commentVoList = commentMapper.selectVoListByViewId(viewId);
        if(commentVoList != null){
            return ServerResponse.createBySuccess(commentVoList);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    public ServerResponse<List<ViewCommentVo>> listViewCommentByUserId(String userId, Integer pageNum){
        int start = pageNum * Const.PAGE_SIZE;
        System.out.println(start);
        int end = (pageNum + 1) * Const.PAGE_SIZE - 1;
        List<ViewCommentVo> viewCommentVos = commentMapper.listViewCommentByUserId(userId, start, end);
        if(viewCommentVos != null){
            return ServerResponse.createBySuccess(viewCommentVos);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    public List<ViewCommentShowVo> assembleViewCommentVo(List<ViewCommentVo> viewCommentVos){
        List<ViewCommentShowVo> viewCommentShowVos = new ArrayList<>();
        for(ViewCommentVo viewCommentVo : viewCommentVos){
            ViewCommentShowVo viewCommentShowVo = new ViewCommentShowVo(viewCommentVo);
            List<String> stringList = photoMapper.selectList(viewCommentVo.getViewId());
            viewCommentShowVo.setPhotoUrl(stringList);
            viewCommentShowVos.add(viewCommentShowVo);
        }
        return viewCommentShowVos;
    }

    public boolean isPublish(String userId,Integer commentId){
        boolean flag = false;
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        if(userId.equals(comment.getUserId())){
            flag = true;
        }
        return flag;
    }

    public ServerResponse<String> delete(Integer commentId){
        int result = commentMapper.updateDelById(commentId);
        if(result > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }
}
