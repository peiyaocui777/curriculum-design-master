package com.cpystu.mhl.service;

import com.cpystu.mhl.dao.MenuDao;
import com.cpystu.mhl.domain.Menu;

import java.util.List;

/**
 * @author 崔佩谣
 * @date 2025/4/20 15:41
 * @description: Menu的业务层
 */
public class MenuService {
    private MenuDao menuDao=new MenuDao();
    //编写方法，返回一个list集合
    public List<Menu> getMenu(){
        return menuDao.queryMulti("select * from menu", Menu.class);
    }
    //编写方法，根据用户输入的menuId返回对应的菜品
    public Menu getMenuById(int menuId){
        return (Menu) menuDao.querySingle("select * from menu where id = ?", Menu.class,menuId);//todo 默认返回的是object？会报错
    }
}
