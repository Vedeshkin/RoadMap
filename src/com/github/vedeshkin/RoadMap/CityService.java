package com.github.vedeshkin.RoadMap;

import java.util.List;

public interface CityService {
    City getCityByName(String name);
    void removeCity(String name);
    void addCity(String name,int population);
    List<City> getAllCitiesList();
    List<Road> getAllRoadsForTheCity(String name);

}