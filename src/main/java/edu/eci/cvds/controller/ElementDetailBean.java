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
import edu.eci.cvds.model.entities.Novelty;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.NoveltyServices;
import edu.eci.cvds.model.services.ServicesException;

@ManagedBean(name = "elementDetailBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class ElementDetailBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private static final String ERROR_MESSAGE_SUMMARY = "Error!";

    private static final String INFO_MESSAGE_SUMMARY = "Info!";

    private boolean validateElement;

    private boolean activateSaveButton;

    private Element currentElement;
    private Element originalElement;

    private List<Novelty> noveltiesReport;

    @Inject
    private transient NoveltyServices noveltyServices;

    @Inject
    private transient ComputerServices computerServices;

    @Inject
    private transient ElementServices elementServices;

    @Inject
    private transient AuthServices authServices;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(ElementDetailBean.class);

    public void save() {
        try {
            Long userId = authServices
                    .getUserIdByUsername(SecurityUtils.getSubject().getPrincipal().toString());
            if (!originalElement.getReference().equals(currentElement.getReference())) {
                saveElementReference(userId);
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

    public void saveElementReference(Long userId) {
        try {
            computerServices.setReferenceById(userId, currentElement.getReference(),
                    originalElement.getId());
            addMessage(INFO_MESSAGE_SUMMARY, "Reference updated", FacesMessage.SEVERITY_INFO);
        } catch (ServicesException e) {
            e.printStackTrace();
            addMessage(ERROR_MESSAGE_SUMMARY, "Reference not updated", FacesMessage.SEVERITY_ERROR);
        }
    }

    private void updateSaveButton() {
        if (originalElement.getReference().equals(currentElement.getReference())) {
            activateSaveButton = false;
        } else {
            if (validateElement) {
                activateSaveButton = true;
            } else {
                activateSaveButton = false;
            }
        }
    }

    public void validateElementReference() {
        LOGGER.info("validateElementReference");
        if (currentElement.getReference().equals(originalElement.getReference())) {
            validateElement = true;
            addElementMessage(null, "Original", FacesMessage.SEVERITY_INFO);
        } else {
            try {
                Long elementId = elementServices.getIdByReference(currentElement.getReference());
                if (elementId == null) {
                    validateElement = true;
                    addElementMessage(null, "Available", FacesMessage.SEVERITY_INFO);
                } else {
                    validateElement = false;
                    addElementMessage(null, "Not available", FacesMessage.SEVERITY_ERROR);
                }
            } catch (ServicesException e) {
                e.printStackTrace();
            }
        }
        updateSaveButton();
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

    public void reset() {
        loadElement();
        loadNoveltiesReport();
        validateElement = true;
        activateSaveButton = false;
    }

    @Override
    public void init() {
        this.getInjector().injectMembers(this);
        loadElement();
        loadNoveltiesReport();
        validateElement = true;
        activateSaveButton = false;
    }

    private void loadNoveltiesReport() {
        LOGGER.info("loadNoveltiesReport");
        try {
            noveltiesReport = noveltyServices.getByElementId(originalElement.getId());
        } catch (ServicesException | NumberFormatException e) {
            e.printStackTrace();
        }

    }

    private void loadElement() {
        LOGGER.info("loadElement");
        originalElement = fetchElement();
        if (originalElement != null) {
            LOGGER.info("loadElement - if");
            currentElement = new Element(originalElement);
        } else {
            LOGGER.info("loadElement - else");
            currentElement = new Element();
        }
    }

    private Element fetchElement() {
        LOGGER.info("fetchElement");
        String backPage = "elements-report.xhtml";
        try {
            LOGGER.info("fetchElement - try");
            Long elementId;
            if (originalElement != null && originalElement.getId() != 0) {
                elementId = originalElement.getId();
            } else {
                elementId = getParamId(backPage);
            }
            Element element = elementServices.getElementById(elementId);
            if (element != null) {
                LOGGER.info("fetchElement - try - if");
                if (element.isDiscarded()) {
                    LOGGER.info("fetchElement - try - if - if");
                    FacesContext.getCurrentInstance().getExternalContext().redirect(backPage);
                } else {
                    LOGGER.info("fetchElement - try - if - else");
                    return element;
                }
            } else {
                LOGGER.info("fetchElement - try - else");
                FacesContext.getCurrentInstance().getExternalContext().redirect(backPage);
            }
        } catch (ServicesException | IOException e) {
            LOGGER.info("fetchElement - catch");
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
                        .redirect("elements-report.xhmtl");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getValidateElement() {
        return validateElement;
    }

    public void setValidateElement(boolean validateElement) {
        this.validateElement = validateElement;
    }

    public boolean getActivateSaveButton() {
        return activateSaveButton;
    }

    public void setActivateSaveButton(boolean activateSaveButton) {
        this.activateSaveButton = activateSaveButton;
    }

    public Element getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(Element currentElement) {
        this.currentElement = currentElement;
    }

    public Element getOriginalElement() {
        return originalElement;
    }

    public void setOriginalElement(Element originalElement) {
        this.originalElement = originalElement;
    }

    public List<Novelty> getNoveltiesReport() {
        return noveltiesReport;
    }

    public void setNoveltiesReport(List<Novelty> noveltiesReport) {
        this.noveltiesReport = noveltiesReport;
    }
}
