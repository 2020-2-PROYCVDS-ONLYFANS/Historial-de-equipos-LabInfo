package edu.eci.cvds.model.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class LabInfoServicesException extends Exception {

    public LabInfoServicesException(String message, PersistenceException e) {
        super(message, e);
    }
}
