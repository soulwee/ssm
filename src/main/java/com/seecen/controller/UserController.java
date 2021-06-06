package com.seecen.controller;

import com.seecen.entity.User;
import com.seecen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("allUser")
    public String queryAllUser(Map map){
        map.put("userList",userService.queryAllUser());
        return "index";
    }
    @RequestMapping(value = "/test/{id}/{username}",method = RequestMethod.GET)
    public String test(@PathVariable("id")int userId,@PathVariable("username")String name){
        System.out.println(userId+","+name);
        return "index";
    }
    @RequestMapping("/ajax")
    @ResponseBody
    public Map map(){
        Map map=new HashMap();
        map.put("username","admin");
        map.put("age",22);
        return map;
    }
    @RequestMapping("/ajax2")
    @ResponseBody
    public User map2(){
        User user=new User();
        user.setUserName("egg");
        return user;
    }

    @RequestMapping("upload")
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam(value = "file",required = false) CommonsMultipartFile file)
    {

        System.out.println(123);
        String path =request.getRealPath("/upload");
        String fileName=file.getOriginalFilename();/*得到文件的名字*/
        try {
            FileOutputStream outputStream=new FileOutputStream(new File(path,fileName));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
            return "成功";
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "失败";

    }

}
