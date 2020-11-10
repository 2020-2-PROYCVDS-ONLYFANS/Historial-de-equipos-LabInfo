package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.ElementHistory;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;

import java.util.List;

@SuppressWarnings("unused")
public interface LabInfoServices {

    void registerElement(ElementTypeName name, String reference, String username)
            throws LabInfoServicesException;

    Element loadElementByReference(String reference) throws LabInfoServicesException;

    void addElementHistoryById(long elementId, long userId, String title)
            throws LabInfoServicesException;

    void addElementHistoryWithDetailById(
            long elementId, long userId, String title, String detail)
            throws LabInfoServicesException;

    void addElementHistoryByReference(String reference, long userId, String title)
            throws LabInfoServicesException;

    void addElementHistoryByReferenceAndUsername(
            String reference, String username, String title)
            throws LabInfoServicesException;

    void addElementHistoryWithDetailByReference(
            String reference, long userId, String title, String detail)
            throws LabInfoServicesException;

    void associateComputerCaseByReferenceAndUsername(
            String reference, String username, String newComputer)
            throws LabInfoServicesException;

    void associateMonitorByReferenceAndUsername(
            String reference, String username, String newComputer)
            throws LabInfoServicesException;

    void associateKeyboardByReferenceAndUsername(
            String reference, String username, String newComputer)
            throws LabInfoServicesException;

    void associateMouseByReferenceAndUsername(
            String reference, String username, String newComputer)
            throws LabInfoServicesException;

    List<ElementHistory> loadElementsHistory() throws LabInfoServicesException;

    List<ElementHistory> loadElementHistoryById(long elementId)
            throws LabInfoServicesException;

    void registerComputerWithReferences(
            String computerCase, String monitor, String keyboard, String mouse,
            String computer, boolean existsComputerCase, boolean existsMonitor,
            boolean existsKeyboard, boolean existsMouse, String username)
            throws LabInfoServicesException;
}
