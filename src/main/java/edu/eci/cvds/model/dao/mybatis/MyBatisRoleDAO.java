package edu.eci.cvds.model.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.RoleDAO;
import edu.eci.cvds.model.dao.mybatis.mappers.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class MyBatisRoleDAO implements RoleDAO {

    @Inject
    private RoleMapper roleMapper;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(MyBatisRoleDAO.class);
}
