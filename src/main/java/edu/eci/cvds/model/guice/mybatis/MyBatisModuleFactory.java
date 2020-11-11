package edu.eci.cvds.model.guice.mybatis;

import edu.eci.cvds.model.dao.*;
import edu.eci.cvds.model.dao.mybatis.*;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.impl.AuthServicesImpl;
import edu.eci.cvds.model.services.impl.ComputerServicesImpl;
import edu.eci.cvds.model.services.impl.ElementServicesImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

public class MyBatisModuleFactory {

    private static final MyBatisModuleFactory instance = new MyBatisModuleFactory();

    private static XMLMyBatisModule myBatisModule;

    public MyBatisModuleFactory() { }

    private XMLMyBatisModule myBatisModule(String env, String pathResource) {
        return new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId(env);
                setClassPathResource(pathResource);

                bind(AuthServices.class).to(AuthServicesImpl.class);
                bind(ElementServices.class).to(ElementServicesImpl.class);
                bind(ComputerServices.class).to(ComputerServicesImpl.class);

                bind(UserDAO.class).to(MyBatisUserDAO.class);
                bind(RoleDAO.class).to(MyBatisRoleDAO.class);
                bind(ElementTypeDAO.class).to(MyBatisElementTypeDAO.class);
                bind(ElementDAO.class).to(MyBatisElementDAO.class);
                bind(ElementHistoryDAO.class).to(MyBatisElementHistoryDAO.class);
                bind(ComputerDAO.class).to(MyBatisComputerDAO.class);
                bind(ComputerHistoryDAO.class).to(MyBatisComputerHistoryDAO.class);
            }
        };
    }

    public XMLMyBatisModule getMyBatisDevModule() {
        if (myBatisModule == null) {
            myBatisModule = myBatisModule("development", "development/mybatis-config.xml");
        }
        return myBatisModule;
    }

    @SuppressWarnings("unused")
    public XMLMyBatisModule getMyBatisLocalModule() {
        if (myBatisModule == null) {
            myBatisModule = myBatisModule("local", "local/mybatis-config-local.xml");
        }
        return myBatisModule;
    }

    public XMLMyBatisModule getMyBatisTestModule() {
        if (myBatisModule == null) {
            myBatisModule = myBatisModule("test", "mybatis-config-h2.xml");
        }
        return myBatisModule;
    }

    public static MyBatisModuleFactory getInstance() {
        return instance;
    }
}
