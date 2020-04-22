

package sample.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Course;
import sample.model.Database;
import sample.model.Staff;
import sample.model.Unit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Controller {
    // declare variables
    @FXML
    private TextField courseName;

    @FXML
    private TextField courseCode;

    @FXML
    private RadioButton underType;

    @FXML
    private RadioButton postType;

    @FXML
    private RadioButton sgsLocation;

    @FXML
    private RadioButton hnLocation;

    @FXML
    private ComboBox<String> courseDirCb;

    @FXML
    private ComboBox<String> courseDepCb;

    @FXML
    private TextArea courseStt;

    @FXML
    private Button submitCourse;

    @FXML
    private ListView<String> linkedUnit;

    @FXML
    private ListView<String> courseList;

    @FXML
    private Button linkUnitBtn;

    @FXML
    private Button courseDeleteBtn;

    @FXML
    private Button courseDetailBtn;


    @FXML
    private TextField unitName;

    @FXML
    private TextField unitCode;

    @FXML
    private RadioButton unitFirstSem;

    @FXML
    private RadioButton unitSecondSem;

    @FXML
    private RadioButton unitThirdSem;

    @FXML
    private RadioButton eeetPrefix;


    @FXML
    private RadioButton coscPrefix;

    @FXML
    private RadioButton oengPrefix;


    @FXML
    private RadioButton levelOne;

    @FXML
    private RadioButton levelTwo;

    @FXML
    private RadioButton levelThree;

    @FXML
    private RadioButton levelFour;


    @FXML
    private RadioButton levelFive;


    @FXML
    private ComboBox<String> lecturerCb;


    @FXML
    private ComboBox<String> examinerCb;

    @FXML
    private TextArea unitStt;

    @FXML
    private Button submitUnit;

    @FXML
    private ListView<String> unitList;

    @FXML
    private Button showUnitDetailBtn;

    @FXML
    private Button deleteUnitBtn;

    @FXML
    private Label unitDetail;

    @FXML
    private ListView<String> avaList;

    @FXML
    private Button linkBtn;




    private static HashMap<String, Staff> staffs;
    private static HashMap<String, Unit> units;
    private static HashMap<String, Course> courses;
    private static HashMap<String, Set<Unit>> linkedUnits;


//Name:initialize
//Purpose: to initialize all data when the system runs
//Returns: preload data
//Output: none
//Effect: pre load all data for user to use.


    @FXML
    public void initialize(){
        staffs = Database.getStaffHashMap();
        units = Database.getUnitHashMap();
        courses = Database.getCourseHashMap();
        linkedUnits = Database.getLinkedUnitHashMap();

        staffs.forEach((k,v)-> courseDirCb.getItems().add(v.toString()));
        staffs.forEach((k,v)-> courseDepCb.getItems().add(v.toString()));
        staffs.forEach((k,v)-> lecturerCb.getItems().add(v.toString()));
        staffs.forEach((k,v)-> examinerCb.getItems().add(v.toString()));


        units.forEach((k,v) ->  unitList.getItems().add(v.toString()));
        courses.forEach((k,v) ->  courseList.getItems().add(v.toString()));

        linkBtn.setDisable(true);

    }
//Name:addUnit
//Purpose: to add unit info to the system
//Returns: unit object with attricutes
//Output: none
//Effect: acts as method to preload unit with validation


    @FXML
    private void addUnit(){
        String name = unitName.getText();
        String code = unitCode.getText();

        int semester;
        if (unitFirstSem.isSelected()){
            semester = 1;
        }
        else if (unitSecondSem.isSelected()){
            semester = 2;
        }
        else if (unitThirdSem.isSelected()){
            semester = 3;
        }
        else {
            semester = 0;
        }

        String semPrefix = "";
        if (eeetPrefix.isSelected()){
            semPrefix = "EEET";
        }
        else if (coscPrefix.isSelected()){
            semPrefix = "COSC";
        }
        else if (oengPrefix.isSelected()){
            semPrefix = "OENG";
        }
        else {
            semPrefix = "Null";
        }


        int unitLv;
        if(levelOne.isSelected()){
            unitLv = 1;
        }
        else if (levelTwo.isSelected()){
            unitLv = 2;
        }
        else if (levelThree.isSelected()){
            unitLv = 3;
        }
        else if (levelFour.isSelected()){
            unitLv = 4;
        }
        else if (levelFive.isSelected()){
            unitLv = 5;
        }
        else {
            unitLv = -1;
        }

        String selectedLecturer = lecturerCb.getValue();
        String selectedExaminer = examinerCb.getValue();
        String completeCode = semPrefix + unitLv + code;


        if (unitLv == -1 || semPrefix.equals("Null") || semester == 0 || name.isEmpty() || code.isEmpty()
            || selectedLecturer == null || selectedExaminer == null ){
            unitStt.setText("Please check again that all fields are filled and in the correct format");


        }

        else if (!code.matches("[0-9]+") || code.length() != 3) {
            unitStt.setText("Unit Code must be an integer and contains of 3 number");

        }
        else if (units.containsKey(completeCode)){
            unitStt.setText("Duplicated unit code");
        }
        else {
            completeCode = semPrefix + unitLv + code;

            String[] codeLecturerElements = selectedLecturer.split(",");
            String lecturerID = codeLecturerElements[0];
            Staff lecturerStaff = staffs.get(lecturerID);

            String[] codeExaminerElements = selectedExaminer.split(",");
            String examinerID = codeExaminerElements[0];
            Staff examinerStaff = staffs.get(examinerID);

            Unit unit = new Unit(completeCode,name,semester,lecturerStaff,examinerStaff);
            units.put(completeCode,unit);
            reloadUnitList();
            reloadUnitAddingElements();
        }
    }

//Name:deleteUnit
//Purpose: to delete a selected unit from the listview
//Returns: no unit found
//Output: none
//Effect: delete the unit when its no needed

    @FXML
    private void deleteUnit() {
        String selectedItem = unitList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String[] itemElements = selectedItem.split(",");
            String deleteUnitID = itemElements[0];
            units.remove(deleteUnitID);
            linkedUnits.forEach((k,v)-> v.removeIf(unit -> unit.getCode().equals(deleteUnitID)));
            reloadUnitList();
            unitDetail.setText("");

        }
    }


