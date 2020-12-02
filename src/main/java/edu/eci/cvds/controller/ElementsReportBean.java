package edu.eci.cvds.controller;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.google.inject.Inject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.ServicesException;

@ManagedBean(name = "elementsReportBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class ElementsReportBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private List<Element> elements;
    private List<Element> filteredElements;
    private List<Element> selectedElements;

    @Inject
    private transient ComputerServices computerServices;

    @Inject
    private transient ElementServices elementServices;

    @Inject
    private transient AuthServices authServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(ElementsReportBean.class);

    public void delete() {
        if (selectedElements != null && !selectedElements.isEmpty()) {
            try {
                Long userId = authServices
                        .getUserIdByUsername(SecurityUtils.getSubject().getPrincipal().toString());
                for (Element element : selectedElements) {
                    Long computerId = computerServices.getIdByElementId(element.getType().getName(),
                            element.getId());
                    elementServices.discard(element.getType().getName(), userId, element.getId(),
                            computerId);
                }
                addMessage("Info", "Discarded elements", FacesMessage.SEVERITY_INFO);
            } catch (ServicesException e) {
                addMessage("Error", "System error", FacesMessage.SEVERITY_FATAL);
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("..");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            selectedElements.clear();
            try {
                elements = elementServices.getActiveElements();
            } catch (ServicesException e) {
                e.printStackTrace();
                addMessage("Not found", "Active elements not found", FacesMessage.SEVERITY_INFO);
            }
        }
    }

    public boolean globalFilterFunction(Object value, Object filter) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Element element = (Element) value;
        return element.getReference().toLowerCase().contains(filterText)
                || element.getType().getName().toString().toLowerCase().contains(filterText)
                || element.getId() < filterInt;
    }

    @Override
    public void init() {
        this.getInjector().injectMembers(this);
        try {
            elements = elementServices.getActiveElements();
            addMessage("Found", "Active elements found", FacesMessage.SEVERITY_INFO);
        } catch (ServicesException e) {
            e.printStackTrace();
            addMessage("Not found", "Active elements not found", FacesMessage.SEVERITY_ERROR);
        }
    }

    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        LOGGER.info("addMessage");
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage("msgs", message);
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<Element> getFilteredElements() {
        return filteredElements;
    }

    public void setFilteredElements(List<Element> filteredElements) {
        this.filteredElements = filteredElements;
    }

    public List<Element> getSelectedElements() {
        return selectedElements;
    }

    public void setSelectedElements(List<Element> selectedElements) {
        this.selectedElements = selectedElements;
    }
}
