package com.github.vedeshkin.RoadMap;

import java.util.LinkedList;
import java.util.List;

public class City {
    private final String cityName;
    private int population;
    private List cityRoads;

    public City(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
        this.cityRoads = new LinkedList();
    }

    public void setPopulation(int population) {
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
        return new LinkedList(cityRoads);
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