//Name:OnClickUnitListViewEventHandler
//Purpose: to control the click event from the user
//Returns: selected unit
//Output: none
//Effect: get data from the clicking event of user

    @FXML
    private void onClickUnitListViewEventHandler(){
        String selectedItem = unitList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String[] itemElements = selectedItem.split(",");
            String showUnitID = itemElements[0];
            Unit unit = units.get(showUnitID);
            unitDetail.setText(unit.toStringForView());

        }
    }

    //Name:reloadUnitList
//Purpose: to refresh the unit listview when needed
//Returns: new loaded list view
//Output: none
//Effect: refresh the listview table when needed


    private void reloadUnitList(){

        unitList.getItems().clear();
        units.forEach((k,v) ->  unitList.getItems().add(v.toString()));
    }

    //Name:reloadUnitAddingElements
//Purpose: to clear out the input fields
//Returns: cleared input fields
//Output: none
//Effect: makes it easier for the user to put new data when he added.

    private void reloadUnitAddingElements(){
        unitName.clear();
        unitFirstSem.setSelected(false);
        unitSecondSem.setSelected(false);
        unitThirdSem.setSelected(false);
        unitCode.clear();
        eeetPrefix.setSelected(false);
        coscPrefix.setSelected(false);
        oengPrefix.setSelected(false);
        unitStt.clear();
        levelOne.setSelected(false);
        levelTwo.setSelected(false);
        levelThree.setSelected(false);
        levelFour.setSelected(false);
        levelFive.setSelected(false);
        lecturerCb.getSelectionModel().clearSelection();
        examinerCb.getSelectionModel().clearSelection();
    }

    //Name:addCourse
//Purpose: to add new course data to the software
//Returns: new added course
//Output: none
//Effect: add new course to the database with validations


    @FXML
    private void addCourse(){
        String name = courseName.getText();
        String code = courseCode.getText();
        String type;
        if (underType.isSelected()){
            type = "Undergraduate";
        }
        else if (postType.isSelected()){
            type = "Postgraduate";
        }
        else {
            type = "Null";
        }

        String location;
        if (sgsLocation.isSelected()){
            location = "SGS";
        }
        else if (hnLocation.isSelected()){
            location = "HN";
        }
        else {
            location = "Null";
        }

        String selectedDirector = courseDirCb.getValue();
        String selectedDeputy = courseDepCb.getValue();


        if (name.isEmpty() || code.isEmpty() || selectedDeputy == null || selectedDirector == null ||
        location.equals("Null") || type.equals("Null")){
            courseStt.setText("Please check again that all fields are filled");

        }
        else if (selectedDirector.equals(selectedDeputy)) {
            courseStt.setText("The Director and Deputy can't be the same person");

        }
        else if (!code.matches("[0-9]+") || code.length() != 4) {
            courseStt.setText("Course Code must be an integer and contains 4 number");

        }
        else if (courses.containsKey(code)){
            courseStt.setText("Duplicated Course Code");

        }
        else {
            String[] directorElements = selectedDirector.split(",");
            String directorID = directorElements[0];
            Staff directorStaff = staffs.get(directorID);

            String[] deputyElements = selectedDeputy.split(",");
            String deputyID = deputyElements[0];
            Staff deputyStaff = staffs.get(deputyID);

            Course course = new Course (code,name,type,directorStaff,deputyStaff,location);
            courses.put(code,course);
            reloadCourseList();
            reloadCourseAddingElements();
        }


    }


