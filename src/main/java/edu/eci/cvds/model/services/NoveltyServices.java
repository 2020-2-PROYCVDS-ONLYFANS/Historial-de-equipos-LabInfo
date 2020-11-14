package edu.eci.cvds.model.services;

import edu.eci.cvds.model.entities.element.type.ElementTypeName;

public interface NoveltyServices {

    void create(Long userId, Long elementId, Long computerId,
                Long labId, String title, String detail)
            throws ServicesException;

    void createByComputerReferenceAndUsername(
            String username, String computerRef, String title, String detail)
            throws ServicesException;

    void createByElementReferenceAndUsername(
            ElementTypeName typeName, String username, String elementRef,
            String title, String detail) throws ServicesException;
}
