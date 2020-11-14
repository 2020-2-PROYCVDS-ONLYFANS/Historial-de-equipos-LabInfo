package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.annotations.Param;

public interface ElementTypeMapper {

    long getElementTypeIdByName(@Param("name") ElementTypeName name);
}
