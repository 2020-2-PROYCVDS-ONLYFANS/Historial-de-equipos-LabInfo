package edu.eci.cvds.model.dao.mybatis.mappers;

import edu.eci.cvds.model.entities.element.ElementHistory;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

import java.util.List;

public interface ElementHistoryMapper {

    @Transactional
    void addElementHistoryById(
            @Param("elementId") long elementId, @Param("userId") long userId, @Param("title") String title);

    @Transactional
    void addElementHistoryWithDetailById(
            @Param("elementId") long elementId, @Param("userId") long userId,
            @Param("title") String title, @Param("detail") String detail);

    @Transactional
    void addElementHistoryByReference(
            @Param("reference") String reference, @Param("userId") long userId, @Param("title") String title);

    @Transactional
    void addElementHistoryByReferenceAndUsername(
            @Param("reference") String reference, @Param("username") String username, @Param("title") String title);

    @Transactional
    void addElementHistoryWithDetailByReference(
            @Param("reference") String reference, @Param("userId") long userId,
            @Param("title") String title, @Param("detail") String detail);

    @Transactional
    void addComputerCaseHistoryWithDetailByReferenceAndUsername(
            @Param("reference") String reference, @Param("username") String username,
            @Param("title") String title, @Param("detail") String detail);

    @Transactional
    void addMonitorHistoryWithDetailByReferenceAndUsername(
            @Param("reference") String reference, @Param("username") String username,
            @Param("title") String title, @Param("detail") String detail);

    @Transactional
    void addKeyboardHistoryWithDetailByReferenceAndUsername(
            @Param("reference") String reference, @Param("username") String username,
            @Param("title") String title, @Param("detail") String detail);

    @Transactional
    void addMouseHistoryWithDetailByReferenceAndUsername(
            @Param("reference") String reference, @Param("username") String username,
            @Param("title") String title, @Param("detail") String detail);


    List<ElementHistory> loadElementsHistory();

    List<ElementHistory> loadElementHistoryById(@Param("elementId") long elementId);
}
