package com.reply.ro.mvc;

import com.reply.ro.models.City;
import com.reply.ro.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping
    public ResponseEntity<?> getAllCities(){
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> createCity(@RequestBody City city){
        return new ResponseEntity<>(cityService.createCity(city), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteCity(@PathVariable String name){
        cityService.deleteCityByName(name);
    }


}
