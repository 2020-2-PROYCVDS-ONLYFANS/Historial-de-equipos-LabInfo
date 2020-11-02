package edu.eci.cvds.model.mybatis;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import java.util.Optional;

public class MyBatisModuleFactory {

    private static MyBatisModuleFactory instance = new MyBatisModuleFactory();

    private static Optional<XMLMyBatisModule> optionalXMLMyBatisModule;

    public MyBatisModuleFactory() {
        optionalXMLMyBatisModule = Optional.empty();
    }

    private XMLMyBatisModule myBatisModule(String env, String pathResource) {
        return new XMLMyBatisModule() {

            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId(env);
                setClassPathResource(pathResource);
            }
        };
    }

    public XMLMyBatisModule getMyBatisModuleLocal() {
        if (!optionalXMLMyBatisModule.isPresent()) {
            optionalXMLMyBatisModule = Optional.of(
                    myBatisModule("local", "local/mybatis-config-local.xml")
            );
        }
        return optionalXMLMyBatisModule.get();
    }

    public XMLMyBatisModule getMyBatisTestModule() {
        if (!optionalXMLMyBatisModule.isPresent()) {
            optionalXMLMyBatisModule = Optional.of(
                    myBatisModule("test", "mybatis-config-h2.xml")
            );
        }
        return optionalXMLMyBatisModule.get();
    }

    public static MyBatisModuleFactory getInstance() {
        return instance;
    }
}
