package edu.eci.cvds.model.util;

import edu.eci.cvds.model.dao.mybatis.mappers.RoleMapper;
import edu.eci.cvds.model.dao.mybatis.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class Client {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        SqlSessionFactory factory = LabInfoUtil.getSqlSessionFactory();
        SqlSession session = factory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);
        RoleMapper roleMapper = session.getMapper(RoleMapper.class);



        session.commit();
        session.close();
    }
}
