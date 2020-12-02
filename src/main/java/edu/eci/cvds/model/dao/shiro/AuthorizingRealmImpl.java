package edu.eci.cvds.model.dao.shiro;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ShiroDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class AuthorizingRealmImpl extends AuthorizingRealm {

    @Inject
    ShiroDAO shiroDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizingRealmImpl.class);

    public AuthorizingRealmImpl(CredentialsMatcher matcher) {
        super(matcher);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        LOGGER.info("doGetAuthenticationInfo");
        if (token.getPrincipal() != null && token.getCredentials() != null) {
            return shiroDAO.fetchAuthenticationInfoByUsername(token.getPrincipal().toString(),
                    this.getClass().getSimpleName());
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
        LOGGER.info("doGetAuthorizationInfo");
        if (!collection.isEmpty()) {
            return shiroDAO
                    .fetchAuthorizationInfoByUsername(collection.getPrimaryPrincipal().toString());
        }
        return null;
    }
}
