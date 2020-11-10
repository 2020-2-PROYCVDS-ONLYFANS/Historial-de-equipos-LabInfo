package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.LabInfoServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "registerElementBean", eager = true)
@ViewScoped
public class RegisterElementBean extends BasePageBean {

    @Inject
    LabInfoServices labInfoServices;

    private ElementTypeName computerCase = ElementTypeName.ETN_COMPUTER_CASE;
    private ElementTypeName monitor = ElementTypeName.ETN_MONITOR;
    private ElementTypeName keyboard = ElementTypeName.ETN_KEYBOARD;
    private ElementTypeName mouse = ElementTypeName.ETN_MOUSE;

    private ElementTypeName type;

    private String reference;

    @SuppressWarnings("unused")
    private static final transient Logger LOGGER = LoggerFactory.getLogger(RegisterElementBean.class);

    public void register() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            labInfoServices.registerElement(type, reference, username);
        } catch (LabInfoServicesException e) {
            e.printStackTrace();
        }
    }

    public void redirectToAdminPanel() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ElementTypeName getComputerCase() {
        return computerCase;
    }

    public void setComputerCase(ElementTypeName computerCase) {
        this.computerCase = computerCase;
    }

    public ElementTypeName getMonitor() {
        return monitor;
    }

    public void setMonitor(ElementTypeName monitor) {
        this.monitor = monitor;
    }

    public ElementTypeName getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(ElementTypeName keyboard) {
        this.keyboard = keyboard;
    }

    public ElementTypeName getMouse() {
        return mouse;
    }

    public void setMouse(ElementTypeName mouse) {
        this.mouse = mouse;
    }

    public ElementTypeName getType() {
        return type;
    }

    public void setType(ElementTypeName type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
