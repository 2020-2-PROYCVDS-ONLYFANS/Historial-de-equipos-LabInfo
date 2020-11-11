package edu.eci.cvds.model.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.model.dao.ComputerDAO;
import edu.eci.cvds.model.dao.ComputerHistoryDAO;
import edu.eci.cvds.model.dao.ElementDAO;
import edu.eci.cvds.model.dao.ElementHistoryDAO;
import edu.eci.cvds.model.entities.computer.Computer;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import edu.eci.cvds.model.services.ComputerServices;
import edu.eci.cvds.model.services.ElementServices;
import edu.eci.cvds.model.services.LabInfoServicesException;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputerServicesImpl implements ComputerServices {

    @Inject
    ElementServices elementServices;

    @Inject
    ComputerDAO computerDAO;

    @Inject
    ComputerHistoryDAO computerHistoryDAO;

    @Inject
    ElementDAO elementDAO;

    @Inject
    ElementHistoryDAO elementHistoryDAO;

    @SuppressWarnings("unused")
    private static final transient Logger LOGGER = LoggerFactory.getLogger(ComputerServicesImpl.class);

    @Override
    public void registerComputerWithReferences(
            String computer, String computerCase, String monitor, String keyboard,
            String mouse, boolean existsComputerCase, boolean existsMonitor,
            boolean existsKeyboard, boolean existsMouse, String username)
            throws LabInfoServicesException {
        try {
            if (!existsComputerCase) {
                elementServices.registerElement(
                        ElementTypeName.ETN_COMPUTER_CASE, computerCase, username);
            } if (!existsMonitor) {
                elementServices.registerElement(
                        ElementTypeName.ETN_MONITOR, monitor, username);
            } if (!existsKeyboard) {
                elementServices.registerElement(
                        ElementTypeName.ETN_KEYBOARD, keyboard, username);
            } if (!existsMouse) {
                elementServices.registerElement(
                        ElementTypeName.ETN_MOUSE, mouse, username);
            }

            elementHistoryDAO.addComputerCaseHistoryWithDetailByReferenceAndUsername(
                    computerCase, username, "Associated", "Computer: " + computer);
            elementDAO.setAvailableByReference(computerCase, false);

            elementHistoryDAO.addMonitorHistoryWithDetailByReferenceAndUsername(
                    monitor, username, "Associated", "Computer: " + computer);
            elementDAO.setAvailableByReference(monitor, false);

            elementHistoryDAO.addKeyboardHistoryWithDetailByReferenceAndUsername(
                    keyboard, username, "Associated", "Computer: " + computer);
            elementDAO.setAvailableByReference(keyboard, false);

            elementHistoryDAO.addMouseHistoryWithDetailByReferenceAndUsername(
                    mouse, username, "Associated", "Computer: " + computer);
            elementDAO.setAvailableByReference(mouse, false);

            computerDAO.registerComputerWithReferences(
                    computer, computerCase, monitor, keyboard, mouse);
            computerHistoryDAO.addComputerHistoryByReferenceAndUsername(
                    computer, username, "Registered");
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public Computer loadComputerByReference(String reference)
            throws LabInfoServicesException {
        try {
            return computerDAO.loadComputerByReference(reference);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateComputerCaseByReference(
            String computer, String computerCase) throws LabInfoServicesException {
        try {
            computerDAO.associateComputerCaseByReference(
                    computer, computerCase);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateMonitorByReference(
            String computer, String monitor) throws LabInfoServicesException {
        try {
            computerDAO.associateMonitorByReference(computer, monitor);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateKeyboardByReference(
            String computer, String keyboard) throws LabInfoServicesException {
        try {
            computerDAO.associateKeyboardByReference(computer, keyboard);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void associateMouseByReference(
            String computer, String mouse) throws LabInfoServicesException {
        try {
            computerDAO.associateMouseByReference(computer, mouse);
        } catch (PersistenceException e) {
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }

    @Override
    public void addComputerHistoryByReferenceAndUsername(
            String reference, String username, String title, String detail)
            throws LabInfoServicesException {
        LOGGER.info("addComputerHistoryByReferenceAndUsername");
        try {
            computerHistoryDAO.addComputerHistoryWithDetailByReferenceAndUsername(
                    reference, username, title, detail);
        } catch (PersistenceException e) {
            LOGGER.info("addComputerHistoryByReferenceAndUsername - catch");
            throw new LabInfoServicesException(e.getMessage(), e);
        }
    }
}
