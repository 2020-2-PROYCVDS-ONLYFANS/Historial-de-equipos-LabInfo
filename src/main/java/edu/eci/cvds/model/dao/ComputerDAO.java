package edu.eci.cvds.model.dao;

import org.apache.ibatis.exceptions.PersistenceException;

public interface ComputerDAO {

    void registerComputerWithReferences(
            String computer, String computerCase, String monitor, String keyboard, String mouse)
            throws PersistenceException;
}
