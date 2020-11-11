package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.computer.Computer;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ComputerDAO {

    void registerComputerWithReferences(
            String computer, String computerCase, String monitor, String keyboard, String mouse)
            throws PersistenceException;

    Computer loadComputerByReference(String reference) throws PersistenceException;

    void associateComputerCaseByReference(String computer, String computerCase)
            throws PersistenceException;

    void associateMonitorByReference(String computer, String monitor)
            throws PersistenceException;

    void associateKeyboardByReference(String computer, String keyboard)
            throws PersistenceException;
    void associateMouseByReference(String computer, String mouse)
            throws PersistenceException;

}
