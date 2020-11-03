package edu.eci.cvds.model.guice.mybatis;

import edu.eci.cvds.model.dao.RoleDAO;
import edu.eci.cvds.model.dao.UserDAO;
import edu.eci.cvds.model.dao.mybatis.MyBatisRoleDAO;
import edu.eci.cvds.model.dao.mybatis.MyBatisUserDAO;
import edu.eci.cvds.model.services.LabInfoServices;
import edu.eci.cvds.model.services.impl.LabInfoServicesImpl;
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

                bind(LabInfoServices.class).to(LabInfoServicesImpl.class);
                bind(UserDAO.class).to(MyBatisUserDAO.class);
                bind(RoleDAO.class).to(MyBatisRoleDAO.class);
            }
        };
    }

    public XMLMyBatisModule getMyBatisModuleLocal() {
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
