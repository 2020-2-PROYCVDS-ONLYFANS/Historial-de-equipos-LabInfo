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

@SuppressWarnings("java:S1130")
public class MyBatisUserDAO implements UserDAO {

    @Inject
    private UserMapper userMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisUserDAO.class);

    @Override
    public Long getIdByUsername(String username) throws PersistenceException {
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
        try {
            LOGGER.info("loadByUsername - try");
            return userMapper.getByUsername(username);
        } catch (PersistenceException e) {
            LOGGER.info("loadByUsername - catch");
            throw new PersistenceException("Not found user " + username + ".", e);
        }
    }

    @Override
    public Set<Role> getUserRolesByUsername(String username) throws PersistenceException {
        try {
            LOGGER.info("loadUserRolesByUsername - try");
            return userMapper.getUserRolesByUsername(username);
        } catch (PersistenceException e) {
            LOGGER.info("loadUserRolesByUsername - catch");
            throw new PersistenceException("Fail to load user roles for " + username, e);
        }
    }

    @Override
    public String getUsernameById(Long id) throws PersistenceException {
        try {
            return userMapper.getUsernameById(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to get username by id.", e);
        }
    }
}
