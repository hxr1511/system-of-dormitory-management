package com.gyk.bean;

/**
 * ËÞÉá½¨Öþ£º
 * 	Building_ID ËÞÉáÂ¥±àºÅ
 * 	Building_Name ËÞÉáÂ¥Ãû×Ö
 * 	Building_Introduction ËÞÉáÂ¥½éÉÜ
 */
public class BuildingBean {

	private int Building_ID;
	private String Building_Name;
	private String Building_Introduction;

	@Override
	public String toString() {
		return "{" +
				"Building_ID=" + Building_ID +
				", Building_Name='" + Building_Name + '\'' +
				", Building_Introduction='" + Building_Introduction + '\'' +
				'}';
	}

	public BuildingBean() {
	}

	public BuildingBean(String building_Name, String building_Introduction) {

		Building_Name = building_Name;
		Building_Introduction = building_Introduction;
	}
	public BuildingBean(int building_ID, String building_Name, String building_Introduction) {
		Building_ID = building_ID;
		Building_Name = building_Name;
		Building_Introduction = building_Introduction;
	}

	public int getBuilding_ID() {
		return this.Building_ID;
	}

	public void setBuilding_ID(int buildingID) {
		Building_ID = buildingID;
	}

	public String getBuilding_Name() {
		return Building_Name;
	}

	public void setBuilding_Name(String buildingName) {
		Building_Name = buildingName;
	}

	public String getBuilding_Introduction() {
		return Building_Introduction;
	}

	public void setBuilding_Introduction(String buildingIntroduction) {
		Building_Introduction = buildingIntroduction;
	}

}
