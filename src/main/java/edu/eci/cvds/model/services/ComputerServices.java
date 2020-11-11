package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.computer.Computer;

public interface ComputerServices {

    void registerComputerWithReferences(
            String computerCase, String monitor, String keyboard, String mouse,
            String computer, boolean existsComputerCase, boolean existsMonitor,
            boolean existsKeyboard, boolean existsMouse, String username)
            throws LabInfoServicesException;

    Computer loadComputerByReference(String reference) throws LabInfoServicesException;

    void associateComputerCaseByReference(String computer, String computerCase)
            throws LabInfoServicesException;

    void associateMonitorByReference(String computer, String monitor)
            throws LabInfoServicesException;

    void associateKeyboardByReference(String computer, String keyboard)
            throws LabInfoServicesException;

    void associateMouseByReference(String computer, String mouse)
            throws LabInfoServicesException;

    void addComputerHistoryByReferenceAndUsername(
            String reference, String username, String title, String detail)
            throws LabInfoServicesException;
}
