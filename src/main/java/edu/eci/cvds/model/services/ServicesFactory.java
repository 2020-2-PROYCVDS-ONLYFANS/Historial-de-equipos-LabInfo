package edu.eci.cvds.model.services;

import edu.eci.cvds.model.guice.mybatis.MyBatisModuleFactory;
import com.google.inject.Injector;

import static com.google.inject.Guice.createInjector;

public class ServicesFactory {

    private static final ServicesFactory instance = new ServicesFactory();

    private Injector guiceInjector;

    private Injector getTestInjector() {
        return createInjector(MyBatisModuleFactory.getMyBatisTestModule());
    }

    private ServicesFactory() {
    }

    public AuthServices getLabInfoServicesTesting() {
        if (guiceInjector == null) {
            guiceInjector = getTestInjector();
        }

        return guiceInjector.getInstance(AuthServices.class);
    }

    public static ServicesFactory getInstance() {
        return instance;
    }
}
