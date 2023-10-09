package com.xyz.theatreService.service;

import com.xyz.theatreService.entity.City;
import com.xyz.theatreService.dto.CityDto;
import com.xyz.theatreService.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(@Autowired CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityDto getCityByName(String name) {
        City city = cityRepository.findByName(name);

        CityDto cityDto = new CityDto(city.getId(), city.getName());
        return cityDto;
    }
}
