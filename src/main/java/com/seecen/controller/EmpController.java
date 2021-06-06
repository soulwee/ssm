package com.seecen.controller;

import com.seecen.service.EmpService;
import com.seecen.service.Impl.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class EmpController {
    @Autowired
    private EmpService empService;
    @RequestMapping("emp")
    public String queryAllEmp(Map map){
        map.put("empList",empService.queryAllEmp());
        return "index";
    }
}
