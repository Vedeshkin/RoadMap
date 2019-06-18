package com.github.vedeshkin.RoadMap;

import com.github.vedeshkin.RoadMap.Core.CityService;
import com.github.vedeshkin.RoadMap.Core.CityServiceImpl;
import com.github.vedeshkin.RoadMap.Core.RoadService;
import com.github.vedeshkin.RoadMap.Core.RoadServiceImpl;

public class Main {

    static CityService cityService = CityServiceImpl.getInstance();
    static RoadService roadService = RoadServiceImpl.getInstance();

    public static void main(String[] args) {
        //just a few test before real actions take a place
      fillUpWithData();


        //let see what we get so far
        cityService.getAllCitiesList().forEach(System.out::println);
        roadService.getAllRoadsList().forEach(System.out::println);
        System.out.println("---------------------------");
        cityService.getAllRoadsForTheCity("Moscow").forEach(System.out::println);
        System.out.println("---------------------------");
        roadService.removeRoad("M102");
        cityService.getAllRoadsForTheCity("Moscow").forEach(System.out::println);
        System.out.println("---------------------------");
        cityService.removeCity("Moscow");
        System.out.println("---------------------------");
        roadService.removeRoad("M105");
        cityService.removeCity("Moscow");
        System.out.println("---------------------------");
        cityService.getAllCitiesList().forEach(System.out::println);


    }
 static  void fillUpWithData(){
    cityService.addCity("Moscow", 12_000_00);
    cityService.addCity("Saint-Petersburg", 6_000_000);
    roadService.addRoad("M105", 670, "Saint-Petersburg", "Moscow");
    roadService.addRoad("MKAD", 120, "Moscow", "Moscow");
    roadService.addRoad("M102", 500, "Moscow", "Saint-Petersburg");
  }
}
