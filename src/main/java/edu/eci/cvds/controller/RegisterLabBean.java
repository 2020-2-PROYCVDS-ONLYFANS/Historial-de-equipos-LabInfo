package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Lab;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.LabServices;
import edu.eci.cvds.model.services.ServicesException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "registerLabBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class RegisterLabBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private Lab lab;
    private Computer computer;

    @Inject
    private AuthServices authServices;

    @Inject
    private ComputerServices computerServices;

    @Inject
    private LabServices labServices;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(RegisterLabBean.class);

    @PostConstruct
    public void init() {
        lab = new Lab();
        lab.setComputers(new ArrayList<>());
        computer = new Computer();
        getInjector().injectMembers(this);
    }

    public void registerLab() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            try {
                LOGGER.info("registerLab");
                LOGGER.info(lab.toString());
                labServices.registerLab(authServices.getUserIdByUsername(
                        SecurityUtils.getSubject().getPrincipal().toString()), lab);
                LOGGER.info(labServices.getLabByName(lab.getName()).toString());
                addMessage("Done!", "Successful registration.", FacesMessage.SEVERITY_INFO);
                reset();
            } catch (ServicesException e) {
                addMessage("Error!", "Please try again later.", FacesMessage.SEVERITY_FATAL);
                e.printStackTrace();
            }
        }
    }

    public void addComputer() {
        try {
            LOGGER.info("addComputer - try");
            computer = computerServices.getComputerByReference(computer.getReference());
            if (computer == null) {
                LOGGER.info("addComputer - if computer == null");
                addMessage("Not found", "This computer not exists.", FacesMessage.SEVERITY_ERROR);
            } else if (computer.isDiscarded()) {
                LOGGER.info("addComputer - else if computer.isDiscarded()");
                addMessage("Error!", "This computer is discarded.", FacesMessage.SEVERITY_ERROR);
            } else if (lab.getComputers().contains(computer)) {
                LOGGER.info("addComputer - try - else if lab.getComputers().contains(computer)");
                addMessage("Duplicated", "This computer has already been added.",
                        FacesMessage.SEVERITY_WARN);
            } else {
                LOGGER.info("addComputer - try - else");
                lab.getComputers().add(computer);
                computer = new Computer();
            }
        } catch (ServicesException e) {
            LOGGER.info("addComputer - catch");
            e.printStackTrace();
            addMessage("Not found", "This computer not exists.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void reset() {
        LOGGER.info("reset");
        lab = new Lab();
        lab.setComputers(new ArrayList<>());
        computer = new Computer();
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

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