//Name:reloadCourseList
//Purpose: to reload the course listview when needed
//Returns: preload course listview
//Output: none
//Effect: refresh the course table when needed for further functions

    private void reloadCourseList(){
        courseList.getItems().clear();
        courses.forEach((k,v) ->  courseList.getItems().add(v.toString()));
    }

    //Name:reloadCourseAddingElements
//Purpose: to clear out the course input elements
//Returns: clear input fields
//Output: none
//Effect: make it easier for user to input dta for course again


    private void reloadCourseAddingElements() {
        courseName.clear();
        courseCode.clear();
        courseDepCb.getSelectionModel().clearSelection();
        courseDirCb.getSelectionModel().clearSelection();
        underType.setSelected(false);
        postType.setSelected(false);
        sgsLocation.setSelected(false);
        hnLocation.setSelected(false);
        courseStt.clear();

    }

    //Name:onClickCourseListViewEventHandler
//Purpose: to handle event click from user in Course List View
//Returns: selected course
//Output: none
//Effect: return data for further functions

    @FXML
    private void onClickCourseListViewEventHandler(){
        String selectedItem = courseList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String[] itemElements = selectedItem.split(",");
            String showCourseID = itemElements[0];
            Course course = courses.get(showCourseID);
            courseStt.setText(course.toStringForView());
            displayLinkedUnits(showCourseID);
        }
    }

    //Name:deleteCourse
//Purpose: to delete selected course
//Returns: deleted course
//Output: none
//Effect: delete the courses when they are no longer  needed

    @FXML
    private void deleteCourse() {
        String selectedItem = courseList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String[] itemElements = selectedItem.split(",");
            String deleteCourseID = itemElements[0];
            courses.remove(deleteCourseID);
            linkedUnits.remove(deleteCourseID);
            linkedUnit.getItems().clear();
            linkBtn.setDisable(true);
            avaList.getItems().clear();

            reloadCourseList();
            courseStt.setText("");


        }
    }

    String linkCourseID;

    //Name:linkUnitToCourse
//Purpose: link the unit to the courses with validation and check
//Returns: linked course and list
//Output: none
//Effect: link course and units


    @FXML
    private void linkUnitToCourse(){
        String selectedItem = courseList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] itemElements = selectedItem.split(",");
            linkCourseID = itemElements[0];

            avaList.getItems().clear();
            units.forEach((k,v) ->  avaList.getItems().add(v.toString()));
            linkBtn.setDisable(false);



        }

    }

    //Name:validateLinkUnit
//Purpose: to check if the units are link with correct course
//Returns: correct linked units and course
//Output: none
//Effect: check of connection between units and courses


    @FXML
    private void validateLinkUnit(){
        String selectedItem = avaList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] itemElements = selectedItem.split(",");
            String linkUnitID = itemElements[0];

            int unitLevel = Integer.parseInt(linkUnitID.substring(4,5));
            Course course = courses.get(linkCourseID);


            String courseType = course.getType();

            if ((courseType.equals("Undergraduate") &&  unitLevel<4) || (courseType.equals("Postgraduate")
            && unitLevel >=4)){
                if (linkedUnits.containsKey(linkCourseID)){
                    Set <Unit> linkSetUnit = linkedUnits.get(linkCourseID);
                    Unit unit = units.get(linkUnitID);
                    linkSetUnit.add(unit);
                    linkedUnits.put(linkCourseID,linkSetUnit);

                }
                else {
                    Set <Unit> linkSetUnit = new HashSet<>();
                    Unit unit = units.get(linkUnitID);
                    linkSetUnit.add(unit);
                    linkedUnits.put(linkCourseID,linkSetUnit);

                }
                courseStt.setText("unit added");
                displayLinkedUnitCourse();
                linkBtn.setDisable(true);
            }
            else {
                courseStt.setText("The unit code does not belong to the program.");
            }


        }

    }

    //Name:displayLinkUnitCourse
//Purpose: display the courses that are associated with units
//Returns: displayed link between courses and units
//Output: none
//Effect: show connections between units and courses


    private void displayLinkedUnitCourse(){
        linkedUnit.getItems().clear();
        Set<Unit> linkedUnitSet = linkedUnits.get(linkCourseID);
        for(Unit unit:linkedUnitSet){
            linkedUnit.getItems().add(unit.toString());
        }
    }

    //Name:displayLinkUnitCourse
//Purpose: display the courses that are associated with units
//Returns: displayed link between courses and units
//Output: none
//Effect: show connections between units and courses
    private void displayLinkedUnits(String code){
        linkedUnit.getItems().clear();
        if(linkedUnits.containsKey(code)){
            Set<Unit> linkedUnitSet = linkedUnits.get(code);
            for(Unit unit:linkedUnitSet){
                linkedUnit.getItems().add(unit.toString());
            }
        }

    }


































}
