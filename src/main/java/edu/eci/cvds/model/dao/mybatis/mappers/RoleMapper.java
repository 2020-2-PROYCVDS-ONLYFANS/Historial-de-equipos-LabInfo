package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.Role;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface RoleMapper {

    @Transactional
    void registerRole(@Param("roleName") String roleName);

    Role loadByRoleName(@Param("roleName") String roleName);
}
