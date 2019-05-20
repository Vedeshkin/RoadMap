package com.github.vedeshkin.RoadMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoadServiceImpl implements RoadService {
    private HashMap<String, Road> roads;
    private static RoadServiceImpl instance;

    private RoadServiceImpl() {
        this.roads = new HashMap<>();
    }

    public static RoadServiceImpl getInstance() {
        if (instance == null) {
            synchronized (RoadServiceImpl.class) {
                instance = new RoadServiceImpl();
            }

        }
        return instance;

    }


    @Override
    public Road getRoadByName(String name) {

        return roads.get(name);
    }

    @Override
    public void removeRoad(String name) {
        Road road = getRoadByName(name);
        if (road == null) {
            System.out.println("The road doesn't exist");
            return;
        }
        //we have to delete the connection between the cities first, so...
        road.getHead().removeRoad(road);
        road.getTail().removeRoad(road);
        roads.remove(name);
    }

    @Override
    public void addRoad(String name, int distance, String from, String to) {
        if (roads.containsKey(name)) {
            System.out.println("The road already exist");
            return;
        }
        CityService cityService = CityServiceImpl.getInstance();
        City cityFrom = cityService.getCityByName(from);
        City cityTo = cityService.getCityByName(to);
        if (cityFrom  == null) {
            System.out.println("The city you've specified(" + from + ") doesn't exist");
            return;
        }
        if (cityTo == null) {
            System.out.println("The city you've specified(" + to + ") doesn't exist");
            return;
        }
        //are you going to build circle road?huh?
        if (cityFrom == cityTo){
            System.out.println("Circle roads are not allowed in the current version");
            return;
        }
        Road newRoad = new Road(name,distance,cityFrom,cityTo);
        roads.put(name,newRoad);
        //here we have to update the list of roads for the  both of  cites
        cityFrom.addRoadToCity(newRoad);
        cityTo.addRoadToCity(newRoad);
    }



    @Override
    public List<Road> getAllRoadsList() {
        return new ArrayList<>(roads.values());
    }
}
