package com.service;


import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;

public interface OaCheckWorkService {

    JSONObject daka(String longitude, String latitude) throws ParseException;


}

