package com.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.entity.OaHome;
import com.httputil.HttpApiUtils;
import com.service.OaCheckWorkService;
import com.service.OaHomeService;
import com.service.OaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
public class OaUserController {

    @Autowired
    private OaCheckWorkService oaCheckWorkService;


    @GetMapping("/user")
    @ResponseBody
    public JSONObject user(@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude) throws ParseException {
        System.out.println("ssss");

        return null;//oaCheckWorkService.daka(longitude,latitude);
    }

    public static void main(String[] args) {
       // this.user();
      String  latitude= "23.125373";
      String  longitude="113.333829";
      //  this.user(latitude,longitude);

    }
}
