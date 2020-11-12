package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "discardElement")
@ViewScoped
public class DiscardElementBean extends BasePageBean {

    private String reference;

    @Inject
    private ElementServices elementServices;

    @SuppressWarnings("unused")
    private static final transient Logger LOGGER = LoggerFactory.getLogger(DiscardElementBean.class);

    public void discard() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            Element element = elementServices.loadElementByReference(reference);
            if (element.isAvailable()) {
                elementServices.discardElement(element.getId(), username);
                addMessage("Error!", "Element associated with a computer.", FacesMessage.SEVERITY_ERROR);
            } else {
                addMessage("Error!", "Element associated with a computer.", FacesMessage.SEVERITY_ERROR);
            }
        } catch (LabInfoServicesException e) {
            e.printStackTrace();
            addMessage("Fatal!", "Element not exists", FacesMessage.SEVERITY_FATAL);
            LOGGER.info(e.getMessage());
        }
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage("elementMessage", message);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
