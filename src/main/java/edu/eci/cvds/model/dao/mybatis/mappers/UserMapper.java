package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.User;
import edu.eci.cvds.model.entities.role.Role;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Long getIdByUsername(@Param("username") String username);

    User getByUsername(@Param("username") String username);

    Set<Role> getUserRolesByUsername(@Param("username") String username);

    String getUsernameById(@Param("id") Long id);
}
