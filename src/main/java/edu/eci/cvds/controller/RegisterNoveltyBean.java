package edu.eci.cvds.controller;

import com.google.inject.Inject;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "registerNoveltyBean")
@ViewScoped
public class RegisterNoveltyBean extends BasePageBean {

    private String reference;
    private String type;
    private String title;
    private String detail;

    @Inject
    private NoveltyServices noveltyServices;

    private static final transient Logger LOGGER = LoggerFactory.getLogger(RegisterNoveltyBean.class);

    public void register() {
        LOGGER.info("register");
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        try {
            LOGGER.info("reference = " + reference);
            LOGGER.info("type = " + type);
            LOGGER.info("title = " + title);
            LOGGER.info("detail = " + detail);
            LOGGER.info("username = " + username);

            switch (type) {
                case "computer":
                    LOGGER.info("computer");
                    noveltyServices.createByComputerReferenceAndUsername(
                            username, reference, title, detail);
                    break;

                case "computerCase":
                    LOGGER.info("computerCase");
                    noveltyServices.createByElementReferenceAndUsername(
                            ElementTypeName.ETN_COMPUTER_CASE,
                            username, reference, title, detail);
                    break;

                case "monitor":
                    LOGGER.info("monitor");
                    noveltyServices.createByElementReferenceAndUsername(
                            ElementTypeName.ETN_MONITOR,
                            username, reference, title, detail);
                    break;

                case "keyboard":
                    LOGGER.info("keyboard");
                    noveltyServices.createByElementReferenceAndUsername(
                            ElementTypeName.ETN_KEYBOARD,
                            username, reference, title, detail);
                    break;

                case "mouse":
                    LOGGER.info("mouse");
                    noveltyServices.createByElementReferenceAndUsername(
                            ElementTypeName.ETN_MOUSE,
                            username, reference, title, detail);
                    break;
            } addMessage("Done!", "Successful registration.", FacesMessage.SEVERITY_INFO);
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
