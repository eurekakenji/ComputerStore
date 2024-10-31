package ee.ivkhkdev.repository;


import ee.ivkhkdev.repository.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage<T> implements Repository<T> {

    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void save(T entity){
        List<T> entities = this.load();
        if(entities == null) entities = new ArrayList<>();
        entities.add(entity);
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities);
            objectOutputStream.flush();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }



    @Override
    public List<T> load(){
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        return new ArrayList<>();
    }
}
