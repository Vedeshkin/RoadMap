package com.github.vedeshkin.RoadMap.Core;

import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.DAO.Road;

import java.util.List;

public interface CityService {
    City getCityByName(String name);
    void removeCity(String name);
    void addCity(String name,int population);
    List<City> getAllCitiesList();
    List<Road> getAllRoadsForTheCity(String name);
    boolean isExist(String cityName);

    void flush();

}