package edu.eci.cvds.model.guice.shiro;

import com.google.inject.Provides;
import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.name.Names;
import edu.eci.cvds.model.dao.auth.AuthDAO;
import edu.eci.cvds.model.dao.shiro.AuthorizingRealmImpl;
import edu.eci.cvds.model.entities.role.RoleName;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.config.Ini;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URL;

public class ShiroWebModuleImpl extends ShiroWebModule {

    ServletContext servletContext; // to loadShiroIni()

    @Inject
    public ShiroWebModuleImpl(ServletContext servletContext) {
        super(servletContext);
        this.servletContext = servletContext; // to loadShiroIni()
    }

    @SuppressWarnings("all")
    protected void configFilterChain() {
        addFilterChain("/login.xhtml", AUTHC);
        addFilterChain("/logout.xhtml", LOGOUT);
        addFilterChain("/account/**", AUTHC);
        addFilterChain(
                "/admin/**",
                filterConfig(AUTHC),
                filterConfig(ROLES, RoleName.ROLE_ADMIN.toString()));
    }

    @Override
    protected void configureShiroWeb() {
        bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/login.xhtml");

        bindRealm().toInstance(new AuthorizingRealmImpl(
                new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME)
        ));

        bind(AuthDAO.class);

        configFilterChain();
    }

    @Provides
    @Singleton
    Ini loadShiroIni() throws MalformedURLException {
        URL iniUrl = servletContext.getResource("/WEB-INF/shiro.ini");
        return Ini.fromResourcePath("url:" + iniUrl.toExternalForm());
    }

    @Override
    protected void bindSessionManager(AnnotatedBindingBuilder<SessionManager> bind) {
        bind.to(DefaultWebSessionManager.class);
        bindConstant().annotatedWith(Names.named("shiro.globalSessionTimeout")).to(5000L);
        bindConstant().annotatedWith(Names.named("shiro.sessionIdUrlRewritingEnabled")).to(false);
        bind(Cookie.class).toInstance(new SimpleCookie("REMEMBER_ME"));
    }

    @Override
    protected void bindWebSecurityManager(AnnotatedBindingBuilder<? super WebSecurityManager> bind) {
        try {
            String cipherKey = loadShiroIni().getSectionProperty(
                    "main",
                    "securityManager.rememberMeManager.cipherKey" );
            DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
            CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
            rememberMeManager.setCipherKey( Base64.decode( cipherKey ) );
            securityManager.setRememberMeManager(rememberMeManager);
            bind.toInstance(securityManager);
        } catch ( MalformedURLException e ) {
            // for now just throw, you could just call
            // super.bindWebSecurityManager(bind) if you do not need rememberMe functionality
            throw new ConfigurationException( "securityManager.rememberMeManager.cipherKey must be set in shiro.ini." );
        }
    }
}
