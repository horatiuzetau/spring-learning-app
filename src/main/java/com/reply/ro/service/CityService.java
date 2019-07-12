package com.reply.ro.service;


import com.reply.ro.models.City;

import java.util.List;

public interface CityService {

//    ADD
    City createCity(City city);

//    DELETE
    void deleteCityByName(String name);

//    GET ALL
    List<City > getAllCities();


}
