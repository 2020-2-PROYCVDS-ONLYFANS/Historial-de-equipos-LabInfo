package edu.eci.cvds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "indexBean", eager = true)
@RequestScoped
public class IndexBean extends BasePageBean {

    private static final transient Logger LOGGER = LoggerFactory.getLogger(IndexBean.class);

    public void redirectToHome() {
        LOGGER.info("redirectToHome");
        try {
            LOGGER.info("redirectToHome - try");
            FacesContext.getCurrentInstance().getExternalContext().dispatch("home.xhtml");
        } catch (IOException e) {
            LOGGER.info("redirectToHome - catch IOException");
            e.printStackTrace();
        }
    }
}
