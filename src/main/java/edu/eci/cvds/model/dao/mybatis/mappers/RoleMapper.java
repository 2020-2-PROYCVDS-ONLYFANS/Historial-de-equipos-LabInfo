package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.Role;
import edu.eci.cvds.model.entities.RoleName;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface RoleMapper {

    @Transactional
    void registerRole(@Param("roleName") RoleName roleName);

    Role loadByRoleName(@Param("roleName") RoleName roleName);
}
