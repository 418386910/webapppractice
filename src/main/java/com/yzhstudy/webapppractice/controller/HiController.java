package com.yzhstudy.webapppractice.controller;

import com.yzhstudy.webapppractice.mapper.UserMapper;
import com.yzhstudy.webapppractice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HiController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/register")
    public String registerInit() {
        return "/register";
    }

    // 注册
    @RequestMapping("/register")
    public String register(HttpServletRequest request,Map<String,Object> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        /**
         * 前端页面的数据获取
         */
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User user1 = userMapper.getUser(username);
        if (user1 != null) {
            map.put("msg1","the user has been registered,please register again!");
            return "register";
        }else {
            userMapper.addUser(user);
        }
        return "login";
    }

    // 验证用户名是否被注册
    @RequestMapping("/getUser")
    public String getUser(HttpServletRequest request, Map<String, Object> map) {
        String username = request.getParameter("username");
        User user = userMapper.getUser(username);
        if (user != null) {
            map.put("msg","the user has been registered!");
            return "register";
        }else {
            map.put("msg","the user has not been registered!");
            return "register";
        }
    }

    // 登录
    @RequestMapping("/login")
    public String login(HttpServletRequest request,Map<String, Object> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User loginUser = userMapper.login(username, password);
        System.out.println(loginUser);
        map.put("msg2","the user " +loginUser+ "login.");
        return "login";

    }

}
