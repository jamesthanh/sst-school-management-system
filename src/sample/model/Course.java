package sample.model;

import java.io.Serializable;

public class Course implements Serializable {
    private String code;
    private String name;
    private String type;
    private Staff courseDir;
    private Staff courseDep;
    private String location;

    public Course(String code, String name, String type, Staff courseDir, Staff courseDep, String location) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.courseDir = courseDir;
        this.courseDep = courseDep;
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Staff getCourseDir() {
        return courseDir;
    }

    public void setCourseDir(Staff courseDir) {
        this.courseDir = courseDir;
    }

    public Staff getCourseDep() {
        return courseDep;
    }

    public void setCourseDep(Staff courseDep) {
        this.courseDep = courseDep;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return code + ",\t" + name ;
    }
    public String toStringForView() {
        return

                "Unit Code: " + code + "\n"
                        +"Course Name: " +name + "\n"
                        +"Type: " +type + "\n"
                        +"Location: " +location + "\n"
                        +"Director: " + courseDir.getName() + "\n"
                        +"Deputy: " + courseDep.getName();
    }

}
