package sample.implement;

import sample.model.*;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class FileSerialization {
    //Name:storeData
//Purpose: store data when closing the system to a file
//Returns: stored data in file
//Output: none
//Effect: make sure that all data is saved when closing the system
    public void storeData() {
        File file = new File("src/database.ser");
        if (file.exists()){
            file.delete();
            System.out.println("File deleted");
        }

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/database.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);


            HashMap<String, Course> courses = Database.getCourseHashMap();
            HashMap<String, Unit> units = Database.getUnitHashMap();
            HashMap<String, Staff> staffs = Database.getStaffHashMap();
            HashMap<String, Set<Unit>> linkUnits = Database.getLinkedUnitHashMap();


            NonStaticDatabase nonStaticDatabase = new NonStaticDatabase(staffs,units,courses,linkUnits);
            out.writeObject(nonStaticDatabase);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in src/database.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }






}
