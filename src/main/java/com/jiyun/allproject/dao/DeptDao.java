package com.jiyun.allproject.dao;

import com.jiyun.allproject.pojo.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptDao extends JpaRepository<Dept,Integer> {
}
