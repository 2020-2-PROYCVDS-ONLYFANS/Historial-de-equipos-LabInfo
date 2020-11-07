package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.element.type.ElementType;
import edu.eci.cvds.model.entities.element.type.ElementTypeName;
import org.apache.ibatis.annotations.Param;

public interface ElementTypeMapper {

    void registerElementType(@Param("name") ElementTypeName name);

    ElementType loadElementTypeByName(@Param("name") ElementTypeName name);
}
