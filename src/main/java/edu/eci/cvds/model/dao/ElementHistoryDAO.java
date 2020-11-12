package edu.eci.cvds.model.dao;

import edu.eci.cvds.model.entities.element.ElementHistory;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public interface ElementHistoryDAO {

    void addElementHistoryByIdAndUsername(long elementId, String username, String title) throws PersistenceException;

    void addElementHistoryWithDetailById(long elementId, long userId, String title, String detail)
            throws PersistenceException;

    void addElementHistoryByReference(String reference, long userId, String title) throws PersistenceException;

    void addElementHistoryByReferenceAndUsername(String reference, String username, String title)
            throws PersistenceException;

    void addElementHistoryWithDetailByReference(String reference, long userId, String title, String detail)
            throws PersistenceException;

    void addComputerCaseHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException;

    void addMonitorHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException;

    void addKeyboardHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException;

    void addMouseHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail) throws PersistenceException;

    List<ElementHistory> loadElementsHistory() throws PersistenceException;

    List<ElementHistory> loadElementHistoryById(long elementId) throws PersistenceException;
}
