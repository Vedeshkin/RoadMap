package com.github.vedeshkin.RoadMap.Core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageServiceFileImpl implements StorageService {

    private static final String WORKING_DIR = System.getProperty("user.home");
    private static StorageServiceFileImpl instance = null;

    private StorageServiceFileImpl(){}
    public static StorageServiceFileImpl getInstance(){
        if(instance == null){
            instance = new StorageServiceFileImpl();
        }return instance;
    }


    @Override
    public <T extends Serializable> void saveObject(T object,String name) {

        File file = Paths.get(WORKING_DIR,name).toFile();
        try(
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream =  new ObjectOutputStream(fileOutputStream) )
        {
            objectOutputStream.writeObject(object);

        }catch (Exception ex)
        {
            System.out.println("Unable to save the object");
            System.out.println(ex.getMessage());
        }

        System.out.println("Object saved to " + file.getAbsolutePath());

    }

    @Override
    public <T> T loadObject(String name) {
        Path path = Paths.get(WORKING_DIR,name);
        T object = null;

        if(!Files.exists(path)){
            System.out.println("Object under ID " + name + " doesn't exist in " + path.toString());
            return null;
        }
        try (FileInputStream fileInputStream = new FileInputStream(path.toFile());
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            object = (T)objectInputStream.readObject();
        }catch (Exception ex)
        {
            System.out.println("Unable to load object");
            System.out.println(ex.getMessage());
        }
        //So far we load object...we don't need a copy of it on disk so it's a good idea to remove it
        //for avoiding any possible conflicts
        //removeObject(name);

        return object;
    }

    private void removeObject(String name) {
        Path path = Paths.get(WORKING_DIR,name);
        try {
            Files.delete(path);
        } catch (IOException iex){
            System.out.println("Unable to delete a file: "+path.toString());
            System.out.println(iex.getMessage());
        }
    }

    private  <T> String generateFileName(T object){
        String fileName = object.hashCode() + "_" + System.currentTimeMillis() + ".dat";
        return fileName;

    }
}
