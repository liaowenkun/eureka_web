package com.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.entity.OaHome;
import com.httputil.HttpApiUtils;
import com.service.OaCheckWorkService;
import com.service.OaHomeService;
import com.service.OaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;

@Controller
public class OaUserController {

    @Autowired
    private OaCheckWorkService oaCheckWorkService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/user")
    @ResponseBody
    public JSONObject user(@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude) throws ParseException {
        System.out.println("ssss");

        redisTemplate.opsForValue().set("user", "世界你好");
        Object user1 = redisTemplate.opsForValue().get("user");
        System.out.println(user1);
        String lockkey="";

        try {

       //设置分布式锁  10秒后失效
        Boolean aBoolean = redisTemplate.opsForValue().setIfPresent(lockkey, "value", 10, TimeUnit.SECONDS);
        if (!aBoolean) {
            //return "返回系统繁忙请稍后再试！";
        }
        int stock=Integer.parseInt(redisTemplate.opsForValue().get("stock")+"");
        if (stock > 0) {
          int  rustock=stock-1;
            redisTemplate.opsForValue().set("stock",rustock+"");
            System.out.println("扣减库存成功，剩余库存："+rustock);
        }else {


        }
       }finally {
            //
        //  if ()
           // 释放redis锁
            redisTemplate.delete(lockkey);
        }

        return null;//oaCheckWorkService.daka(longitude,latitude);
    }

    public static void main(String[] args) {
       // this.user();
      String  latitude= "23.125373";
      String  longitude="113.333829";
      //  this.user(latitude,longitude);

    }
}
