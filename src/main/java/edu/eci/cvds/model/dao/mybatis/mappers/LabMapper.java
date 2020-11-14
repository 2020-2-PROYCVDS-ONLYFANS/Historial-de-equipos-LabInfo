package edu.eci.cvds.model.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;

public interface LabMapper {

    Long getLabIdByLinkedComputerId(@Param("id") Long id);
}
