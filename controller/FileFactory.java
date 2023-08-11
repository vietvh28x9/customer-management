package controller;

import model.Customer;

import java.io.*;
import java.util.ArrayList;

public class FileFactory {
    public static boolean saveFile(ArrayList<Customer>cusList, String path){
        try {
            //get path to save file
            FileOutputStream fos = new FileOutputStream(path);
            //create and save the object to the hardware
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cusList);
            //close stream
            oos.close();
            fos.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
            return false;
    }
    public static ArrayList<Customer>readFile (String path){
        ArrayList<Customer> cusList = new ArrayList<Customer>();
        try{
            //get path to read file
            FileInputStream fis = new FileInputStream(path);
            //read each object and add to an array list
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object data = ois.readObject();
            cusList = (ArrayList<Customer>) data;
            //close stream
            fis.close();
            ois.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return cusList;
    }
}
