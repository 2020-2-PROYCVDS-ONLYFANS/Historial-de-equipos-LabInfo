package edu.eci.cvds.model.dao;

import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.cvds.model.entities.Novelty;

@SuppressWarnings("java:S1130")
public interface NoveltyDAO {

    List<Novelty> getByElementId(Long elementId) throws PersistenceException;

    List<Novelty> getByComputerId(Long computerId) throws PersistenceException;

    void create(Long userId, Long elementId, Long computerId, Long labId, String title,
            String detail) throws PersistenceException;
}
