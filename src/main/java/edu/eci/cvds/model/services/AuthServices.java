package edu.eci.cvds.model.services;

public interface AuthServices {

    Long getUserIdByUsername(String username) throws ServicesException;

    String getUsernameById(Long id) throws ServicesException;
}
