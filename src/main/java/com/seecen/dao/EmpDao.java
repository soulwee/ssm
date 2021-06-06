package com.seecen.dao;

import com.seecen.entity.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpDao {
    List<Emp> queryAllEmp();
}
