package edu.eci.cvds.model.dao.shiro;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ShiroDAO;
import edu.eci.cvds.model.dao.UserDAO;
import edu.eci.cvds.model.entities.role.Role;
import edu.eci.cvds.model.entities.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ShiroDAOImpl implements ShiroDAO {

    @Inject
    UserDAO userDAO;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(ShiroDAOImpl.class);

    public AuthenticationInfo fetchAuthenticationInfoByUsername(String username, String realmName) {
        LOGGER.info("fetchAuthenticationInfoByUsername");
        try {
            User user = userDAO.getByUsername(username);
            if (user != null) {
                return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
                        realmName);
            }
        } catch (PersistenceException e) {
            LOGGER.info("fetchAuthenticationInfoByUsername - catch PersistenceException");
            return null;
        }
        return null;
    }

    public AuthorizationInfo fetchAuthorizationInfoByUsername(String username) {
        LOGGER.info("fetchAuthorizationInfoByUsername");
        try {
            Set<Role> roles = userDAO.getUserRolesByUsername(username);
            Set<String> roleNames = new HashSet<>();

            if (roles != null) {
                for (Role role : roles) {
                    roleNames.add(role.getRoleName().toString());
                }
            }

            return new SimpleAuthorizationInfo(roleNames);
        } catch (PersistenceException e) {
            LOGGER.info("fetchAuthorizationInfoByUsername - catch PersistenceException");
            return null;
        }
    }
}
