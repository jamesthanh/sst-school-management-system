package sample.model;

import java.io.Serializable;

public class Unit implements Serializable {
    private String code;
    private String name;
    private int semester;
    private Staff lecturer;
    private Staff examiner;

    public Unit(String code, String name, int semester, Staff lecturer, Staff examiner) {
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.lecturer = lecturer;
        this.examiner = examiner;
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Staff getLecturer() {
        return lecturer;
    }

    public void setLecturer(Staff lecturer) {
        this.lecturer = lecturer;
    }

    public Staff getExaminer() {
        return examiner;
    }

    public void setExaminer(Staff examiner) {
        this.examiner = examiner;
    }

    @Override
    public String toString() {
        return code + ",\t" + name ;
    }

    public String toStringForView() {
        return

                        "Unit Code: " + code + "\n"
                        +"Unit Name: " +name + "\n"
                                +"Semester : " +semester + "\n"
                        +"Lecturer: " + lecturer.getName() + "\n"
                        +"Chef Examiner: " + examiner.getName();
    }


}
