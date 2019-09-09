package com.github.vedeshkin.RoadMap.Core;

import com.github.vedeshkin.RoadMap.DAO.City;
import com.github.vedeshkin.RoadMap.DAO.Road;

import java.util.List;

public interface RoadService {
    Road getRoadByName(String name);
    void removeRoad(String name);
    void addRoad(String name,int distance,String fromCity,String toCity);

    void addRoad(String name, int distance, City fromCity, City toCity);
    List<Road> getAllRoadsList();

    boolean isRoadExist(String name);
}
