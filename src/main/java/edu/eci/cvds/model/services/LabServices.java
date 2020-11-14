package edu.eci.cvds.model.services;

public interface LabServices {

    Long getLabIdByLinkedComputerId(Long id) throws ServicesException;
}
