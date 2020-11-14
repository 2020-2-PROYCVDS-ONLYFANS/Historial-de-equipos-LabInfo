package edu.eci.cvds.model.dao;

import org.apache.ibatis.exceptions.PersistenceException;

public interface LabDAO {

    Long getLabIdByLinkedComputerId(Long id) throws PersistenceException;
}
