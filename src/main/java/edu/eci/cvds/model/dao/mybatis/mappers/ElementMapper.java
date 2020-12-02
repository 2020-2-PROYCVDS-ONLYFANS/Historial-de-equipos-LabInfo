package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.element.Element;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface ElementMapper {

    @Transactional
    void registerElement(@Param("reference") String reference, @Param("typeId") Long typeId);

    Long getIdByReference(@Param("reference") String reference);

    Element getElementById(@Param("id") Long id);

    Element getElementByReference(@Param("reference") String reference);

    List<Element> getActiveElements();

    @Transactional
    void setAvailableById(@Param("id") Long id, @Param("available") Boolean available);

    @Transactional
    void setDiscardedById(@Param("id") Long id, @Param("discarded") Boolean discarded);
}
