package edu.eci.cvds.model.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ServicesException extends Exception {

    public ServicesException(String message, PersistenceException e) {
        super(message, e);
    }
}
