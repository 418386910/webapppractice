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
            map.put("msg2","welcome back! " + loginUser + "~~");
            return "login";
        /*User getUser = userMapper.getUser(username);
        if (getUser != null) {
            User loginUser = userMapper.login(username, password);
            map.put("msg2", "welcome back! " + loginUser + "~~");
            return "login";
        }else {
            map.put("msg2","the password is wrong!");              // 密码错误怎么写？？
            return "login";
        }*/

    }

    // 删除用户
    @RequestMapping("/deleteUser")
    public String deleteUser(HttpServletRequest request, Map<String, Object> map) {
        String username = request.getParameter("username");
        User getUser = userMapper.getUser(username);
        if (getUser != null) {
            userMapper.deleteUser(username);
            map.put("msg3","the user has been deleted!");
            return "login";
        }else {
            map.put("msg3","the user is not exist!");
            return "login";
        }
    }

    // 更新用户
    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request,Map<String, Object> map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User getUser = userMapper.getUser(username);
        if (getUser != null) {
            userMapper.updateUser(username,password);
            map.put("msg4","the user has been updated!");
            return "login";
        }else {
            map.put("msg4","the user is not exist!");
            return "login";
        }
    }

}
