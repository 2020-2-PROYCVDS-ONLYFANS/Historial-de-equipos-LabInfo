package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.Computer;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ComputerDAO {

    void registerComputer(
            String reference, Long computerCaseId,
            Long monitorId, Long keyboardId, Long mouseId)
            throws PersistenceException;

    Long getIdByReference(String reference) throws PersistenceException;

    Long getIdByComputerCaseId(Long id) throws PersistenceException;

    Long getIdByMonitorId(Long id) throws PersistenceException;

    Long getIdByKeyboardId(Long id) throws PersistenceException;

    Long getIdByMouseId(Long id) throws PersistenceException;

    Computer getComputerByReference(String reference) throws PersistenceException;

    void setComputerCaseIdByIds(Long computerId, Long computerCaseId)
            throws PersistenceException;

    void setMonitorIdByIds(Long computerId, Long monitorId)
            throws PersistenceException;

    void setKeyboardIdByIds(Long computerId, Long keyboardId)
            throws PersistenceException;

    void setMouseIdByIds(Long computerId, Long mouseId)
            throws PersistenceException;

    void setDiscardedAndAvailableById(Long id, Boolean discarded, Boolean available)
            throws PersistenceException;
}
