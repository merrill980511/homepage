package com.merrill.realm;

import com.merrill.dao.entity.UserInfo;
import com.merrill.dao.entity.UserLogin;
import com.merrill.dao.mapper.PermissionMapper;
import com.merrill.dao.mapper.RoleMapper;
import com.merrill.dao.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    //认证操作
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从token中获取登录的用户名， 查询数据库返回用户信息
        String email = token.getPrincipal().toString();
        UserLogin user = userMapper.getUserLoginByEmail(email);

        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(email), "UserRealm");
        return info;
    };


//    @Override
//    public String getName() {
//        return "UserRealm";
//    }

    //授权操作
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        UserLogin user = (UserLogin) principals.getPrimaryPrincipal();

        List<String> permissions = new ArrayList<String>();
        List<String> roles = new ArrayList<>();

//        if("admin".equals(user.getUsername())){
//            //拥有所有权限
//            permissions.add("*:*");
//            //查询所有角色
//            roles = roleMapper.getAllRoleSn();
//        }else{
            //根据用户id查询该用户所具有的角色
            roles = roleMapper.getRoleSnByUserId(user.getId());
            //根据用户id查询该用户所具有的权限
            permissions = permissionMapper.getPermissionByUserId(user.getId());
//        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }
}
