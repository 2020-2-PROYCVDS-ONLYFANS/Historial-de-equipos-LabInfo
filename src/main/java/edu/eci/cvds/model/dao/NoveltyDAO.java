package edu.eci.cvds.model.dao;

import org.apache.ibatis.exceptions.PersistenceException;

public interface NoveltyDAO {

    void create(Long userId, Long elementId, Long computerId,
                Long labId, String title, String detail)
            throws PersistenceException;
}
