package com.reply.ro.service.impl;

import com.reply.ro.models.City;
import com.reply.ro.repository.CityRepository;
import com.reply.ro.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteCityByName(String name) {
        cityRepository.deleteCityByName(name);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
