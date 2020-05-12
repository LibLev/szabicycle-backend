package com.szabicycle.szabicycle.repository;

import com.szabicycle.szabicycle.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Long> {

    @Query("select c from Component c where c.typeOfComponent = 'BARTAPE' ")
    List<Component> findAllBartape();

    @Query("select c from Component c where c.typeOfComponent = 'BREAK' ")
    List<Component> findAllBreak();

    @Query("select c from Component c where c.typeOfComponent = 'CALLIPER' ")
    List<Component> findAllCalliper();

    @Query("select c from Component c where c.typeOfComponent = 'CRANKSET' ")
    List<Component> findAllCrankset();

    @Query("select c from Component c where c.typeOfComponent = 'FORK' ")
    List<Component> findAllFork();

    @Query("select c from Component c where c.typeOfComponent = 'FRAME' ")
    List<Component> findAllFrame();

    @Query("select c from Component c where c.typeOfComponent = 'GROUPSET' ")
    List<Component> findAllGroupset();

    @Query("select c from Component c where c.typeOfComponent = 'HANDLEBAR' ")
    List<Component> findAllHandlebar();

    @Query("select c from Component c where c.typeOfComponent = 'PEDAL' ")
    List<Component> findAllPedal();

    @Query("select c from Component c where c.typeOfComponent = 'SADDLE' ")
    List<Component> findAllSaddle();

    @Query("select c from Component c where c.typeOfComponent = 'SEATPOST' ")
    List<Component> findAllSeatpost();

    @Query("select c from Component c where c.typeOfComponent = 'SHIFTER' ")
    List<Component> findAllShifter();

    @Query("select c from Component c where c.typeOfComponent = 'STEM' ")
    List<Component> findAllStem();

    @Query("select c from Component c where c.typeOfComponent = 'WHEEL' ")
    List<Component> findAllWheel();
}
