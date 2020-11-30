package edu.eci.cvds.model.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ServicesException extends Exception {

    private static final long serialVersionUID = 1L;

    public ServicesException(String message, PersistenceException e) {
        super(message, e);
    }
}
