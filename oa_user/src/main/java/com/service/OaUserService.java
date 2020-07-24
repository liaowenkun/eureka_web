package com.service;


import com.entity.OaUser;

public interface OaUserService {
    String getnaba();

    OaUser loginUser(String name, String password);

}


