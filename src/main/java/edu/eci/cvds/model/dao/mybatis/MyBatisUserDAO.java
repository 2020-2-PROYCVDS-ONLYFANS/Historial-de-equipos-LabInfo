package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.UserDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.UserMapper;
import edu.eci.cvds.model.entities.Role;
import edu.eci.cvds.model.entities.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class MyBatisUserDAO implements UserDAO {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    @Inject
    UserMapper userMapper;

    @Override
    public User loadByUsername(String username) throws PersistenceException {
        try {
            return userMapper.loadByUsername(username);
        } catch (PersistenceException e) {
            throw new PersistenceException("Not found user " + username + ".", e);
        }
    }

    @Override
    public void addRoleToUserByUsername(String username, String roleName) throws PersistenceException {
        try {
            userMapper.addRoleToUserByUsername(username, roleName);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fails to add role to user " + username + ".", e);
        }
    }

    @Override
    public List<User> loadAll() throws PersistenceException {
        try {
            return userMapper.loadAll();
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to load users.", e);
        }
    }

    @Override
    public Set<Role> loadUserRolesByUsername(String username) throws PersistenceException {
        try {
            return userMapper.loadUserRolesByUsername(username);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to load user roles for " + username, e);
        }
    }

    @Override
    public void registerUser(User user) throws PersistenceException {
        try {
            userMapper.registerUser(user);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to register user " + user.getName() + ".", e);
        }
    }

    @Override
    public void updatePassword(String username, String password) throws PersistenceException {
        try {
            userMapper.updatePassword(username, password);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fail to update password", e);
        }
    }
}
