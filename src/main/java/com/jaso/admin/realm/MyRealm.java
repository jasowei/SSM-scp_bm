package com.jaso.admin.realm;

import com.jaso.admin.bean.Admin;
import com.jaso.admin.mapper.AdminMapper;
import com.jaso.admin.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongren1 on 2017/12/4.
 */
@Service
public class MyRealm extends AuthorizingRealm {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 授权方法

        // 1. 认证的结果: 取出的User实体类/用户名

        String username = (String) principalCollection.getPrimaryPrincipal();

        // 2. 从数据库中获取该用户的所有角色和权限

        // ======>模拟数据<======

        List<String> roleList = new ArrayList<String>();
        roleList.add("CEO");
        roleList.add("HR");

        List<String> perList = new ArrayList<String>();
        perList.add("user:create");
        perList.add("user:query");

        // =======>模拟结束<=======


        // 3. 将获取的权限和角色都统一起来

        SimpleAuthorizationInfo info =
                new SimpleAuthorizationInfo();

        info.addRoles(roleList);
        info.addStringPermissions(perList);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 获得用户此次输入的用户名
        String username = (String) authenticationToken.getPrincipal();

        // 获取用户输入的密码
        String password = new String((char[]) authenticationToken.getCredentials());

        // 此处应该拿username去数据库查询, 是否存在该用户
        Admin admin = adminMapper.select_adminByLoginName(username);

        if (admin == null) {
            throw new UnknownAccountException("用户名不存在");
        }

        Admin admin1 = new Admin(username, password);
        admin = adminMapper.select_adminByLoginNameAndPwd(admin1);

        if (admin == null) {
            throw new IncorrectCredentialsException("密码错误");
        }

        // 返回认证成功的信息
        return new SimpleAuthenticationInfo(username, password, getName());

    }
}
