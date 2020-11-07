package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.entities.role.RoleName;
import edu.eci.cvds.model.services.LabInfoServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name = "registerElementBean", eager = true)
@ViewScoped
public class RegisterElementBean extends BasePageBean implements Serializable {

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
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated() && currentUser.hasRole(RoleName.ROLE_ADMIN.toString())) {
            try {
                labInfoServices.registerElement(type, reference); // register element

                labInfoServices
                        .addElementHistoryByReferenceAndUsername(
                                reference, currentUser.getPrincipal().toString(), "Created"
                        ); // update history
            } catch (LabInfoServicesException e) {
                e.printStackTrace();
            }
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
