package edu.eci.cvds.model.services.impl;

import edu.eci.cvds.model.entities.Role;
import edu.eci.cvds.model.entities.User;
import edu.eci.cvds.model.services.LabInfoServices;
import edu.eci.cvds.model.services.LabInfoServicesException;

import java.util.List;
import java.util.Set;

public class LabInfoServicesItemStub implements LabInfoServices {

    @Override
    public User loadByUsername(String username) throws LabInfoServicesException {
        return null;
    }

    @Override
    public void addRoleToUserByUsername(String username, String roleName) throws LabInfoServicesException {

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
    public void registerRole(String roleName) throws LabInfoServicesException {

    }

    @Override
    public Role loadByRoleName(String roleName) throws LabInfoServicesException {
        return null;
    }

    @Override
    public void updatePassword(String username, String password) throws LabInfoServicesException {

    }
}
