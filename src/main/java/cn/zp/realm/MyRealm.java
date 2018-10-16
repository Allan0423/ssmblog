package cn.zp.realm;

import cn.zp.model.Blogger;
import cn.zp.service.IBloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm{

    @Resource
    private IBloggerService bloggerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String) token.getPrincipal();
        Blogger blogger = bloggerService.findBloggerByName(name);
        if (blogger != null){
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
            return new SimpleAuthenticationInfo(blogger.getName(), blogger.getPassword(), "realmName");
        }else {
            return null;
        }
    }
}
