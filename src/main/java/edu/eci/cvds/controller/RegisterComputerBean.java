package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.shiro.SecurityUtils;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "registerComputerBean")
@ViewScoped
public class RegisterComputerBean extends BasePageBean {

    @Inject
    private ComputerServices computerServices;

    @Inject
    private ElementServices elementServices;

    private String computerCaseReference;
    private String monitorReference;
    private String keyboardReference;
    private String mouseReference;

    private boolean existsComputerCase;
    private boolean existsMonitor;
    private boolean existsKeyboard;
    private boolean existsMouse;

    private String computerReference;

    @SuppressWarnings("unused")
    private static final transient Logger LOGGER = LoggerFactory.getLogger(RegisterComputerBean.class);

    public String updateMessage() {
        String message = buildMessage();
        LOGGER.info("message = " + message);
        return message;
    }

    public void register() {
        LOGGER.info("register");
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            computerServices.registerComputerWithReferences(
                    computerReference, computerCaseReference, monitorReference, keyboardReference, mouseReference,
                    existsComputerCase, existsMonitor, existsKeyboard, existsMouse, username);
            addMessage("Done", "Computer successfully registered.");
        } catch (Exception e) {
            LOGGER.info("register - catch");
            // e.printStackTrace();
            addMessage("System Error", "Please try again later.");
        }
    }

    public void addMessage(String summary, String detail) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String buildMessage() {
        LOGGER.info("buildMessage");
        if (!areSomeReferencesNull()) {
            Pair<Integer, String> messageAndCont = elementsMessageAndCont();
            LOGGER.info(messageAndCont.toString());
            if (messageAndCont.getValue0() != 0) {
                if (messageAndCont.getValue0() == 1) {
                    return "The " + messageAndCont.getValue1() + " does not exist, so it will be created." +
                            " The other components will be associated with this computer.";
                } else if (messageAndCont.getValue0() == 4) {
                    return "The " + messageAndCont.getValue1() + " do not exist, so they will be created.";
                } else {
                    return "The " + messageAndCont.getValue1() + " do not exist, so they will be created." +
                            " The other components will be associated with this computer.";
                }
            } else {
                return "The components will be associated with this computer.";
            }
        } else {
            return null;
        }
    }

    private Pair<Integer, String> elementsMessageAndCont() {
        LOGGER.info("elementsMessageAndCont");
        StringBuilder elementsMessage = new StringBuilder();
        List<String> elements = new ArrayList<>();
        int cont = 0;

        if (!existsComputerCase) {
            elements.add("computer case");
            cont++;
        } if (!existsMonitor) {
            elements.add("monitor");
            cont++;
        } if (!existsKeyboard) {
            elements.add("keyboard");
            cont++;
        } if (!existsMouse) {
            elements.add("mouse");
            cont++;
        }

        for (int i = 0; i < elements.size(); i++) {
            if (i == 0) {
                elementsMessage.append(elements.get(i));
            } else if (i != elements.size() - 1) {
                elementsMessage.append(", ").append(elements.get(i));
            } else {
                elementsMessage.append(" and ").append(elements.get(i));
            }
        }

        return new Pair<>(cont, elementsMessage.toString());
    }

    private boolean areSomeReferencesNull() {
        return computerCaseReference == null || monitorReference == null
                || keyboardReference == null || mouseReference == null;
    }

    public void askComputerCase() {
        try {
            existsComputerCase = elementServices.loadElementByReference(computerCaseReference) != null;
        } catch (LabInfoServicesException e) {
            LOGGER.info("askComputerCase - catch");
            existsComputerCase = false;
        }
    }

    public void askMonitor() {
        try {
            existsMonitor = elementServices.loadElementByReference(monitorReference) != null;
        } catch (LabInfoServicesException e) {
            LOGGER.info("askMonitor - catch");
            existsMonitor = false;
        }
    }

    public void askKeyboard() {
        try {
            existsKeyboard = elementServices.loadElementByReference(keyboardReference) != null;
        } catch (LabInfoServicesException e) {
            LOGGER.info("askKeyboard - catch");
            existsKeyboard = false;
        }
    }

    public void askMouse() {
        try {
            existsMouse = elementServices.loadElementByReference(mouseReference) != null;
        } catch (LabInfoServicesException e) {
            LOGGER.info("askMouse - catch");
            existsMouse = false;
        }
    }

    public void redirectToAdmin() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("admin/");
        } catch (IOException e) {
            LOGGER.info("redirectToAdmin - catch");
            e.printStackTrace();
        }
    }

    public String getComputerCaseReference() {
        return computerCaseReference;
    }

    public void setComputerCaseReference(String computerCaseReference) {
        this.computerCaseReference = computerCaseReference;
    }

    public String getMonitorReference() {
        return monitorReference;
    }

    public void setMonitorReference(String monitorReference) {
        this.monitorReference = monitorReference;
    }

    public String getKeyboardReference() {
        return keyboardReference;
    }

    public void setKeyboardReference(String keyboardReference) {
        this.keyboardReference = keyboardReference;
    }

    public String getMouseReference() {
        return mouseReference;
    }

    public void setMouseReference(String mouseReference) {
        this.mouseReference = mouseReference;
    }

    public String getComputerReference() {
        return computerReference;
    }

    public void setComputerReference(String computerReference) {
        this.computerReference = computerReference;
    }
}
