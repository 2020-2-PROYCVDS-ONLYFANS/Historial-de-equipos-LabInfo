package edu.eci.cvds.model.services;

import java.util.List;
import edu.eci.cvds.model.entities.Novelty;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;

public interface NoveltyServices {

    List<Novelty> getByElementId(Long elementId) throws ServicesException;

    List<Novelty> getByComputerId(Long computerId) throws ServicesException;

    void create(Long userId, Long elementId, Long computerId, Long labId, String title,
            String detail) throws ServicesException;

    void createByComputerReferenceAndUsername(String username, String computerRef, String title,
            String detail) throws ServicesException;

    void createByElementReferenceAndUsername(ElementTypeName typeName, String username,
            String elementRef, String title, String detail) throws ServicesException;
}
