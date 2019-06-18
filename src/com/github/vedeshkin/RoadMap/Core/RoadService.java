package com.github.vedeshkin.RoadMap.Core;

import com.github.vedeshkin.RoadMap.DAO.Road;

import java.util.List;

public interface RoadService {
    Road getRoadByName(String name);
    void removeRoad(String name);
    void addRoad(String name,int distance,String fromCity,String toCity);
    List<Road> getAllRoadsList();
}
