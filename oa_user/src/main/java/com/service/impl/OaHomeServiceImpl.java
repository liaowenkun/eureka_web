package com.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.httputil.HttpApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.OaHomeMapper;
import com.entity.OaHome;
import com.service.OaHomeService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OaHomeServiceImpl extends ServiceImpl<OaHomeMapper, OaHome> implements OaHomeService {


    @Autowired
    private OaHomeMapper oaHomeMapper;


    @Override
    public OaHome findByOaHome() {
        LambdaQueryWrapper<OaHome> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OaHome::getId, 1);
        OaHome oaHome = oaHomeMapper.selectOne(wrapper);
        return oaHome;
    }


}




