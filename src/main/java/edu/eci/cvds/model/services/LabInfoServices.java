package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.Role;
import edu.eci.cvds.model.entities.User;

import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public interface LabInfoServices {

    User loadByUsername(String username) throws LabInfoServicesException;

    void addRoleToUserByUsername(String username, String roleName) throws LabInfoServicesException;

    List<User> loadAll() throws LabInfoServicesException;

    Set<Role> loadUserRolesByUsername(String username) throws LabInfoServicesException;

    void registerUser(User user) throws LabInfoServicesException;

    void registerRole(String roleName) throws LabInfoServicesException;

    Role loadByRoleName(String roleName) throws LabInfoServicesException;

    void updatePassword(String username, String password) throws LabInfoServicesException;
}
