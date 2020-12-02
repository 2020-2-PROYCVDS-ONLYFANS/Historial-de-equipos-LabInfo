package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "registerNoveltyBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class RegisterNoveltyBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private String reference;
    private String type;
    private String title;
    private String detail;

    @Inject
    private transient NoveltyServices noveltyServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(RegisterNoveltyBean.class);

    public void register() {
        LOGGER.info("register");
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            String message = String.format("reference = %s", reference);
            LOGGER.info(message);
            message = String.format("type = %s", type);
            LOGGER.info(message);
            message = String.format("title = %s", title);
            LOGGER.info(message);
            message = String.format("detail = %s", detail);
            LOGGER.info(message);
            message = String.format("username = %s", username);
            LOGGER.info(message);

            switch (type) {
                case "computer":
                    LOGGER.info("computer");
                    noveltyServices.createByComputerReferenceAndUsername(username, reference, title,
                            detail);
                    break;
                case "computerCase":
                    LOGGER.info("computerCase");
                    noveltyServices.createByElementReferenceAndUsername(
                            ElementTypeName.ETN_COMPUTER_CASE, username, reference, title, detail);
                    break;
                case "monitor":
                    LOGGER.info("monitor");
                    noveltyServices.createByElementReferenceAndUsername(ElementTypeName.ETN_MONITOR,
                            username, reference, title, detail);
                    break;
                case "keyboard":
                    LOGGER.info("keyboard");
                    noveltyServices.createByElementReferenceAndUsername(
                            ElementTypeName.ETN_KEYBOARD, username, reference, title, detail);
                    break;
                case "mouse":
                    LOGGER.info("mouse");
                    noveltyServices.createByElementReferenceAndUsername(ElementTypeName.ETN_MOUSE,
                            username, reference, title, detail);
                    break;
                default:
                    addMessage("Error", "System error!", FacesMessage.SEVERITY_ERROR);
            }
            addMessage("Done!", "Successful registration.", FacesMessage.SEVERITY_INFO);
        } catch (ServicesException e) {
            LOGGER.info("register - catch");
            addMessage("Error!", "Element or computer not exists.", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
