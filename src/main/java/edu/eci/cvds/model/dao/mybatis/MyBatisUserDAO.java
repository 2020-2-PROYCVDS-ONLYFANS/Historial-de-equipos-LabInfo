package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.UserDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.UserMapper;
import edu.eci.cvds.model.entities.User;
import edu.eci.cvds.model.entities.role.Role;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class MyBatisUserDAO implements UserDAO {

    @Inject
    private UserMapper userMapper;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(UserDAO.class);

    @Override
    public Long getIdByUsername(String username) throws PersistenceException {
        LOGGER.info("loadIdByUsername");
        try {
            LOGGER.info("loadIdByUsername - try");
            return userMapper.getIdByUsername(username);
        } catch (PersistenceException e) {
            LOGGER.info("loadIdByUsername - catch");
            throw new PersistenceException("Fail to load id by username.", e);
        }
    }

    @Override
    public User getByUsername(String username) throws PersistenceException {
        LOGGER.info("loadByUsername");
        try {
            LOGGER.info("loadByUsername - try");
            return userMapper.getByUsername(username);
        } catch (PersistenceException e) {
            LOGGER.info("loadByUsername - catch");
            throw new PersistenceException("Not found user " + username + ".", e);
        }
    }

    @Override
    public Set<Role> getUserRolesByUsername(String username)
            throws PersistenceException {
        LOGGER.info("loadUserRolesByUsername");
        try {
            LOGGER.info("loadUserRolesByUsername - try");
            return userMapper.getUserRolesByUsername(username);
        } catch (PersistenceException e) {
            LOGGER.info("loadUserRolesByUsername - catch");
            throw new PersistenceException("Fail to load user roles for " + username, e);
        }
    }
}
