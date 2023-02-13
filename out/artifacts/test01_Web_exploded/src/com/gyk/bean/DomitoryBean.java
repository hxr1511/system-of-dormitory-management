package com.gyk.bean;

import java.util.List;

/**
 * Àﬁ…·£∫
 * 	Domitory_ID Àﬁ…·id∫≈
 * 	Domitory_BuildingID Àﬁ…·¬•±‡∫≈
 * 	Domitory_Name Àﬁ…·√˚
 * 	Domitory_Type Àﬁ…·¿‡–Õ
 * 	Domitory_Number Àﬁ…·∫≈
 * 	Building_Name Àﬁ…·¬•√˚≥∆
 */
public class DomitoryBean {

	private int Domitory_ID;
	private int Domitory_BuildingID;
	private String Domitory_Name;
	private String Domitory_Type;
	private String Domitory_Number;
//	private String Domitory_Tel;
	private String Domitory_BuildingName;

	@Override
	public String toString() {
		return "DomitoryBean{" +
				"Domitory_ID=" + Domitory_ID +
				", Domitory_BuildingID=" + Domitory_BuildingID +
				", Domitory_Name='" + Domitory_Name + '\'' +
				", Domitory_Type='" + Domitory_Type + '\'' +
				", Domitory_Number='" + Domitory_Number + '\'' +
				", Domitory_BuildingName='" + Domitory_BuildingName + '\'' +
				'}';
	}

	public DomitoryBean(Integer domitory_buildingID, String domitory_name, String domitory_type, String domitory_number, String domitory_BuildingName) {
		Domitory_BuildingID = domitory_buildingID;
		Domitory_Name = domitory_name;
		Domitory_Type = domitory_type;
		Domitory_Number = domitory_number;
		Domitory_BuildingName = domitory_BuildingName;
	}

	public int getDomitory_ID() {
		return Domitory_ID;
	}

	public void setDomitory_ID(int domitoryID) {
		Domitory_ID = domitoryID;
	}

	public int getDomitory_BuildingID() {
		return Domitory_BuildingID;
	}

	public void setDomitory_BuildingID(int domitoryBuildingID) {
		Domitory_BuildingID = domitoryBuildingID;
	}

	public String getDomitory_Name() {
		return Domitory_Name;
	}

	public void setDomitory_Name(String domitoryName) {
		Domitory_Name = domitoryName;
	}

	public String getDomitory_Type() {
		return Domitory_Type;
	}

	public void setDomitory_Type(String domitoryType) {
		Domitory_Type = domitoryType;
	}

	public String getDomitory_Number() {
		return Domitory_Number;
	}

	public void setDomitory_Number(String domitoryNumber) {
		Domitory_Number = domitoryNumber;
	}




	public String getDomitory_BuildingName() {
		return Domitory_BuildingName;
	}

	public void setDomitory_BuildingName(String domitory_BuildingName) {
		Domitory_BuildingName = domitory_BuildingName;
	}

	public DomitoryBean() {
	}

	public DomitoryBean(int domitory_ID, int domitory_BuildingID, String domitory_Name, String domitory_Type, String domitory_Number, String domitory_BuildingName) {
		Domitory_ID = domitory_ID;
		Domitory_BuildingID = domitory_BuildingID;
		Domitory_Name = domitory_Name;
		Domitory_Type = domitory_Type;
		Domitory_Number = domitory_Number;
		Domitory_BuildingName = domitory_BuildingName;
	}
}
