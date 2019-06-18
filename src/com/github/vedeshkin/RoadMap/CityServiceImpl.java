package com.github.vedeshkin.RoadMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CityServiceImpl implements CityService {

    private HashMap<String ,City> cities;
    private  static CityServiceImpl instance;
    private StorageService storageService;
    final static String OBJECT_NAME = "Cities";

    private  CityServiceImpl(){
        this.storageService = StorageServiceFileImpl.getInstance();
        this.cities = new HashMap<>();
        //Let's try to load data from somewhere;
        HashMap<String, City> map =  storageService.loadObject(OBJECT_NAME);
        if(map !=  null){
            //good at least we have something here
            this.cities = map;
            System.out.println("Loaded from disk,count is "+cities.size());
        }




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
        storageService.saveObject(this.cities,OBJECT_NAME);
    }

    @Override
    public void addCity(String name, int population) {
        if(cities.containsKey(name)){
            System.out.println("The city under this name already exists");
            return;
        }
        City newCity = new City(name,population);
        cities.put(name,newCity);
        //here we go, this is a good idea to save object here.
        storageService.saveObject(this.cities,OBJECT_NAME);

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
