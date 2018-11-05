package com.eim.controller;

import com.eim.util.RedisSynUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/11/5.
 */
@Controller
public class TestController {

    @Autowired
    private RedisSynUtil redisSynUtil;

    @ResponseBody
    @RequestMapping("/test.do")
    public String  test(){

        //设置延迟时间
        long l = System.currentTimeMillis() + Long.valueOf(10);

        //上锁
        if(!redisSynUtil.lock("1",String.valueOf(l))){
            //排队中
            System.out.println("抛出异常，人太多了，换个姿势再试试");
        }

        //业务逻辑
        System.out.println("呵呵");

        //解锁
        redisSynUtil.unlock("1",String.valueOf(l));

        return  "成功";
    }
}
