package com.srg.exchange.controller;

import com.srg.exchange.common.Const;
import com.srg.exchange.common.ServerResponse;
import com.srg.exchange.pojo.User;
import com.srg.exchange.service.IUserService;
import com.srg.exchange.util.CommonUtil;
import com.srg.exchange.util.WXOpenidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by srg
 *
 * @date 2018/5/21
 */
@Controller
@RequestMapping("/user/")
public class userController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(User user, HttpServletRequest request){
        String openId = WXOpenidUtil.oauth2GetOpenid(user.getUserId());
        user.setUserId(openId);
        user.setUsername(CommonUtil.encode(user.getUsername()));
        ServerResponse<User> response = iUserService.login(user);
        HttpSession session = request.getSession();
        if(response.isSuccess()){
            User user1 = response.getData();
            user1.setUsername(CommonUtil.decode(user1.getUsername()));
            session.setAttribute(Const.CURRENT_USER,user1);
            System.out.println("login:"+user1.getUsername());

            if(user1.getAuth() != null){
                System.out.println("login.do:" + user1.getAuth());
            } else {
                System.out.println("login hello wold");
            }

            return ServerResponse.createBySuccess(session.getId(),user1);
        }
        return ServerResponse.createByErrorMessage("登录未成功");
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> detail(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        if(user.getAuth() != null){
            System.out.println("detail.do:" + user.getAuth());
        } else {
            System.out.println("hello wold");
        }
        return ServerResponse.createByErrorMessage("发生故障");
    }

    @RequestMapping(value = "auth.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> auth(User userNew,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("当前未登录");
        }
        if(!CommonUtil.isNotBlank(userNew.getName()) || !CommonUtil.isNotBlank(userNew.getSchoolCardNo())){
            return ServerResponse.createByErrorMessage("您未进行实名认证");
        }
        user.setUsername(CommonUtil.encode(user.getUsername()));
        user.setName(userNew.getName());
        user.setSchool(userNew.getSchool());
        user.setSchoolCardNo(userNew.getSchoolCardNo());
        user.setPhoneNumber(userNew.getPhoneNumber());
        user.setCity(userNew.getCity());
        user.setSex(userNew.getSex());
        user.setEmail(userNew.getEmail());
        ServerResponse<User> response = iUserService.auth(user);
        if(response.isSuccess()){
            User user1 = response.getData();
            user1.setUsername(CommonUtil.decode(user1.getUsername()));
            session.setAttribute(Const.CURRENT_USER,user1);
            return ServerResponse.createBySuccess(user1);
        }
        return ServerResponse.createByErrorMessage("认证失败");
    }

}
