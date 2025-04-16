package com.cpystu.mhl.service;

import com.cpystu.mhl.dao.EmployeeDAO;
import com.cpystu.mhl.domain.Employee;

/**
 * @author 崔佩谣
 * @date 2025/4/15 16:53
 * @description: 最关键的业务层，该类完成对employee表的各种操作，通过调用employeeDAO对象完成
 */
public class EmployeeService {
    //定义一个EmployeeDAO属性（对象）给Service使用
    private EmployeeDAO employeeDAO=new EmployeeDAO();
    //下面是一个校验方法，根据传入的empId和psw判断是否在表中存在，
    //如果存在（查询到了），就返回Employee对象，不存在返回null
    public Employee getEmployeeByIdAndPwd(String EmpId,String pwd){//传进去一个EmpId和pwd进行校验
        //String EmpId,String pwd注意事项：
        //1.这是形参，格式为数据类型 参数名
        //2.参数EmpId和pwd由实现getEmployeeByIdAndPwd的类传入
        return//sql查询语句，这是service层的核心功能，调用DAO的方法实现具体的业务要求
                //pwd=md5(?)一定要使用md5函数，否则查询不到结果（md5()是加密函数）
                //Employee.class 指定要操作的是哪个类（表）todo domain跟数据库怎么联系起来的
                //EmpId,pwd getEmployeeByIdAndPwd()传进来的参数，传到querySingle()方法中
                employeeDAO.querySingle("Select * from Employee where empId=?&&pwd=md5(?)",
                        Employee.class,
                        EmpId,pwd);
    }
}
