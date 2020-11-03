package edu.eci.cvds.model.dao.auth;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.jdbc.JdbcServices;
import edu.eci.cvds.model.dao.mybatis.mappers.RoleMapper;
import edu.eci.cvds.model.dao.mybatis.mappers.UserMapper;
import edu.eci.cvds.model.dao.shiro.AuthorizingRealmImpl;
import edu.eci.cvds.model.entities.Role;
import edu.eci.cvds.model.entities.User;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class AuthDAO {

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Inject
    UserMapper userMapper;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(AuthDAO.class);

    public AuthenticationInfo fetchAuthenticationInfoByUsername(String username, String realmName) {
        LOGGER.info("fetchAuthenticationInfoByUsername");
        try {
            User user = userMapper.loadByUsername(username);
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), realmName);
        } catch (PersistenceException e) {
            return null;
        }
    }

    public AuthorizationInfo fetchAuthorizationInfoByUsername(String username, String realmName) {
        LOGGER.info("fetchAuthorizationInfoByUsername");
        try {
            Set<Role> roles = userMapper.loadUserRolesByUsername(username);
            Set<String> roleNames = new HashSet<>();

            for (Role role : roles) {
                roleNames.add(role.getName());
            }

            return new SimpleAuthorizationInfo(roleNames);
        } catch (PersistenceException e) {
            return null;
        }
    }

    public static String getRoleUser() {
        return ROLE_USER;
    }

    public static String getRoleAdmin() {
        return ROLE_ADMIN;
    }
}
