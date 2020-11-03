package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.Role;
import org.apache.ibatis.exceptions.PersistenceException;

@SuppressWarnings("unused")
public interface RoleDAO {

    void registerRole(String roleName) throws PersistenceException;

    Role loadByRoleName(String roleName) throws PersistenceException;
}
