package edu.eci.cvds.model.util;

import edu.eci.cvds.model.dao.mybatis.mappers.*;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
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
        ElementTypeMapper elementTypeMapper = session.getMapper(ElementTypeMapper.class);
        ElementMapper elementMapper = session.getMapper(ElementMapper.class);
        ElementHistoryMapper elementHistoryMapper = session.getMapper(ElementHistoryMapper.class);
        ComputerMapper computerMapper = session.getMapper(ComputerMapper.class);

        computerMapper.registerComputerWithReferences(
                "computer", "computerCase", "monitor", "keyboard", "mouse");

        session.commit();
        session.close();
    }
}
