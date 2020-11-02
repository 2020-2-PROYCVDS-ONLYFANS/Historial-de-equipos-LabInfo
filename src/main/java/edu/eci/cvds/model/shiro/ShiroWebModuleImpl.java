package edu.eci.cvds.model.shiro;

import edu.eci.cvds.model.shiro.realms.AuthorizingRealmImpl;
import org.apache.shiro.guice.web.ShiroWebModule;

import javax.servlet.ServletContext;

public class ShiroWebModuleImpl extends ShiroWebModule {

    public ShiroWebModuleImpl(ServletContext servletContext) {
        super(servletContext);
    }

    @Override
    protected void configureShiroWeb() {
        bindRealm().to(AuthorizingRealmImpl.class);
    }
}
