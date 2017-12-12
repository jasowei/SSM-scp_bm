package com.jaso.base.controller;

import com.github.pagehelper.PageInfo;
import com.jaso.admin.bean.Admin;
import com.jaso.base.bean.Menu;
import com.jaso.base.bean.MenuExt;
import com.jaso.base.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.jvm.hotspot.debugger.Page;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/12/4.
 */

@Controller
public class MainController {

    /**
     * 登录页
     */
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    /**
     * 主页
     */
    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    /**
     * 欢迎
     */
    @RequestMapping(value = "welcome")
    public String welcome() {
        return "welcome";
    }

    @Autowired
    @Qualifier("menuService")
    private MenuService menuService;

    /**
     * --->查询所有菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "allMenu")
    public PageInfo<Menu> allMenu(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("search") String search) {

        PageInfo<Menu> allMenu = menuService.select_allmenu(pageNum, pageSize,search);
        System.out.println("<<<菜单界面>>>");
        System.out.println("搜索结果 : "+allMenu.getList());
        return allMenu;
    }

//    /**
//     * 搜索
//     */
//    @ResponseBody
//    @RequestMapping(value = "searchMenu")
//    public MenuExt searchMenu(@RequestParam("search") String search){
//        System.out.println("搜索输入 : " + search);
//        List<Menu> menuList = menuService.searchMenu(search);
//        MenuExt menuExt = new MenuExt(menuList,search);
//        System.out.println("搜索结果 : "+menuList);
//        return menuExt;
//    }


    /**
     * 根据id查菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "select_menuById")
    public Menu select_menuById(Integer id) {
        Menu menu = menuService.select_menuById(id);
        return menu;
    }

    /**
     * 查询所有父级菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findAllParentMenu")
    public List<Menu> findAllParentMenu() {
        List<Menu> menuList = menuService.findAllParentMenu();
        return menuList;
    }


    /**
     * --->删除菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delMenu")
    public int delMenu(Integer id) {
        List<Menu> menu = menuService.hasSubMenu(id);
        if (menu.size() > 0) {
            return 0;
        }
        //无子菜单,可移除
        menuService.delMenu(id);
        return 1;
    }

    /**
     * 所有移除菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "allDelMenu")
    public PageInfo<Menu> allDelMenu(@RequestParam("pageNum") Integer pageNum,
                                     @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Menu> allMenu = menuService.select_allDelmenu(pageNum, pageSize);
        return allMenu;
    }

    /**
     * --->恢复菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "rcoMenu")
    public int rcoMenu(Integer id) {
        menuService.rcoMenu(id);
        return 1;
    }

    /**
     * --->添加菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMenu")
    public int addMenu(Menu menuForm, HttpServletRequest request) {

        Menu menu1 = menuService.select_menuByName(menuForm.getMenu_name());
        if (menu1 != null) {
            return 0;
        }

        //类型
        int type;
        String url = menuForm.getUrl();
        if (menuForm.getParent_id() == 0) {
            type = 1;
            url = "";
        } else {
            type = 2;
        }
        //时间
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        df.format(date);// new Date()为获取当前系统时间
        //序号
        int size = menuService.menuSize() + 1;

        //创建者
        int adminId = 0;
        Cookie[] cookie = request.getCookies();
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equalsIgnoreCase("adminId")) { //获取键
                adminId = Integer.parseInt(cook.getValue());
            }
        }

        Menu menu = new Menu(menuForm.getParent_id(), "icon", menuForm.getMenu_name(), "urlkey",
                url, 1, type, size, 1, date, adminId);
        menuService.addMenu(menu);
        return 1;

    }


    /**
     * --->编辑菜单
     *
     * @return
     */
    @RequestMapping(value = "menu-edi")
    public String menuEdi() {
        return "admin/menu-edi";
    }

    /**
     * 回填
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "backfill")
    public Menu backfill(Integer id) {
        System.out.println("~编辑~"+id);
        Menu menu = menuService.select_menuById(id);
        return menu;

    }

    /**
     * 编辑操作
     */
    @ResponseBody
    @RequestMapping(value = "ediMenu")
    public int ediMenu(Menu menuForm, HttpServletRequest request) {

        //名称去重
        Menu menu2 = menuService.select_menuById(menuForm.getMenu_id());
        Menu menu1 = menuService.select_menuByName(menuForm.getMenu_name());
        if (menu1 != null && !menu1.getMenu_name().equals(menu2.getMenu_name())) {
            return 0;
        }

        //类型
        int type;
        String url = menuForm.getUrl();
        if (menuForm.getParent_id() == 0) {
            type = 1;
            url = "";
        } else {
            type = 2;
        }
        //时间
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        df.format(date);// new Date()为获取当前系统时间

        //创建者
        int adminId = 0;
        Cookie[] cookie = request.getCookies();
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equalsIgnoreCase("adminId")) { //获取键
                adminId = Integer.parseInt(cook.getValue());
            }
        }

        Menu menu = new Menu(menuForm.getMenu_id(), menuForm.getParent_id(), "icon", menuForm.getMenu_name(), "urlkey",
                url, 1, type, menu2.getSort(), 1, date, adminId);
        menuService.ediMenu(menu);
        return 1;
    }


    /**
     * 菜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showMenu")
    public List<Menu> pMenu(){
        List<Menu> menuList = menuService.findAllpMenu();
        return menuList;
    }
    /**
     * 子菜单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sonMenu")
    public List<Menu> sMenu(Integer id){
        List<Menu> menuList = menuService.findAllsMenu(id);
        return menuList;
    }


    //----------admin------------
    @RequestMapping(value = "admin-role")
    public String adminRole() {
        return "admin/admin-role";
    }

    @RequestMapping(value = "admin-role-add")
    public String adminRoleAdd() {
        return "admin/admin-role-add";
    }

    @RequestMapping(value = "admin-list")
    public String adminList() {
        return "admin/admin-list";
    }

    @RequestMapping(value = "admin-add")
    public String adminAdd() {
        return "admin/admin-add";
    }

    @RequestMapping(value = "menu")
    public String menu() {
        return "admin/menu";
    }

    @RequestMapping(value = "menu-add")
    public String menuAdd() {
        return "admin/menu-add";
    }

    //----------system------------

    @RequestMapping(value = "system-base")
    public String systembase() {
        return "system/system-base";
    }


}
