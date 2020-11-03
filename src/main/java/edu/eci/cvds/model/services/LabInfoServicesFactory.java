package edu.eci.cvds.model.services;

import edu.eci.cvds.model.guice.mybatis.MyBatisModuleFactory;
import com.google.inject.Injector;

import static com.google.inject.Guice.createInjector;

public class LabInfoServicesFactory {

    private static final LabInfoServicesFactory instance = new LabInfoServicesFactory();

    private Injector guiceInjector;

    private Injector getTestInjector() {
        return createInjector(
                MyBatisModuleFactory.getInstance().getMyBatisTestModule()
        );
    }

    private LabInfoServicesFactory(){ }

    public LabInfoServices getLabInfoServicesTesting() {
        if (guiceInjector == null) {
            guiceInjector = getTestInjector();
        }

        return guiceInjector.getInstance(LabInfoServices.class);
    }

    public static LabInfoServicesFactory getInstance() {
        return instance;
    }
}
