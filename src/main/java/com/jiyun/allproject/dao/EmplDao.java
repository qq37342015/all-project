package com.jiyun.allproject.dao;

import com.jiyun.allproject.pojo.Empl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmplDao extends JpaRepository<Empl,Integer> {

    // 分页，模糊查询ename
    Page<Empl> findAllByEnameLike(Pageable pageable,String ename);

}
