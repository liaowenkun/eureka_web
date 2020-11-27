package com.service.impl;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 利用监听器统计在线人数
 *
 * */
@WebListener  //监听器注解
public class LogCostFilter implements HttpSessionListener {

     /**
      * 定义在线人数
      *
      * */

     private Integer sumRS=0;


    /**
     * 统计在线人数
     * 当创建session时 执行这个方法
     * */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
       //当用户登录时加1
        sumRS++;
        //当用户登录时将在线人数设置当Session作用域
        se.getSession().getServletContext().setAttribute("sumRS",sumRS);
        System.out.println("创建session");

    }

    /**
     * 统计在线人数
     * 当销毁session时 执行这个方法
     * */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /*当用户退出时 销毁session并减去退出的人数*/
        sumRS--;
        /*将减去的人数重新设置到Session作用域中*/
        se.getSession().getServletContext().setAttribute("sumRS",sumRS);
        System.out.println("销毁session");

    }

}
