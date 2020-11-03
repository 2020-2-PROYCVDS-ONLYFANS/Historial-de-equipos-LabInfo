package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.Role;
import edu.eci.cvds.model.entities.User;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public interface UserDAO {

    User loadByUsername(String username) throws PersistenceException;

    void addRoleToUserByUsername(String username, String roleName) throws PersistenceException;

    List<User> loadAll() throws PersistenceException;

    Set<Role> loadUserRolesByUsername(String username) throws PersistenceException;

    void registerUser(User user) throws PersistenceException;

    void updatePassword(String username, String password) throws PersistenceException;
}
