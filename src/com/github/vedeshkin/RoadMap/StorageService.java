package com.github.vedeshkin.RoadMap;


import java.io.Serializable;

public interface StorageService {

   <T extends Serializable> void saveObject(T t,String name);
     <T> T loadObject(String name);


}
