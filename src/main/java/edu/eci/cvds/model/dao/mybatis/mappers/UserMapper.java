package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.role.Role;
import edu.eci.cvds.model.entities.role.RoleName;
import edu.eci.cvds.model.entities.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

import java.util.List;
import java.util.Set;

public interface UserMapper {

    User loadByUsername(@Param("username") String username);

    @Transactional
    void addRoleToUserByUsername(
            @Param("username") String username,
            @Param("roleName") RoleName roleName
    );

    List<User> loadAll();

    Set<Role> loadUserRolesByUsername(@Param("username") String username);

    @Transactional
    void registerUser(@Param("user") User user);

    @Transactional
    void updatePassword(@Param("username") String username,
                        @Param("password") String password);
}
