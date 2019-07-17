package com.reply.ro.mvc;

import com.reply.ro.models.City;
import com.reply.ro.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityController {

    @Autowired private CityServiceImpl cityService;

    @GetMapping("/manage/cities/all")
    public ResponseEntity<?> getAllCities(){
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
    }

    @PutMapping("/manage/cities/add")
    public ResponseEntity<?> createCity(@RequestBody City city){
        return new ResponseEntity<>(cityService.createCity(city), HttpStatus.OK);
    }

    @DeleteMapping("/manage/cities/drop/{name}")
    public void deleteCity(@PathVariable String name){
        cityService.deleteCityByName(name);
    }


}
