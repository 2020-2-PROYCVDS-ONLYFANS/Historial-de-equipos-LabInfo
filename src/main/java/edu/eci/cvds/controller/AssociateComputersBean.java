package edu.eci.cvds.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.google.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Lab;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.LabServices;
import edu.eci.cvds.model.services.ServicesException;

@ManagedBean(name = "associateComputersBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class AssociateComputersBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private Lab lab;
    private String computerRef;

    @Inject
    private AuthServices authServices;

    @Inject
    private ComputerServices computerServices;

    @Inject
    private LabServices labServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(AssociateComputersBean.class);

    @PostConstruct
    public void init() {
        lab = new Lab();
        getInjector().injectMembers(this);
    }

    public void findLab() {
        try {
            LOGGER.info("findLab - try");
            Lab findedLab = labServices.getLabByName(lab.getName());
            LOGGER.info(findedLab.toString());
            if (lab.getClosingDate() == null) {
                lab = findedLab;
                addMessage("Done!", "Lab found.", FacesMessage.SEVERITY_INFO);
            } else {
                addMessage("Closed!", "Closed lab", FacesMessage.SEVERITY_INFO);
            }
        } catch (ServicesException e) {
            addMessage("Not found!", "Sorry, lab not found.", FacesMessage.SEVERITY_ERROR);
            e.printStackTrace();
        }
    }

    public void addComputer() {
        if (lab.getId() == 0) {
            addMessage("Not found", "You must first find a laboratory",
                    FacesMessage.SEVERITY_FATAL);
        } else {
            try {
                LOGGER.info("addComputer - try");
                Computer computer = computerServices.getComputerByReference(computerRef);
                if (computer == null) {
                    LOGGER.info("addComputer - if computer == null");
                    addMessage("Not found", "This computer not exists.",
                            FacesMessage.SEVERITY_ERROR);
                } else if (computer.isDiscarded()) {
                    LOGGER.info("addComputer - else if computer.isDiscarded()");
                    addMessage("Error!", "This computer is discarded.",
                            FacesMessage.SEVERITY_ERROR);
                } else if (lab.getComputers().contains(computer)) {
                    LOGGER.info(
                            "addComputer - try - else if lab.getComputers().contains(computer)");
                    addMessage("Duplicated", "This computer has already been added.",
                            FacesMessage.SEVERITY_WARN);
                } else {
                    Long userId = authServices.getUserIdByUsername(
                            SecurityUtils.getSubject().getPrincipal().toString());
                    LOGGER.info("addComputer - try - else");
                    LOGGER.info("lab.getId() = " + lab.getId());
                    if (computer.isAvailable()) {
                        labServices.registerComputerToLabByIds(userId, computer.getId(),
                                lab.getId());
                    } else {
                        labServices.unlinkLabComputerByIds(userId, computer.getId(),
                                labServices.getLabIdByLinkedComputerId(computer.getId()));
                        labServices.registerComputerToLabByIds(userId, computer.getId(),
                                lab.getId());
                    }
                    lab.getComputers().add(computer);
                    computerRef = null;
                    addMessage("Done!", "Computer added.", FacesMessage.SEVERITY_INFO);
                }
            } catch (ServicesException e) {
                LOGGER.info("addComputer - catch");
                e.printStackTrace();
                addMessage("Not found", "This computer not exists.", FacesMessage.SEVERITY_ERROR);
            }
        }
    }

    public void removeComputer(Computer computer) {
        LOGGER.info("removeComputer");
        LOGGER.info(computer.toString());
        try {
            LOGGER.info("removeComputer - try");
            labServices.unlinkLabComputerByIds(
                    authServices.getUserIdByUsername(
                            SecurityUtils.getSubject().getPrincipal().toString()),
                    computer.getId(), lab.getId());
            lab.getComputers().remove(computer);
            addMessage("Done!", "Lab removed.", FacesMessage.SEVERITY_INFO);
        } catch (ServicesException e) {
            LOGGER.info("removeComputer - catch");
            e.printStackTrace();
            addMessage("Error!", "Please try again later.", FacesMessage.SEVERITY_FATAL);
        }
    }

    public void reset() {
        LOGGER.info("reset");
        lab = new Lab();
        computerRef = null;
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage("elementMessage", message);
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public String getComputerRef() {
        return computerRef;
    }

    public void setComputerRef(String computerRef) {
        this.computerRef = computerRef;
    }
}
