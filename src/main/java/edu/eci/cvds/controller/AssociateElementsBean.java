package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.ServicesException;
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

    @Inject
    AuthServices authServices;

    private ElementTypeName elementTypeName;
    private String elementReference;
    private String computerReference;

    private Boolean elementAvailable;
    private Long elementId;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(AssociateElementsBean.class);

    public void associate() {
        LOGGER.info("associate");
        if (elementAvailable) {
            String username = SecurityUtils.getSubject().getPrincipal().toString();
            try {
                Computer computer = computerServices.loadComputerByReference(computerReference);
                if (computer != null && !computer.isDiscarded()) {
                    Long userId = authServices.getUserIdByUsername(username);
                    computerServices.linkElementByIdsAndComputer(elementTypeName, userId, elementId, computer);
                    addMessage("Done!", "Successful association.", FacesMessage.SEVERITY_INFO);
                } else {
                    addMessage("Error!", "Computer not exists", FacesMessage.SEVERITY_ERROR);
                }
            } catch (ServicesException e) {
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
            Element element = elementServices.getElementByReference(elementReference);
            elementAvailable = element.isAvailable();
            elementId = element.getId();
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

    public ElementTypeName getElementTypeName() {
        return elementTypeName;
    }

    public void setElementTypeName(ElementTypeName elementTypeName) {
        this.elementTypeName = elementTypeName;
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
