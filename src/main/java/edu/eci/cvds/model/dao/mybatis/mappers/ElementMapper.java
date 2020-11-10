package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.element.Element;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ElementMapper {

    @Transactional
    void registerElement(
            @Param("name") ElementTypeName name,
            @Param("reference") String reference);

    Element loadElementById(@Param("id") long id);

    Element loadElementByReference(@Param("reference") String reference);

    @Transactional
    void setAvailableByReference(
            @Param("reference") String reference,
            @Param("available") boolean available);
}
