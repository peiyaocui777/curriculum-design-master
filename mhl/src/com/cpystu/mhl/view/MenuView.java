package com.cpystu.mhl.view;

import com.cpystu.mhl.dao.MenuDao;
import com.cpystu.mhl.domain.Menu;
import com.cpystu.mhl.service.MenuService;

import java.util.List;

/**
 * @author 崔佩谣
 * @date 2025/4/20 15:49
 * @description: 显示Menu界面
 */
public class MenuView {
    //
    private MenuService menuService=new MenuService();

    public void showMenu(){
        System.out.println("========菜单========");
        List<Menu> menus = menuService.getMenu();
        System.out.println("\n菜品编号\t\t菜品名\t\t类别\t\t价格");
        for (Menu menu : menus) {
            //System.out.println(menu.toString());
            System.out.println(menu);
        }
        System.out.println("========显示完毕========");
    }
}
