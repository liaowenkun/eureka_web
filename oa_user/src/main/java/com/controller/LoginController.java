package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.OaUser;
import com.httputil.HttpApiUtils;
import com.service.OaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private OaUserService oaUserService;


    @GetMapping ("userLogin")
    @ResponseBody
    public JSONObject userLogin(String js_code, HttpSession session) {
        String mcUrl="https://api.weixin.qq.com/sns/jscode2session";
        JSONObject jsonObject=new JSONObject();
        String appid="wx3d658e648de7bc5c";
        String secret="164ae6ded0d7e077816f75386f50fc5a";
        String grant_type="authorization_code";

        jsonObject.put("appid",appid);
        jsonObject.put("secret",secret);
        jsonObject.put("js_code",js_code);
        jsonObject.put("grant_type",grant_type);
        //获取openid
        JSONObject mcget = HttpApiUtils.get(mcUrl, jsonObject);

        System.out.println(mcget);


        session.setAttribute("openid",mcget.get("openid"));
        System.out.println(session.getAttribute("openid"));

        return mcget;
    }


    @GetMapping ("userLogin1")
    @ResponseBody
    public JSONObject userLogin1(String js_code, HttpSession session) {
        OaUser user = oaUserService.loginUser("2", "");
        System.out.println(user.getUserName());

        return new JSONObject() ;
    }


    @GetMapping ("userAdminLogin")
    @ResponseBody
    public JSONObject userAdminLogin(String userName,String password) {
        OaUser user = oaUserService.loginUser("2", "");
        System.out.println(user.getUserName());

        return new JSONObject() ;
    }

}
