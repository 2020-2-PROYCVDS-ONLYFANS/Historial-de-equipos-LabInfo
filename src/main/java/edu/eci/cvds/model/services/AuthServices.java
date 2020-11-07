package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.role.Role;
import edu.eci.cvds.model.entities.role.RoleName;
import edu.eci.cvds.model.entities.User;

import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public interface AuthServices {

    User loadByUsername(String username) throws LabInfoServicesException;

    void addRoleToUserByUsername(String username, RoleName roleName) throws LabInfoServicesException;

    List<User> loadAll() throws LabInfoServicesException;

    Set<Role> loadUserRolesByUsername(String username) throws LabInfoServicesException;

    void registerUser(User user) throws LabInfoServicesException;

    void registerRole(RoleName roleName) throws LabInfoServicesException;

    Role loadByRoleName(RoleName roleName) throws LabInfoServicesException;

    void updatePassword(String username, String password) throws LabInfoServicesException;
}
