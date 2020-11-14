package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.ServicesException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "registerElementBean")
@ViewScoped
public class RegisterElementBean extends BasePageBean {

    @Inject
    ElementServices elementServices;

    private ElementTypeName typeName;

    private String reference;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(RegisterElementBean.class);

    public void register() {
        LOGGER.info("register");
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            LOGGER.info("register - try");
            elementServices.registerElement(typeName, reference, username);
        } catch (ServicesException e) {
            LOGGER.info("register - catch");
            e.printStackTrace();
        }
    }

    public void redirectToAdminPanel() {
        LOGGER.info("redirectToAdminPanel");
        try {
            LOGGER.info("redirectToAdminPanel - try");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/");
        } catch (IOException e) {
            LOGGER.info("redirectToAdminPanel - catch");
            e.printStackTrace();
        }
    }

    public ElementTypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(ElementTypeName typeName) {
        this.typeName = typeName;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
