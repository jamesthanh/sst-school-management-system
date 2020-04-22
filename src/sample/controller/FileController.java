package sample.controller;

import sample.model.Database;
import sample.model.NonStaticDatabase;
import sample.model.Staff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileController {
    //Name:FileController
//Purpose: load the data from the file to the database model
//Returns: data from the file
//Output: none
//Effect: checking file IO and load data
    public static void readFile(){

        File file = new File("src/database.ser");
        if (!file.exists()){
            try {
                List <String> fileLines = Files.readAllLines(Paths.get("src/staffs.txt"), StandardCharsets.UTF_8);
                HashMap<String, Staff> staffList = new HashMap<>();
                // loop each 2 lines
                for (int i = 1; i < fileLines.size(); i++){
                    String idAndName = fileLines.get(i);

                    String [] elements = idAndName.split(",");
                    String id = elements[0];
                    String name = elements[1];


                    String address = fileLines.get(++i);

                    // create a temp place to store the data because we are looping line by line
                    Staff staffTemp = new Staff(id, name, address);
                    staffList.put(id, staffTemp);

                }
                Database.setStaffHashMap(staffList);



            } catch (IOException ex){
                ex.printStackTrace();
            }

        } else {

            NonStaticDatabase nonStaticDatabase =null;
            try {
                FileInputStream fileIn = new FileInputStream("src/database.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                nonStaticDatabase = (NonStaticDatabase) in.readObject();
                in.close();
                fileIn.close();

                Database.setStaffHashMap(nonStaticDatabase.getStaffHashMap());
                Database.setCourseHashMap(nonStaticDatabase.getCourseHashMap());
                Database.setUnitHashMap(nonStaticDatabase.getUnitHashMap());
                Database.setLinkedUnitHashMap(nonStaticDatabase.getLinkedUnitHashMap());


            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Data not found");
                c.printStackTrace();
                return;
            }
        }

    }

}
