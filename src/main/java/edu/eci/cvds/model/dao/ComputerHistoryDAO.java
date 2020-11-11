package edu.eci.cvds.model.dao;

import org.apache.ibatis.exceptions.PersistenceException;

public interface ComputerHistoryDAO {

    void addComputerHistoryById(long computerId, long userId, String title)
            throws PersistenceException;

    void addComputerHistoryWithDetailById(
            long computerId, long userId, String title, String detail)
            throws PersistenceException;

    void addComputerHistoryByReference(String reference, long userId, String title)
            throws PersistenceException;

    void addComputerHistoryByReferenceAndUsername(
            String reference, String username, String title)
            throws PersistenceException;

    void addComputerHistoryWithDetailByReference(
            String reference, long userId, String title, String detail)
            throws PersistenceException;

    void addComputerHistoryWithDetailByReferenceAndUsername(
            String reference, String username, String title, String detail)
            throws PersistenceException;
}
