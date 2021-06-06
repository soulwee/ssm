package com.seecen.service.Impl;

import com.seecen.dao.EmpDao;
import com.seecen.entity.Emp;
import com.seecen.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;

    public List<Emp> queryAllEmp() {
        return empDao.queryAllEmp();
    }
}
