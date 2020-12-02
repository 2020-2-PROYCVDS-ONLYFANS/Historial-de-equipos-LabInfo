package edu.eci.cvds.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.google.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.Novelty;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.NoveltyServices;
import edu.eci.cvds.model.services.ServicesException;

@ManagedBean(name = "computerDetailBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class ComputerDetailBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private static final String ERROR_MESSAGE_SUMMARY = "Error!";

    private static final String INFO_MESSAGE_SUMMARY = "Info!";

    private boolean validateComputer;
    private boolean validateComputerCase;
    private boolean validateMonitor;
    private boolean validateKeyboard;
    private boolean validateMouse;

    private boolean activateSaveButton;

    private Computer currentComputer;
    private Computer originalComputer;

    private List<Novelty> noveltiesReport;

    @Inject
    private transient NoveltyServices noveltyServices;

    @Inject
    private transient ComputerServices computerServices;

    @Inject
    private transient ElementServices elementServices;

    @Inject
    private transient AuthServices authServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(ComputerDetailBean.class);

    public void save() {
        try {
            Long userId = authServices
                    .getUserIdByUsername(SecurityUtils.getSubject().getPrincipal().toString());
            if (!originalComputer.getReference().equals(currentComputer.getReference())) {
                saveComputerReference(userId);
            }
            if (!originalComputer.getComputerCase().getReference()
                    .equals(currentComputer.getComputerCase().getReference())) {
                saveElement(userId, currentComputer.getComputerCase());
            }
            if (!originalComputer.getMonitor().getReference()
                    .equals(currentComputer.getMonitor().getReference())) {
                saveElement(userId, currentComputer.getMonitor());
            }
            if (!originalComputer.getKeyboard().getReference()
                    .equals(currentComputer.getKeyboard().getReference())) {
                saveElement(userId, currentComputer.getKeyboard());
            }
            if (!originalComputer.getMouse().getReference()
                    .equals(currentComputer.getMouse().getReference())) {
                saveElement(userId, currentComputer.getMouse());
            }
        } catch (ServicesException e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("..");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        reset();
    }

    public void saveComputerReference(Long userId) {
        try {
            computerServices.setReferenceById(userId, currentComputer.getReference(),
                    originalComputer.getId());
            addMessage(INFO_MESSAGE_SUMMARY, "Reference updated", FacesMessage.SEVERITY_INFO);
        } catch (ServicesException e) {
            e.printStackTrace();
            addMessage(ERROR_MESSAGE_SUMMARY, "Reference not updated", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void saveElement(Long userId, Element element) {
        try {
            Long elementId = elementServices.getIdByReference(element.getReference());
            if (elementId != null && elementId != 0) {
                computerServices.unlinkElement(element.getType().getName(), userId,
                        elementServices.getIdByReference(originalComputer
                                .getElement(element.getType().getName()).getReference()),
                        originalComputer.getId());
                computerServices.setElementIdByIds(element.getType().getName(), userId,
                        element.getId(), originalComputer.getId());
                switch (element.getType().getName()) {
                    case ETN_COMPUTER_CASE:
                        addMessage(INFO_MESSAGE_SUMMARY, "Computer case updated",
                                FacesMessage.SEVERITY_INFO);
                        break;
                    case ETN_MONITOR:
                        addMessage(INFO_MESSAGE_SUMMARY, "Monitor updated",
                                FacesMessage.SEVERITY_INFO);
                        break;
                    case ETN_KEYBOARD:
                        addMessage(INFO_MESSAGE_SUMMARY, "Keyboard updated",
                                FacesMessage.SEVERITY_INFO);
                        break;
                    case ETN_MOUSE:
                        addMessage(INFO_MESSAGE_SUMMARY, "Mouse updated",
                                FacesMessage.SEVERITY_INFO);
                        break;
                }
            } else {
                elementServices.registerElement(element.getType().getName(), element.getReference(),
                        userId);
                Element registeredElement =
                        elementServices.getElementByReference(element.getReference());
                computerServices.unlinkElement(element.getType().getName(), userId,
                        originalComputer.getElement(element.getType().getName()).getId(),
                        originalComputer.getId());
                computerServices.setElementIdByIds(registeredElement.getType().getName(), userId,
                        registeredElement.getId(), originalComputer.getId());
                originalComputer.setElement(registeredElement.getType().getName(),
                        registeredElement);
                currentComputer.setElement(registeredElement.getType().getName(),
                        new Element(registeredElement));
                switch (element.getType().getName()) {
                    case ETN_COMPUTER_CASE:
                        addMessage(INFO_MESSAGE_SUMMARY, "New computer case linked",
                                FacesMessage.SEVERITY_INFO);
                        break;
                    case ETN_MONITOR:
                        addMessage(INFO_MESSAGE_SUMMARY, "New monitor linked",
                                FacesMessage.SEVERITY_INFO);
                        break;
                    case ETN_KEYBOARD:
                        addMessage(INFO_MESSAGE_SUMMARY, "New keyboard linked",
                                FacesMessage.SEVERITY_INFO);
                        break;
                    case ETN_MOUSE:
                        addMessage(INFO_MESSAGE_SUMMARY, "New mouse linked",
                                FacesMessage.SEVERITY_INFO);
                        break;
                }
            }
        } catch (ServicesException e) {
            e.printStackTrace();
            switch (element.getType().getName()) {
                case ETN_COMPUTER_CASE:
                    addMessage(ERROR_MESSAGE_SUMMARY, "Error saving computer case",
                            FacesMessage.SEVERITY_ERROR);
                    break;
                case ETN_MONITOR:
                    addMessage(ERROR_MESSAGE_SUMMARY, "Error saving monitor",
                            FacesMessage.SEVERITY_ERROR);
                    break;
                case ETN_KEYBOARD:
                    addMessage(ERROR_MESSAGE_SUMMARY, "Error saving keyboard",
                            FacesMessage.SEVERITY_ERROR);
                    break;
                case ETN_MOUSE:
                    addMessage(ERROR_MESSAGE_SUMMARY, "Error saving mouse",
                            FacesMessage.SEVERITY_ERROR);
                    break;
            }
        }
    }

    public void validateMouse() {
        LOGGER.info("validateKeyboard");
        validateElement(ElementTypeName.ETN_MOUSE);
    }

    public void validateKeyboard() {
        LOGGER.info("validateKeyboard");
        validateElement(ElementTypeName.ETN_KEYBOARD);
    }

    public void validateMonitor() {
        LOGGER.info("validateMonitor");
        validateElement(ElementTypeName.ETN_MONITOR);
    }

    public void validateComputerCase() {
        LOGGER.info("validateComputerCase");
        validateElement(ElementTypeName.ETN_COMPUTER_CASE);
    }

    private void validateElement(ElementTypeName typeName) {
        LOGGER.info("validateElement");
        try {
            if (originalComputer.getElement(typeName).getReference()
                    .equals(currentComputer.getElement(typeName).getReference())) {
                updateValidateElement(typeName, true);
                addElementMessage(typeName, null, "Original", FacesMessage.SEVERITY_INFO);
            } else {
                Element element = elementServices
                        .getElementByReference(currentComputer.getComputerCase().getReference());
                if (element == null) {
                    currentComputer.getElement(typeName).setId(0);
                    updateValidateElement(typeName, true);
                    addElementMessage(typeName, null, "Element will be created.",
                            FacesMessage.SEVERITY_INFO);
                } else {
                    if (element.isAvailable() && !element.isDiscarded()) {
                        currentComputer.setElement(typeName, element);
                        updateValidateElement(typeName, true);
                        addElementMessage(typeName, null, "Available", FacesMessage.SEVERITY_INFO);
                    } else if (!element.isAvailable()) {
                        updateValidateElement(typeName, false);
                        addElementMessage(typeName, null, "Not available",
                                FacesMessage.SEVERITY_ERROR);
                    } else if (element.isDiscarded()) {
                        updateValidateElement(typeName, false);
                        addElementMessage(typeName, null, "Discarded", FacesMessage.SEVERITY_ERROR);
                    }
                }
            }
        } catch (ServicesException e) {
            e.printStackTrace();
        }
    }

    private void updateValidateElement(ElementTypeName typeName, boolean validateElement) {
        switch (typeName) {
            case ETN_COMPUTER_CASE:
                validateComputerCase = validateElement;
                break;
            case ETN_MONITOR:
                validateMonitor = validateElement;
                break;
            case ETN_KEYBOARD:
                validateKeyboard = validateElement;
                break;
            case ETN_MOUSE:
                validateMouse = validateElement;
                break;
            default:
                break;
        }
        updateSaveButton();
    }

    private void updateSaveButton() {
        if (originalComputer.getReference().equals(currentComputer.getReference())
                && originalComputer.getComputerCase().getReference()
                        .equals(currentComputer.getComputerCase().getReference())
                && originalComputer.getMonitor().getReference()
                        .equals(currentComputer.getMonitor().getReference())
                && originalComputer.getKeyboard().getReference()
                        .equals(currentComputer.getKeyboard().getReference())
                && originalComputer.getMouse().getReference()
                        .equals(currentComputer.getMouse().getReference())) {
            activateSaveButton = false;
        } else {
            if (validateComputer && validateComputerCase && validateMonitor && validateKeyboard
                    && validateMouse) {
                activateSaveButton = true;
            } else {
                activateSaveButton = false;
            }
        }
    }

    public void validateComputerReference() {
        LOGGER.info("validateComputer");
        if (currentComputer.getReference().equals(originalComputer.getReference())) {
            validateComputer = true;
            addComputerMessage(null, "Original", FacesMessage.SEVERITY_INFO);
        } else {
            try {
                Long computerId = computerServices.getIdByReference(currentComputer.getReference());
                if (computerId == null) {
                    validateComputer = true;
                    addComputerMessage(null, "Available", FacesMessage.SEVERITY_INFO);
                } else {
                    validateComputer = false;
                    addComputerMessage(null, "Not available", FacesMessage.SEVERITY_ERROR);
                }
            } catch (ServicesException e) {
                e.printStackTrace();
            }
        }
        updateSaveButton();
    }

    private void addElementMessage(ElementTypeName typeName, String summary, String detail,
            FacesMessage.Severity severity) {
        LOGGER.info("addElementMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        switch (typeName) {
            case ETN_COMPUTER_CASE:
                FacesContext.getCurrentInstance().addMessage("computerCaseMessage", message);
                break;
            case ETN_MONITOR:
                FacesContext.getCurrentInstance().addMessage("monitorMessage", message);
                break;
            case ETN_KEYBOARD:
                FacesContext.getCurrentInstance().addMessage("keyboardMessage", message);
                break;
            case ETN_MOUSE:
                FacesContext.getCurrentInstance().addMessage("mouseMessage", message);
                break;
            default:
                break;
        }
    }

    public void addComputerMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addComputerMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage("computerMessage", message);
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void reset() {
        loadComputer();
        loadNoveltiesReport();
        validateComputer = true;
        validateComputerCase = true;
        validateMonitor = true;
        validateKeyboard = true;
        validateMouse = true;
        activateSaveButton = false;
    }

    @Override
    public void init() {
        this.getInjector().injectMembers(this);
        loadComputer();
        loadNoveltiesReport();
        validateComputer = true;
        validateComputerCase = true;
        validateMonitor = true;
        validateKeyboard = true;
        validateMouse = true;
        activateSaveButton = false;
    }

    private void loadNoveltiesReport() {
        LOGGER.info("loadNoveltiesReport");
        try {
            noveltiesReport = noveltyServices.getByComputerId(originalComputer.getId());
        } catch (ServicesException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void loadComputer() {
        LOGGER.info("loadComputer");
        originalComputer = fetchComputer();
        if (originalComputer != null) {
            LOGGER.info("loadComputer - if");
            currentComputer = new Computer(originalComputer);
        } else {
            LOGGER.info("loadComputer - else");
            currentComputer = new Computer();
        }
    }

    private Computer fetchComputer() {
        LOGGER.info("fetchComputer");
        String backPage = "computers-report.xhtml";
        try {
            LOGGER.info("fetchComputer - try");
            Long computerId;
            if (originalComputer != null && originalComputer.getId() != 0) {
                computerId = originalComputer.getId();
            } else {
                computerId = getParamId(backPage);
            }
            Computer computer = computerServices.getComputerById(computerId);
            if (computer != null) {
                LOGGER.info("fetchComputer - try - if");
                if (computer.isDiscarded()) {
                    LOGGER.info("fetchComputer - try - if - if");
                    FacesContext.getCurrentInstance().getExternalContext().redirect(backPage);
                } else {
                    LOGGER.info("fetchComputer - try - if - else");
                    return computer;
                }
            } else {
                LOGGER.info("fetchComputer - try - else");
                FacesContext.getCurrentInstance().getExternalContext().redirect(backPage);
            }
        } catch (ServicesException | IOException e) {
            LOGGER.info("fetchComputer - catch");
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(backPage);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    private Long getParamId(String backPage) {
        try {
            return Long.valueOf(getParam("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(backPage);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    private String getParam(String param) {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return params.get(param);
    }

    public void goBack() {
        boolean flag = FacesContext.getCurrentInstance().getExternalContext().isResponseCommitted();
        if (!flag) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("computers-report.xhmtl");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getValidateComputer() {
        return validateComputer;
    }

    public void setValidateComputer(boolean validateComputer) {
        this.validateComputer = validateComputer;
    }

    public boolean getValidateComputerCase() {
        return validateComputerCase;
    }

    public void setValidateComputerCase(boolean validateComputerCase) {
        this.validateComputerCase = validateComputerCase;
    }

    public boolean getValidateMonitor() {
        return validateMonitor;
    }

    public void setValidateMonitor(boolean validateMonitor) {
        this.validateMonitor = validateMonitor;
    }

    public boolean getValidateKeyboard() {
        return validateKeyboard;
    }

    public void setValidateKeyboard(boolean validateKeyboard) {
        this.validateKeyboard = validateKeyboard;
    }

    public boolean getValidateMouse() {
        return validateMouse;
    }

    public void setValidateMouse(boolean validateMouse) {
        this.validateMouse = validateMouse;
    }

    public boolean getActivateSaveButton() {
        return activateSaveButton;
    }

    public void setActivateSaveButton(boolean activateSaveButton) {
        this.activateSaveButton = activateSaveButton;
    }

    public Computer getCurrentComputer() {
        return currentComputer;
    }

    public void setCurrentComputer(Computer currentComputer) {
        this.currentComputer = currentComputer;
    }

    public Computer getOriginalComputer() {
        return originalComputer;
    }

    public void setOriginalComputer(Computer originalComputer) {
        this.originalComputer = originalComputer;
    }

    public List<Novelty> getNoveltiesReport() {
        return noveltiesReport;
    }

    public void setNoveltiesReport(List<Novelty> noveltiesReport) {
        this.noveltiesReport = noveltiesReport;
    }
}
