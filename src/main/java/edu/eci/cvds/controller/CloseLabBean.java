package edu.eci.cvds.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.google.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.LabServices;
import edu.eci.cvds.model.services.ServicesException;

@ManagedBean(name = "closeLabBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class CloseLabBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private String labName;

    @Inject
    private transient LabServices labServices;

    @Inject
    private transient AuthServices authServices;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(CloseLabBean.class);

    public void closeLab() {
        try {
            labServices.closeLabByName(
                    authServices.getUserIdByUsername(
                            SecurityUtils.getSubject().getPrincipal().toString()),
                    labServices.getLabByName(labName));
            this.addMessage("Done!", "Closed lab", FacesMessage.SEVERITY_INFO);
        } catch (ServicesException e) {
            this.addMessage("Not found", "Lab not found", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage("elementMessage", message);
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }
}
