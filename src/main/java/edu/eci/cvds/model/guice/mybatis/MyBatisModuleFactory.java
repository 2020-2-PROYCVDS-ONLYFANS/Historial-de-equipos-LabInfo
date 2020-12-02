package edu.eci.cvds.model.guice.mybatis;

import edu.eci.cvds.model.dao.*;
import edu.eci.cvds.model.dao.mybatis.*;
import edu.eci.cvds.model.services.*;
import edu.eci.cvds.model.services.impl.*;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisModuleFactory {

    private static XMLMyBatisModule myBatisModule;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisModuleFactory.class);

    private MyBatisModuleFactory() {
    }

    private static XMLMyBatisModule myBatisModule(String env, String pathResource) {
        return new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId(env);
                setClassPathResource(pathResource);

                bind(AuthServices.class).to(AuthServicesImpl.class);
                bind(ElementServices.class).to(ElementServicesImpl.class);
                bind(ComputerServices.class).to(ComputerServicesImpl.class);
                bind(LabServices.class).to(LabServicesImpl.class);
                bind(NoveltyServices.class).to(NoveltyServicesImpl.class);

                bind(UserDAO.class).to(MyBatisUserDAO.class);
                bind(RoleDAO.class).to(MyBatisRoleDAO.class);
                bind(ElementTypeDAO.class).to(MyBatisElementTypeDAO.class);
                bind(ElementDAO.class).to(MyBatisElementDAO.class);
                bind(ComputerDAO.class).to(MyBatisComputerDAO.class);
                bind(LabDAO.class).to(MyBatisLabDAO.class);
                bind(NoveltyDAO.class).to(MyBatisNoveltyDAO.class);
            }
        };
    }

    public static XMLMyBatisModule getMyBatisDevModule() {
        if (myBatisModule == null) {
            myBatisModule = myBatisModule("development", "development/mybatis-config.xml");
        }
        return myBatisModule;
    }

    public static XMLMyBatisModule getMyBatisTestModule() {
        LOGGER.info("getMyBatisTestModule");
        if (myBatisModule == null) {
            LOGGER.info("getMyBatisTestModule - if myBatisModule == null");
            myBatisModule = myBatisModule("test", "mybatis-config-h2.xml");
        }
        return myBatisModule;
    }
}
