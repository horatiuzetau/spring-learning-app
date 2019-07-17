package com.reply.ro.repository;

import com.reply.ro.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    void deleteCityByName(String name);

    Optional<City > findCityByName(String name);

}
