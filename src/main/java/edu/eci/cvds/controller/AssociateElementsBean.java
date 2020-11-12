package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.computer.Computer;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "associateElementsBean")
@ViewScoped
public class AssociateElementsBean extends BasePageBean {

    @Inject
    ElementServices elementServices;

    @Inject
    ComputerServices computerServices;

    private ElementTypeName computerCase = ElementTypeName.ETN_COMPUTER_CASE;
    private ElementTypeName monitor = ElementTypeName.ETN_MONITOR;
    private ElementTypeName keyboard = ElementTypeName.ETN_KEYBOARD;
    private ElementTypeName mouse = ElementTypeName.ETN_MOUSE;

    private ElementTypeName type;
    private String elementReference;
    private String computerReference;

    private boolean elementAvailable;

    @SuppressWarnings("unused")
    private static final transient Logger LOGGER = LoggerFactory.getLogger(AssociateElementsBean.class);

    public void associate() {
        LOGGER.info("associate");
        if (elementAvailable) {
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            try {
                Computer computer = computerServices.loadComputerByReference(computerReference);
                if (computer != null && computer.isAvailable()) {
                    switch (type) {
                        case ETN_COMPUTER_CASE:
                            LOGGER.info("ETN_COMPUTER_CASE");
                            elementServices
                                    .associateComputerCaseByReferenceAndUsername(
                                            elementReference, username, computer);
                            break;
                        case ETN_MONITOR:
                            LOGGER.info("ETN_MONITOR");
                            elementServices
                                    .associateMonitorByReferenceAndUsername(
                                            elementReference, username, computer);
                            break;
                        case ETN_KEYBOARD:
                            LOGGER.info("ETN_KEYBOARD");
                            elementServices
                                    .associateKeyboardByReferenceAndUsername(
                                            elementReference, username, computer);
                            break;
                        case ETN_MOUSE:
                            LOGGER.info("ETN_MOUSE");
                            elementServices
                                    .associateMouseByReferenceAndUsername(
                                            elementReference, username, computer);
                            break;
                    }
                    addMessage("Done!", "Successful association.", FacesMessage.SEVERITY_INFO);
                } else {
                    addMessage("Error!", "Computer not exists", FacesMessage.SEVERITY_ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
                addMessage("System Error!", "Please try again later.", FacesMessage.SEVERITY_FATAL);
            }
        } else {
            addMessage("Error!", "Element is not available or not exists.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void askElementAvailable() {
        LOGGER.info("askElementAvailable");
        try {
            elementAvailable = elementServices.loadElementByReference(elementReference).isAvailable();
            if (elementAvailable) {
                addElementMessage("Info", "Element is available.", FacesMessage.SEVERITY_INFO);
            } else {
                addElementMessage("Warning!", "Element is not available.", FacesMessage.SEVERITY_WARN);
            }
        } catch (Exception e) {
            addElementMessage("Error!", "Element not exists.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void addElementMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addElementMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage("elementMessage", message);
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public String getElementReference() {
        return elementReference;
    }

    public void setElementReference(String elementReference) {
        this.elementReference = elementReference;
    }

    public String getComputerReference() {
        return computerReference;
    }

    public void setComputerReference(String computerReference) {
        this.computerReference = computerReference;
    }
}
