package edu.eci.cvds.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "loginBean", eager = true)
@ViewScoped
public class LoginBean extends BasePageBean {

    private String username;
    private String password;
    private boolean rememberMe;
    private String message;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);

    public void doLogin() {
        LOGGER.info("doLogin");
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            LOGGER.info("doLogin - !currentUser.isAuthenticated()");
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe);
            try {
                LOGGER.info("doLogin - if !currentUser.isAuthenticated() - try");
                currentUser.login(token);
                message = "Successful login";
            } catch (UnknownAccountException uae) {
                LOGGER.info("doLogin - if !currentUser.isAuthenticated() - catch UnknownAccountException");
                message = "There is no user with username of " + token.getPrincipal();
            } catch (IncorrectCredentialsException ice) {
                LOGGER.info("doLogin - if !currentUser.isAuthenticated() - catch IncorrectCredentialsException");
                message = "Password for account " + token.getPrincipal() + " was incorrect!";
            } catch (LockedAccountException lae) {
                LOGGER.info("doLogin - if !currentUser.isAuthenticated() - catch LockedAccountException");
                message = "The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.";
            } catch (AuthenticationException ae) {
                LOGGER.info("doLogin - if !currentUser.isAuthenticated() - catch AuthenticationException");
                message = "Pair username/password was incorrect!.";
            }
        }
    }

    public void redirectToAccount() {
        LOGGER.info("redirectToAccount");
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            LOGGER.info("redirectToAccount - if currentUser.isAuthenticated()");
            try {
                LOGGER.info("redirectToAccount - if currentUser.isAuthenticated() - try");
                FacesContext.getCurrentInstance().getExternalContext().redirect("account/");
            } catch (IOException e) {
                LOGGER.info("redirectToAccount - if currentUser.isAuthenticated() - catch IOException");
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
