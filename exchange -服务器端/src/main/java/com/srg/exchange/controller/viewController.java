package com.srg.exchange.controller;

import com.srg.exchange.common.Const;
import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.pojo.User;
import com.srg.exchange.pojo.View;
import com.srg.exchange.service.IPhotoService;
import com.srg.exchange.service.IViewService;
import com.srg.exchange.util.CommonUtil;
import com.srg.exchange.vo.ViewShowVo;
import com.srg.exchange.vo.ViewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by srg
 *
 * @date 2018/5/7
 */
@Controller
@RequestMapping("/view/")
public class viewController {

    @Autowired
    private IViewService iViewService;

    @Autowired
    private IPhotoService iPhotoService;

    @RequestMapping(value = "index.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<ViewShowVo>> index(Integer pageNum,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        System.out.println("index:"+user.getUsername());
        if(user == null){
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        List<ViewVo> viewVoList = iViewService.index(pageNum);
        if(viewVoList == null){
            return ServerResponse.createByErrorMessage("初始化失败");
        }
        List<ViewShowVo> viewShowVoList = iViewService.assembleViewShowVo(viewVoList);
        return ServerResponse.createBySuccess(viewShowVoList);
    }

    @RequestMapping(value = "insert.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Integer> insert(View view, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        if(user.getAuth() == 0){
            return ServerResponse.createByErrorMessage("您当前未认证，还不能发表说说");
        }
        view.setUserId(user.getUserId());
        view.setContent(CommonUtil.encode(view.getContent()));
        Integer viewId = iViewService.insert(view);
        if(viewId != -1){
            return ServerResponse.createBySuccess(viewId);
        }
        return ServerResponse.createByErrorMessage("上传失败");
    }

    @RequestMapping(value = "upload.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> upload(@RequestParam(value = "file",required = false) MultipartFile file,
                                         HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        if(user.getAuth() == 0){
            return ServerResponse.createByErrorMessage("您当前未认证，还不能上传图片");
        }
//        String path = request.getSession().getServletContext().getRealPath("upload");
        String path = "C:\\upload";
//        System.out.println(path);
        String url = iViewService.upload(file,path);
        Integer viewId = Integer.valueOf(request.getParameter("viewId"));
        return iViewService.insertPhoto(viewId,url);
    }

    @RequestMapping(value = "detail.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<ViewShowVo> detail(Integer viewId,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        ServerResponse response = iViewService.detail(viewId);
        if(response.isSuccess()){
            ViewVo viewVo = (ViewVo)response.getData();
            ViewShowVo viewShowVo = new ViewShowVo(viewVo);
            List<String> stringList = iPhotoService.selectList(viewId);
            viewShowVo.setPhotoUrl(stringList);
            return ServerResponse.createBySuccess(viewShowVo);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    @RequestMapping(value = "list_by_classify.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<ViewShowVo>> listByClassify(Integer classify,Integer pageNum,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        List<ViewVo> viewVoList = iViewService.listByClassify(classify,pageNum);
        if(viewVoList == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        List<ViewShowVo> viewShowVoList = iViewService.assembleViewShowVo(viewVoList);
        return ServerResponse.createBySuccess(viewShowVoList);
    }

    @RequestMapping(value = "list_view_about_me.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<ViewShowVo>> listViewAboutMe(Integer pageNum, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        List<ViewVo> viewVoList = iViewService.listByUserId(user.getUserId(),pageNum);
        if(viewVoList == null){
            return ServerResponse.createByErrorMessage("查询失败");
        }
        List<ViewShowVo> viewShowVoList = iViewService.assembleViewShowVo(viewVoList);
        return ServerResponse.createBySuccess(viewShowVoList);
    }

    @RequestMapping(value = "delete_view.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteView(Integer viewId,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        if(!iViewService.isPublish(viewId,user.getUserId())){
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return iViewService.deleteView(viewId);
    }
}
