package sample.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class NonStaticDatabase implements Serializable {
    private  HashMap<String, Staff> staffHashMap = new HashMap<>();
    private  HashMap<String, Unit> unitHashMap = new HashMap<>();
    private  HashMap<String, Course> courseHashMap = new HashMap<>();
    private  HashMap<String, Set<Unit>> linkedUnitHashMap = new HashMap<>();

    public NonStaticDatabase(HashMap<String, Staff> staffHashMap, HashMap<String, Unit> unitHashMap, HashMap<String, Course> courseHashMap, HashMap<String, Set<Unit>> linkedUnitHashMap) {
        this.staffHashMap = staffHashMap;
        this.unitHashMap = unitHashMap;
        this.courseHashMap = courseHashMap;
        this.linkedUnitHashMap = linkedUnitHashMap;
    }

    public HashMap<String, Staff> getStaffHashMap() {
        return staffHashMap;
    }

    public void setStaffHashMap(HashMap<String, Staff> staffHashMap) {
        this.staffHashMap = staffHashMap;
    }

    public HashMap<String, Unit> getUnitHashMap() {
        return unitHashMap;
    }

    public void setUnitHashMap(HashMap<String, Unit> unitHashMap) {
        this.unitHashMap = unitHashMap;
    }

    public HashMap<String, Course> getCourseHashMap() {
        return courseHashMap;
    }

    public void setCourseHashMap(HashMap<String, Course> courseHashMap) {
        this.courseHashMap = courseHashMap;
    }

    public HashMap<String, Set<Unit>> getLinkedUnitHashMap() {
        return linkedUnitHashMap;
    }

    public void setLinkedUnitHashMap(HashMap<String, Set<Unit>> linkedUnitHashMap) {
        this.linkedUnitHashMap = linkedUnitHashMap;
    }
}
