package com.jiyun.allproject.controller;

import com.jiyun.allproject.dao.UserDao;
import com.jiyun.allproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpServletRequest request){
        User user = userDao.findByUsernameAndPassword(username, password);
        if(user!=null){
            request.getSession().setAttribute("user",user);
            return "redirect:/empl/findAll";
        }else{
            request.setAttribute("msg","账号或密码错误！");
            return "login";
        }

    }

    @RequestMapping("/toReg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/yz")
    public void yz(String username, HttpServletResponse response){
        User user = userDao.findByUsername(username);

            try {
                if(user==null){
                    response.getWriter().write("0");
                }else{
                    response.getWriter().write("1");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    @RequestMapping("/reg")
    public String reg(User user){
        userDao.save(user);
        return "login";
    }

}
