package sample.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class Database implements Serializable {
    private static HashMap<String, Staff> staffHashMap = new HashMap<>();
    private static HashMap<String, Unit> unitHashMap = new HashMap<>();
    private static HashMap<String, Course> courseHashMap = new HashMap<>();
    private static HashMap<String, Set<Unit>> linkedUnitHashMap = new HashMap<>();

    public Database(){

    }

    public static HashMap<String, Staff> getStaffHashMap() {
        return staffHashMap;
    }

    public static void setStaffHashMap(HashMap<String, Staff> staffHashMap) {
        Database.staffHashMap = staffHashMap;
    }

    public static HashMap<String, Unit> getUnitHashMap() {
        return unitHashMap;
    }

    public static void setUnitHashMap(HashMap<String, Unit> unitHashMap) {
        Database.unitHashMap = unitHashMap;
    }

    public static HashMap<String, Course> getCourseHashMap() {
        return courseHashMap;
    }

    public static void setCourseHashMap(HashMap<String, Course> courseHashMap) {
        Database.courseHashMap = courseHashMap;
    }

    public static HashMap<String, Set<Unit>> getLinkedUnitHashMap() {
        return linkedUnitHashMap;
    }

    public static void setLinkedUnitHashMap(HashMap<String, Set<Unit>> linkedUnitHashMap) {
        Database.linkedUnitHashMap = linkedUnitHashMap;
    }
}
