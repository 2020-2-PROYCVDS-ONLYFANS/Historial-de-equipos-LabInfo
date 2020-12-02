package edu.eci.cvds.controller;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "indexBean", eager = true)
@RequestScoped
@SuppressWarnings("deprecation")
public class IndexBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    public void redirectToHome() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().dispatch("home.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
