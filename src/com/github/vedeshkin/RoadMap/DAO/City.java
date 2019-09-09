package com.github.vedeshkin.RoadMap.DAO;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class City implements Serializable {
    private final String cityName;
    private final int population;
    private List<Road> cityRoads = new LinkedList<>();

    public City(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
    }


    public String getCityName() {
        return cityName;
    }

    public int getPopulation() {
        return population;
    }

    public List getCityRoads() {
        //user shouldn't have access to the underlying object
        return new LinkedList<>(cityRoads);
    }
    public void addRoadToCity(Road road)
    {
        this.cityRoads.add(road);
    }

    public void removeRoad(Road road){
        this.cityRoads.remove(road);
    }

    @Override
    public String toString() {
        return "City[" + cityName + "]" + " population[" + population + "]";
    }
}
