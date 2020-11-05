package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.Role;
import edu.eci.cvds.model.entities.RoleName;
import org.apache.ibatis.exceptions.PersistenceException;

@SuppressWarnings("unused")
public interface RoleDAO {

    void registerRole(RoleName roleName) throws PersistenceException;

    Role loadByRoleName(RoleName roleName) throws PersistenceException;
}
