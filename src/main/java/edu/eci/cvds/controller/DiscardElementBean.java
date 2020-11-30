package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.ServicesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "discardElement")
@ViewScoped
@SuppressWarnings("deprecation")
public class DiscardElementBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private String reference;

    @Inject
    private ElementServices elementServices;

    @Inject
    private AuthServices authServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(DiscardElementBean.class);

    public void discard() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            Element element = elementServices.getElementByReference(reference);
            if (element.isAvailable()) {
                elementServices.discard(element.getType().getName(),
                        authServices.getUserIdByUsername(username), element.getId(), null);
                addMessage("Done!", "Discarded successful.", FacesMessage.SEVERITY_INFO);
            } else {
                addMessage("Error!", "Element associated with a computer.",
                        FacesMessage.SEVERITY_ERROR);
            }
        } catch (ServicesException e) {
            LOGGER.info(e.getMessage());
            addMessage("Fatal!", "Element not exists", FacesMessage.SEVERITY_FATAL);
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
