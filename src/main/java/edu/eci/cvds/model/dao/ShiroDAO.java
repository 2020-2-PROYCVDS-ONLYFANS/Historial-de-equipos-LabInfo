package edu.eci.cvds.model.dao;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;

public interface ShiroDAO {

    AuthenticationInfo fetchAuthenticationInfoByUsername(String username, String realmName)
            throws PersistenceException;

    AuthorizationInfo fetchAuthorizationInfoByUsername(String username) throws PersistenceException;
}
