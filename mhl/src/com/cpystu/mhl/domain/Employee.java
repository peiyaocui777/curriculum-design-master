package com.cpystu.mhl.domain;

/**
 * todo employee view界面-employeeService（业务层，指定需要完成的到底是查询还是删除业务，组织sql语句并调用相应的DAO）-employeeDAO（继承BasicDAO，提供对employee表的增删改查）-employee表（数据库，存取员工信息）-Employee类（JavaBean，domain，一个类，含有属性方法构造器）
 * @author 崔佩谣
 * @date 2025/4/15 16:37
 * @description: 这是一个javabean 与employee表对应
 * 其实就是员工类吧 该类包括employee的属性 方法 构造器与get set方法
 */
public class Employee {
    private Integer id;//类型写成Integer，防止出现空指针
    private String empId;
    private String pwd;
    private String name;
    private String job;

    public Employee() {//无参构造器，一定要有，底层 todo apache-dbutils反射要用？
    }
    //有参构造器
    public Employee(Integer id, String empId, String pwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }
    //get set方法方便数据传递
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
