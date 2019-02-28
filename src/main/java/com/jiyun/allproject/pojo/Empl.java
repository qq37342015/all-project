package com.jiyun.allproject.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Empl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eid;
    @Temporal(TemporalType.DATE)
    private Date birth;
    private String ename;
    private Integer gender;
    private String file;

    @JoinColumn(name = "did")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Dept dept;

    @Override
    public String toString() {
        return "Empl{" +
                "eid=" + eid +
                ", birth=" + birth +
                ", ename='" + ename + '\'' +
                ", gender=" + gender +
                ", file='" + file + '\'' +
                ", dept=" + dept +
                '}';
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Empl() {
    }

    public Empl(Date birth, String ename, Integer gender, String file, Dept dept) {
        this.birth = birth;
        this.ename = ename;
        this.gender = gender;
        this.file = file;
        this.dept = dept;
    }
}
