package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.role.Role;
import edu.eci.cvds.model.entities.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

import java.util.Set;

public interface UserMapper {

    Long getIdByUsername(@Param("username") String username);

    User getByUsername(@Param("username") String username);

    Set<Role> getUserRolesByUsername(@Param("username") String username);
}
