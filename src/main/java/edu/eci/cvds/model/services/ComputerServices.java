package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.Computer;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.javatuples.Pair;

public interface ComputerServices {

    void registerComputerByReferences(String username, String reference,
            Pair<Boolean, String> computerCasePair, Pair<Boolean, String> monitorPair,
            Pair<Boolean, String> keyboardPair, Pair<Boolean, String> mousePair)
            throws ServicesException;

    Long getIdByReference(String reference) throws ServicesException;

    Long getIdByComputerCaseId(Long computerCaseId) throws ServicesException;

    Long getIdByMonitorId(Long monitorId) throws ServicesException;

    Long getIdByKeyboardId(Long keyboardId) throws ServicesException;

    Long getIdByMouseId(Long mouseId) throws ServicesException;

    Computer getComputerByReference(String reference) throws ServicesException;

    void linkElementByIdsAndComputer(ElementTypeName typeName, Long userId, Long elementId,
            Computer computer) throws ServicesException;

    void setElementIdByIds(ElementTypeName typeName, Long userId, Long elementId, Computer computer)
            throws ServicesException;

    void setComputerCaseIdByIds(Long userId, Long computerCaseId, Long computerId)
            throws ServicesException;

    void setMonitorIdByIds(Long userId, Long monitorId, Long computerId) throws ServicesException;

    void setKeyboardIdByIds(Long userId, Long keyboardId, Long computerId) throws ServicesException;

    void setMouseIdByIds(Long userId, Long mouseId, Long computerId) throws ServicesException;

    void unlinkElement(ElementTypeName typeName, Long userId, Long elementId, Long computerId)
            throws ServicesException;

    void setDiscardedAndAvailableById(Long id, Boolean discarded, Boolean available)
            throws ServicesException;

    void discard(Long userId, Computer computer, boolean discardComputerCase,
            boolean discardMonitor, boolean discardKeyboard, boolean discardMouse)
            throws ServicesException;
}
