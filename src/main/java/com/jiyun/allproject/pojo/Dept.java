package com.jiyun.allproject.pojo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer did;
    private String dname;


    @OneToMany(mappedBy = "dept" , cascade = CascadeType.MERGE)
    private List<Empl> empls;

    public Dept() {
    }

    public Dept(String dname, List<Empl> empls) {
        this.dname = dname;
        this.empls = empls;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public List<Empl> getEmpls() {
        return empls;
    }

    public void setEmpls(List<Empl> empls) {
        this.empls = empls;
    }
}
