package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.OaUser;
import com.mapper.OaUserMapper;
import com.service.OaUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OaUserServiceImpl extends ServiceImpl<OaUserMapper, OaUser> implements OaUserService {
    @Autowired
    OaUserMapper oaUserMapper;

    @Override
    public String getnaba() {
        LambdaQueryWrapper<OaUser> wrapper = new LambdaQueryWrapper<OaUser>();


        return "ssssssssss";
    }

    @Override
    public OaUser loginUser(String name, String password) {
        LambdaQueryWrapper<OaUser> wrapper = new LambdaQueryWrapper<OaUser>();
        wrapper.eq(OaUser::getUserName, name)
                .eq(OaUser::getUserPassword, password);
        OaUser user =new OaUser();
        if(StringUtils.isNotBlank(name)&& StringUtils.isNotBlank(password)){
         user = oaUserMapper.selectOne(wrapper);
        }
        return user;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("ss", "ssssada");
        System.out.println(map.toString());

    }
}


