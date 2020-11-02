package edu.eci.cvds.model.services;

import com.google.inject.Injector;
import edu.eci.cvds.model.mybatis.MyBatisModuleFactory;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;

public class LabInfoServicesFactory {

    private static final LabInfoServicesFactory instance = new LabInfoServicesFactory();

    private static Optional<Injector> optionalInjector;

    private Injector getTestInjector() {
        return createInjector(
                MyBatisModuleFactory.getInstance().getMyBatisTestModule()
        );
    }

    private LabInfoServicesFactory(){
        optionalInjector = Optional.empty();
    }

    public LabInfoServices getLabInfoServicesTesting() {
        if (!optionalInjector.isPresent()) {
            optionalInjector = Optional.of(getTestInjector());
        }

        return optionalInjector.get().getInstance(LabInfoServices.class);
    }

    public static LabInfoServicesFactory getInstance() {
        return instance;
    }
}
