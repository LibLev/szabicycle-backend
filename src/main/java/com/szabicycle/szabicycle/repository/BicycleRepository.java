package com.szabicycle.szabicycle.repository;

import com.szabicycle.szabicycle.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {

    @Query("select b from Bicycle b where b.typeOfBicycle = 'ROAD' ")
    List<Bicycle> findAllRoadBicycle();

    @Query("select b from Bicycle b where b.typeOfBicycle = 'GRAVEL' ")
    List<Bicycle> findAllGravelBicycle();

    @Query("select b from Bicycle b where b.typeOfBicycle = 'CYCLECROSS' ")
    List<Bicycle> findAllCyclecrossBicycle();

    @Query("select b from Bicycle b where b.typeOfBicycle = 'MOUNTAIN' ")
    List<Bicycle> findAllMTBBicycle();

    @Query("select b from Bicycle b where b.typeOfBicycle = 'TRACK' ")
    List<Bicycle> findAllTrackBicycle();

    @Query("select b from Bicycle b where b.typeOfBicycle = 'TREKKING' ")
    List<Bicycle> findAllTrekkingBicycle();

    @Query("select b from Bicycle b where b.typeOfBicycle = 'CITY' ")
    List<Bicycle> findAllCityBicycle();
}
