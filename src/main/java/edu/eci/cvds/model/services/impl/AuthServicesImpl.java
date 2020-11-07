package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.RoleDAO;
import edu.eci.cvds.model.dao.UserDAO;
import edu.eci.cvds.model.entities.role.Role;
import edu.eci.cvds.model.entities.role.RoleName;
import edu.eci.cvds.model.entities.User;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.util.List;
import java.util.Set;

public class AuthServicesImpl implements AuthServices {

    @Inject
    UserDAO userDAO;

    @Inject
    RoleDAO roleDAO;

    @Override
    public User loadByUsername(String username) throws LabInfoServicesException {
        try {
            return userDAO.loadByUsername(username);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Not found user " + username + ".", e);
        }
    }

    @Override
    public void addRoleToUserByUsername(String username, RoleName roleName) throws LabInfoServicesException {
        try {
            userDAO.addRoleToUserByUsername(username, roleName);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Fails to add role to user " + username + ".", e);
        }
    }

    @Override
    public List<User> loadAll() throws LabInfoServicesException {
        try {
            return userDAO.loadAll();
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Fail to load users.", e);
        }
    }

    @Override
    public Set<Role> loadUserRolesByUsername(String username) throws LabInfoServicesException {
        try {
            return userDAO.loadUserRolesByUsername(username);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Fail to load user roles for " + username, e);
        }
    }

    @Override
    public void registerUser(User user) throws LabInfoServicesException {
        try {
            user.setPassword(new Sha256Hash(user.getPassword()).toString()); // encrypting password
            userDAO.registerUser(user);
            addRoleToUserByUsername(user.getUsername(), RoleName.ROLE_USER);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Fail to register user " + user.getName() + ".", e);
        }
    }

    @Override
    public void registerRole(RoleName roleName) throws LabInfoServicesException {
        try {
            roleDAO.registerRole(roleName);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Fail to register role " + roleName, e);
        }
    }

    @Override
    public Role loadByRoleName(RoleName roleName) throws LabInfoServicesException {
        try {
            return roleDAO.loadByRoleName(roleName);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Fail to load role " + roleName, e);
        }
    }

    @Override
    public void updatePassword(String username, String password) throws LabInfoServicesException {
        try {
            password = new Sha256Hash(password).toString(); // encrypting password
            userDAO.updatePassword(username, password);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException("Fail to update password.", e);
        }
    }
}
