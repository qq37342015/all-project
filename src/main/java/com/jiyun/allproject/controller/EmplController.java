package com.jiyun.allproject.controller;

import com.jiyun.allproject.dao.DeptDao;
import com.jiyun.allproject.dao.EmplDao;
import com.jiyun.allproject.pojo.Dept;
import com.jiyun.allproject.pojo.Empl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/empl")
public class EmplController {

    @Autowired
    EmplDao emplDao;
    @Autowired
    DeptDao deptDao;

    /**
     * page：当前页码
     * tr：每页显示的数量
     * ename：模糊查询的时候使用的
     */
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "tr", defaultValue = "2") Integer tr,
                          @RequestParam(value = "ename", defaultValue = "") String ename,
                          Model model) {
        // 从第0页往上一页走，-1。只要当前的page传过来之后小于0就让他等于0
//        page = page<0? 0:page;
        if (page < 0) {
            page = 0;
        }

        Pageable pageable = new PageRequest(page, tr);

        Page<Empl> pages = emplDao.findAllByEnameLike(pageable, "%" + ename + "%");

        model.addAttribute("pages", pages);
        model.addAttribute("ename", ename);

        return "show";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        // 查询部门
        List<Dept> depts = deptDao.findAll();
        model.addAttribute("depts", depts);

        return "add";
    }

    @RequestMapping("/add")
    public String add(@RequestParam("fileName") MultipartFile fileName, Empl empl) {

        // file 都是存在tomcat的项目目录下，为了访问
        // 镜像，随便一个本地文件夹中，和项目中的某一个文件作为镜像
        try {
            String fName = fileName.getOriginalFilename();

            String path = "F://upload/" + fName;

            File desc = new File(path);

            // 写文件

            fileName.transferTo(desc);
            empl.setFile("files/" + fName);

            emplDao.save(empl);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/empl/findAll";
    }

    @GetMapping("/preUpdate/{eid}")
    public String preUpdate(@PathVariable("eid") Integer eid,Model model){

        // 先查询
        Empl empl = emplDao.findOne(eid);
        model.addAttribute("empl",empl);

        List<Dept> depts = deptDao.findAll();
        model.addAttribute("depts", depts);

        return "update";
    }

    @RequestMapping("/update")
    public String update(@RequestParam("fileName") MultipartFile fileName, Empl empl) throws IOException {
        if(fileName.getSize()!=0){
            String fName = fileName.getOriginalFilename();

            String path = "F://upload/" + fName;

            File desc = new File(path);

            // 写文件
            fileName.transferTo(desc);
            empl.setFile("files/" + fName);
        }
        emplDao.saveAndFlush(empl);

        return "redirect:/empl/findAll";
    }

}
