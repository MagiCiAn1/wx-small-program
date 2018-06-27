package com.srg.exchange.controller;

import com.srg.exchange.common.Const;
import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.pojo.Comment;
import com.srg.exchange.pojo.User;
import com.srg.exchange.service.ICommentService;
import com.srg.exchange.util.CommonUtil;
import com.srg.exchange.vo.CommentVo;
import com.srg.exchange.vo.ViewCommentShowVo;
import com.srg.exchange.vo.ViewCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by srg
 *
 * @date 2018/5/30
 */
@Controller
@RequestMapping("/comment/")
public class commentController {

    @Autowired
    private ICommentService iCommentService;

    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insert(Comment comment, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(user.getAuth() == 0){
            return ServerResponse.createByErrorMessage("您当前未认证，还不能评论");
        }
        String userId = user.getUserId();
        comment.setUserId(userId);
        comment.setContent(CommonUtil.encode(comment.getContent()));
        return iCommentService.insert(comment);
    }

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    ServerResponse<List<CommentVo>> list(String viewId,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iCommentService.list(viewId);
    }

    @RequestMapping(value = "list_comment_about_me.do", method = RequestMethod.POST)
    @ResponseBody
    ServerResponse<List<ViewCommentShowVo>> listCommentAboutMe(Integer pageNum, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        //查询到基本view和comment的信息
        ServerResponse<List<ViewCommentVo>> response = iCommentService.listViewCommentByUserId(user.getUserId(), pageNum);
        if(!response.isSuccess()){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        //根据基本信息组装vo（加上photourl）
        List<ViewCommentShowVo> viewCommentShowVos = iCommentService.assembleViewCommentVo(response.getData());
        if(viewCommentShowVos != null){
            return ServerResponse.createBySuccess(viewCommentShowVos);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delete(Integer commentId,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        boolean isPublish = iCommentService.isPublish(user.getUserId(),commentId);
        if(!isPublish){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return iCommentService.delete(commentId);
    }
}
