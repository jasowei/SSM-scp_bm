package com.jaso.admin.mapper;

import com.jaso.admin.bean.Permit;
import com.jaso.admin.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
public interface RoleMapper {
    /**
     * 查询所有角色
     */
    List<Role> select_allRole();

    /**
     * 查询所有权限
     */
    List<Permit> select_allPermit();

    /**
     * 根据id查询角色
     */
    Role select_RoleById(int role_id);

    /**
     * 更新角色
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
    void insertRoleToPermit(@Param("role_id") int role_id, @Param("permit_id")int permit_id);

    /**
     * 根据 名字和id 查询角色
     */
    Role select_RoleByNameAndId(Role role);

    /**
     * 添加角色
     * @param role 新角色
     */
    void addRole(Role role);

    /**
     * 根据id 删除角色
     */
    void deleteRoleById(int role_id);

    /**
     * 根据 角色名 查询, 不带权限
     */
    Role select_RoleByNameNoPermit(String role_name);
}
