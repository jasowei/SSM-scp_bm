package com.jaso.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jaso.admin.bean.Permit;
import com.jaso.admin.bean.Role;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.mapper.RoleMapper;
import com.jaso.admin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/12/8.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private AdminMapper adminMapper;

    @Override
    public PageInfo<Role> select_allRoles(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Role> allRole = roleMapper.select_allRole();
        PageInfo pageInfo = new PageInfo<Role>(allRole);

        return pageInfo;
    }

    @Override
    public int getAllAccount() {
        return roleMapper.select_allRole().size();
    }

    @Override
    public List<Permit> select_allPermit() {
        return roleMapper.select_allPermit();
    }

    @Override
    public Role select_RoleById(int role_id) {
        return roleMapper.select_RoleById(role_id);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public Role select_RoleByName(String role_name) {
        return roleMapper.select_RoleByName(role_name);
    }

    @Override
    public void deleteRoleToPermit(int role_id) {
        roleMapper.deleteRoleToPermit(role_id);
    }

    @Override
    public void insertRoleToPermit(int role_id, int permit_id) {
        roleMapper.insertRoleToPermit(role_id, permit_id);
    }

    @Override
    public Role select_RoleByNameAndId(Role role) {
        return roleMapper.select_RoleByNameAndId(role);
    }

    /**
     * 修改角色
     *  role 新角色
     *  permit 角色权限
     */
    @Override
    public void updateRole(Role role, String permit) {
        roleMapper.updateRole(role);
        roleMapper.deleteRoleToPermit(role.getRole_id());
        saveRoleToPermit(role, permit);
    }

    private void saveRoleToPermit(Role role, String permit) {
        for (String s : permit.split(",")) {
            if (!s.equals("")) {
                int permit_id = Integer.parseInt(s);
                roleMapper.insertRoleToPermit(role.getRole_id(), permit_id);
            }
        }
    }

    /**
     * 添加角色
     * role 新角色
     * permit 角色权限
     */
    @Override
    public void addRole(Role role, String permit) {
        roleMapper.addRole(role);
        // 给超级管理员添加此角色
        adminMapper.insertAdminToRole(1, roleMapper.select_RoleByNameNoPermit(role.getRole_name()).getRole_id());
        role = roleMapper.select_RoleByNameNoPermit(role.getRole_name());
        saveRoleToPermit(role, permit);
    }

    @Override
    public void deleteRoleById(int role_id) {
        roleMapper.deleteRoleById(role_id);
        roleMapper.deleteRoleToPermit(role_id);
        roleMapper.deleteAdminToRole(role_id);
    }

    @Override
    public Role select_RoleByNameNoPermit(String role_name) {
        return roleMapper.select_RoleByNameNoPermit(role_name);
    }

    /**
     * 批量删除
     * @param roleId 要删除角色的id串
     */
    @Override
    public void deleteRoleMore(String roleId) {
        // 以逗号分隔id串
        for (String s : roleId.split(",")) {
            if (!s.equals("")){
                int role_id = Integer.parseInt(s);
                if (role_id != 1){
                    roleMapper.deleteRoleById(role_id);
                    roleMapper.deleteRoleToPermit(role_id);
                    roleMapper.deleteAdminToRole(role_id);
                }
            }
        }
    }

    @Override
    public List<Role> select_allRoles() {
        return roleMapper.select_allRole();
    }
}
