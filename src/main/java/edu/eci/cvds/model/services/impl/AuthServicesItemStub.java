package edu.eci.cvds.model.services.impl;

import edu.eci.cvds.model.entities.role.Role;
import edu.eci.cvds.model.entities.role.RoleName;
import edu.eci.cvds.model.entities.User;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.LabInfoServicesException;

import java.util.List;
import java.util.Set;

@SuppressWarnings("all")
public class AuthServicesItemStub implements AuthServices {

    @Override
    public User loadByUsername(String username) throws LabInfoServicesException {
        return null;
    }

    @Override
    public void addRoleToUserByUsername(String username, RoleName roleName) throws LabInfoServicesException {

    }

    @Override
    public List<User> loadAll() throws LabInfoServicesException {
        return null;
    }

    @Override
    public Set<Role> loadUserRolesByUsername(String username) throws LabInfoServicesException {
        return null;
    }

    @Override
    public void registerUser(User user) throws LabInfoServicesException {

    }

    @Override
    public void registerRole(RoleName roleName) throws LabInfoServicesException {

    }

    @Override
    public Role loadByRoleName(RoleName roleName) throws LabInfoServicesException {
        return null;
    }

    @Override
    public void updatePassword(String username, String password) throws LabInfoServicesException {

    }
}
