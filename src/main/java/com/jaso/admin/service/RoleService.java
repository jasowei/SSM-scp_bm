package com.jaso.admin.service;

import com.github.pagehelper.PageInfo;
import com.jaso.admin.bean.Permit;
import com.jaso.admin.bean.Role;

import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
public interface RoleService {
    /**
     * 查询所有角色
     * @param pageNum 开始页
     * @param pageSize 限制条数
     */
    PageInfo<Role> select_allRoles(Integer pageNum, Integer pageSize);

    /**
     * 获取角色总记录数
     */
    int getAllAccount();

    /**
     * 获取全部权限
     */
    List<Permit> select_allPermit();

    /**
     * 根据id查询角色
     */
    Role select_RoleById(int role_id);

    /**
     * 编辑角色
     */
    void updateRole(Role role);

    /**
     * 根据名字查询角色
     */
    Role select_RoleByName(String role_name);

    /**
     * 根据 roleId 删除 role_permit 关系
     */
    void deleteRoleToPermit(int role_id);

    /**
     * 添加 role_permit 的关系
     */
    void insertRoleToPermit(int role_id, int permit_id);

    /**
     * 根据 名字和id 查询角色
     */
    Role select_RoleByNameAndId(Role role);

    /**
     * 修改角色
     * @param role 新角色
     * @param permit 角色权限
     */
    void updateRole(Role role, String permit);

    /**
     * 添加角色
     * @param role 新角色
     * @param permit 角色权限
     */
    void addRole(Role role, String permit);

    /**
     * 根据id 删除角色
     */
    void deleteRoleById(int role_id);

    /**
     * 根据 角色名 查询, 不带权限
     */
    Role select_RoleByNameNoPermit(String role_name);

    /**
     * 批量删除
     * @param roleId 要删除角色的id串
     */
    void deleteRoleMore(String roleId);

    List<Role> select_allRoles();
}
