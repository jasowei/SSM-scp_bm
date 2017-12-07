package com.jaso.base.controller;

import com.github.pagehelper.PageInfo;
import com.jaso.admin.bean.Admin;
import com.jaso.base.bean.Menu;
import com.jaso.base.service.MenuService;
import com.jaso.base.util.PageBean;
import com.jaso.base.util.PageExt;
import com.jaso.base.util.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.jvm.hotspot.debugger.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * 查询所有菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "allMenu")
    public PageInfo<Menu> allMenu(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Menu> allMenu = menuService.select_allmenu(pageNum, pageSize);
        return allMenu;
    }

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
     * 添加菜单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMenu")
    public int addMenu(Menu menuForm) {
        System.out.println("表单id : "+menuForm.getMenu_id());
        //有id信息, 编辑
        if (menuForm.getMenu_id() != 0) {
            ediMenu(menuForm);
        } else {

            Menu menu1 = menuService.select_menuByName(menuForm.getMenu_name());
            if (menu1 != null) {
                return 0;
            }

            //类型
            int type;
            if (menuForm.getParent_id() == 0) {
                type = 1;
            } else {
                type = 2;
            }
            //时间
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            df.format(date);// new Date()为获取当前系统时间
            //序号
            int size = menuService.menuSize() + 1;

            Menu menu = new Menu(menuForm.getParent_id(), "icon", menuForm.getMenu_name(), "urlkey",
                    menuForm.getUrl(), 1, type, size, 1, date, 1);
            menuService.addMenu(menu);
            return 1;
        }
        return 0;
    }


    /**
     * 删除菜单
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
     * 恢复菜单
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
     * 编辑菜单
     *
     * @return
     */
    @RequestMapping(value = "menu-edi/{id}")
    public String menuEdi(@PathVariable
                                  int id,
                          HttpServletRequest request) {
        Menu menu = menuService.select_menuById(id);

        System.out.println("要编辑的id : "+id);
        request.setAttribute("id", id);
        request.setAttribute("menu", menu);

        return "admin/menu-add";
    }

    @ResponseBody
    @RequestMapping(value = "backfill")
    public Menu backfill(HttpServletRequest request) {

        Object id = request.getAttribute("id");
        if (id == null){
            System.out.println("~添加~");
            return null;
        }
        System.out.println("~编辑~");
        return (Menu) request.getAttribute("menu");
    }

    @ResponseBody
    @RequestMapping(value = "ediMenu")
    public int ediMenu(Menu menuForm) {

        Menu menu2 = menuService.select_menuById(menuForm.getMenu_id());
        Menu menu1 = menuService.select_menuByName(menuForm.getMenu_name());
        if (menu1 != null && !menu1.getMenu_name().equals(menu2.getMenu_name())) {
            return 0;
        }

        //类型
        int type;
        if (menuForm.getParent_id() == 0) {
            type = 1;
        } else {
            type = 2;
        }
        //时间
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        df.format(date);// new Date()为获取当前系统时间
        //序号
        int size = menuService.menuSize() + 1;

        Menu menu = new Menu(menuForm.getMenu_id(), menuForm.getParent_id(), "icon", menuForm.getMenu_name(), "urlkey",
                menuForm.getUrl(), 1, type, size, 1, date, 1);
        menuService.ediMenu(menu);
        return 1;
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


}
