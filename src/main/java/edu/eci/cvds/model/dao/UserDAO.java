package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.role.Role;
import edu.eci.cvds.model.entities.User;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.Set;

public interface UserDAO {

    Long getIdByUsername(String username)
            throws PersistenceException;

    User getByUsername(String username)
            throws PersistenceException;

    Set<Role> getUserRolesByUsername(String username)
            throws PersistenceException;
}
