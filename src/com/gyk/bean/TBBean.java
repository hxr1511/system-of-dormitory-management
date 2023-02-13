package com.gyk.bean;

public class TBBean {
    private int TB_ID;
    private int TB_TeacherID;
    private int TB_BuildingID;
    private String Teacher_Username;
    private String Teacher_Name;
    private String Teacher_Sex;
    private String Teacher_Tel;
    private String Building_Name;

    @Override
    public String toString() {
        return "TBBean{" +
                "TB_ID=" + TB_ID +
                ", TB_TeacherID=" + TB_TeacherID +
                ", TB_BuildingID=" + TB_BuildingID +
                ", Teacher_Username='" + Teacher_Username + '\'' +
                ", Teacher_Name='" + Teacher_Name + '\'' +
                ", Teacher_Sex='" + Teacher_Sex + '\'' +
                ", Teacher_Tel='" + Teacher_Tel + '\'' +
                ", Building_Name='" + Building_Name + '\'' +
                '}';
    }

    public int getTB_ID() {
        return TB_ID;
    }

    public void setTB_ID(int TB_ID) {
        this.TB_ID = TB_ID;
    }

    public int getTB_TeacherID() {
        return TB_TeacherID;
    }

    public void setTB_TeacherID(int TB_TeacherID) {
        this.TB_TeacherID = TB_TeacherID;
    }

    public int getTB_BuildingID() {
        return TB_BuildingID;
    }

    public void setTB_BuildingID(int TB_BuildingID) {
        this.TB_BuildingID = TB_BuildingID;
    }

    public String getTeacher_Username() {
        return Teacher_Username;
    }

    public void setTeacher_Username(String teacher_Username) {
        Teacher_Username = teacher_Username;
    }

    public String getTeacher_Name() {
        return Teacher_Name;
    }

    public void setTeacher_Name(String teacher_Name) {
        Teacher_Name = teacher_Name;
    }

    public String getTeacher_Sex() {
        return Teacher_Sex;
    }

    public void setTeacher_Sex(String teacher_Sex) {
        Teacher_Sex = teacher_Sex;
    }

    public String getTeacher_Tel() {
        return Teacher_Tel;
    }

    public void setTeacher_Tel(String teacher_Tel) {
        Teacher_Tel = teacher_Tel;
    }

    public String getBuilding_Name() {
        return Building_Name;
    }

    public void setBuilding_Name(String building_Name) {
        Building_Name = building_Name;
    }

    public TBBean(int TB_ID, int TB_TeacherID, int TB_BuildingID, String teacher_Username, String teacher_Name, String teacher_Sex, String teacher_Tel, String building_Name) {
        this.TB_ID = TB_ID;
        this.TB_TeacherID = TB_TeacherID;
        this.TB_BuildingID = TB_BuildingID;
        Teacher_Username = teacher_Username;
        Teacher_Name = teacher_Name;
        Teacher_Sex = teacher_Sex;
        Teacher_Tel = teacher_Tel;
        Building_Name = building_Name;
    }

    public TBBean() {
    }
}
