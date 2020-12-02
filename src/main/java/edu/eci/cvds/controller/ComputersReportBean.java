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
import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ServicesException;

@ManagedBean(name = "computersReportBean")
@ViewScoped
@SuppressWarnings("deprecation")
public class ComputersReportBean extends BasePageBean {

    private static final long serialVersionUID = 1L;

    private List<Computer> computers;
    private List<Computer> filteredComputers;
    private List<Computer> selectedComputers;

    @Inject
    private transient ComputerServices computerServices;

    @Inject
    private transient AuthServices authServices;

    private static final transient Logger LOGGER =
            LoggerFactory.getLogger(ComputersReportBean.class);

    public void delete() {
        if (selectedComputers != null && !selectedComputers.isEmpty()) {
            try {
                Long userId = authServices
                        .getUserIdByUsername(SecurityUtils.getSubject().getPrincipal().toString());
                for (Computer computer : selectedComputers) {
                    computerServices.discard(userId, computer, false, false, false, false);
                }
                addMessage("Info", "Discarded computers", FacesMessage.SEVERITY_INFO);
            } catch (ServicesException e) {
                addMessage("Error", "System error", FacesMessage.SEVERITY_FATAL);
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("..");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            selectedComputers.clear();
            try {
                computers = computerServices.getActiveComputers();
            } catch (ServicesException e) {
                e.printStackTrace();
                addMessage("Not found", "Active computers not found", FacesMessage.SEVERITY_INFO);
            }
        }
    }

    public boolean globalFilterFunction(Object value, Object filter) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Computer computer = (Computer) value;
        return computer.getReference().toLowerCase().contains(filterText)
                || computer.getComputerCase().getReference().toLowerCase().contains(filterText)
                || computer.getMonitor().getReference().toLowerCase().contains(filterText)
                || computer.getKeyboard().getReference().toLowerCase().contains(filterText)
                || computer.getMouse().getReference().toLowerCase().contains(filterText)
                || computer.getId() < filterInt;
    }

    @Override
    public void init() {
        this.getInjector().injectMembers(this);
        try {
            computers = computerServices.getActiveComputers();
        } catch (ServicesException e) {
            e.printStackTrace();
            addMessage("Not found", "Active computers not found", FacesMessage.SEVERITY_INFO);
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
        FacesContext.getCurrentInstance().addMessage("elementMessage", message);
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public List<Computer> getFilteredComputers() {
        return filteredComputers;
    }

    public void setFilteredComputers(List<Computer> filteredComputers) {
        this.filteredComputers = filteredComputers;
    }

    public List<Computer> getSelectedComputers() {
        return selectedComputers;
    }

    public void setSelectedComputers(List<Computer> selectedComputers) {
        this.selectedComputers = selectedComputers;
    }
}
