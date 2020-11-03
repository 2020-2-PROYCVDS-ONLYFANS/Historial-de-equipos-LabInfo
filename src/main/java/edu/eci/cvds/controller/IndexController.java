package edu.eci.cvds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "indexController", eager = true)
@RequestScoped
public class IndexController {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    public void redirectToHome() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
}
