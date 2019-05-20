package com.github.vedeshkin.RoadMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CityServiceImpl implements CityService {

    private HashMap<String ,City> cities;
    private  static CityServiceImpl instance;

    private  CityServiceImpl(){
        this.cities = new HashMap<>();
    }

    public static CityServiceImpl getInstance() {
        if (instance == null) {
            synchronized (CityServiceImpl.class) {
                instance = new CityServiceImpl();
            }

        }
        return instance;

    }

    @Override
    public City getCityByName(String name) {
        return this.cities.get(name);
    }

    @Override
    public void removeCity(String name) {
        City city = cities.get(name);
        if(city == null){
            System.out.println("The city doesn't exist");
            return;
        }
        if (city.getCityRoads().size()!= 0){
            System.out.println("Before deleting the city, you have to remove all roads");
            return;
        }
        cities.remove(name);
    }

    @Override
    public void addCity(String name, int population) {
        if(cities.containsKey(name)){
            System.out.println("The city under this name already exists");
            return;
        }
        City newCity = new City(name,population);
        cities.put(name,newCity);

    }

    @Override
    public List<City> getAllCitiesList() {
        return new ArrayList<>(cities.values());
    }

    @Override
    public List<Road> getAllRoadsForTheCity(String name) {
        City city = getCityByName(name);
        if(city == null){
            System.out.println("The city you've specified doesn't exist");
            return null;
        }
        return city.getCityRoads();
    }
}
