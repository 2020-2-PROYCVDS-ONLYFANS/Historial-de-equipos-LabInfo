package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ServicesException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

@ManagedBean(name = "discardComputerBean")
@Transactional
public class DiscardComputerBean extends BasePageBean {

    private String computerReference;
    private boolean discardComputerCase;
    private boolean discardMonitor;
    private boolean discardKeyboard;
    private boolean discardMouse;

    @Inject
    private ComputerServices computerServices;

    @Inject
    private AuthServices authServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(DiscardComputerBean.class);

    public void discardComputer() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            Computer computer = computerServices.loadComputerByReference(computerReference);
            if (!computer.isDiscarded()) {
                computerServices.discard(
                        authServices.getUserIdByUsername(username), computer, discardComputerCase,
                        discardMonitor, discardKeyboard, discardMouse);
                addMessage("Done!", "Computer discarded",
                        FacesMessage.SEVERITY_INFO);
            } else {
                addMessage("Error!", "Computer discarded already",
                        FacesMessage.SEVERITY_ERROR);
            }
        } catch (ServicesException e) {
            e.printStackTrace();
            addMessage("Fatal!", "Computer not exists",
                    FacesMessage.SEVERITY_FATAL);
        }
    }

    public void addMessage(
            String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage("elementMessage", message);
    }

    public String getComputerReference() {
        return computerReference;
    }

    public void setComputerReference(String computerReference) {
        this.computerReference = computerReference;
    }

    public boolean isDiscardComputerCase() {
        return discardComputerCase;
    }

    public void setDiscardComputerCase(boolean discardComputerCase) {
        this.discardComputerCase = discardComputerCase;
    }

    public boolean isDiscardMonitor() {
        return discardMonitor;
    }

    public void setDiscardMonitor(boolean discardMonitor) {
        this.discardMonitor = discardMonitor;
    }

    public boolean isDiscardKeyboard() {
        return discardKeyboard;
    }

    public void setDiscardKeyboard(boolean discardKeyboard) {
        this.discardKeyboard = discardKeyboard;
    }

    public boolean isDiscardMouse() {
        return discardMouse;
    }

    public void setDiscardMouse(boolean discardMouse) {
        this.discardMouse = discardMouse;
    }
}
