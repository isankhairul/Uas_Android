package com.example.uas_android;

public class MountainInformation {

	
	private int id;
	private String MountainName;
	private String MountainPictSmall;
	private String MountainPictLarge;
	private String DescriptionMountain;
	private String MapPict;
	private String DescriptionMap;
	
	
	public MountainInformation(int id, String MountainName, String MountainPictSmall, String MountainPictLarge, String DescriptionMountain, String MapPict, String DescriptionMap) {
		super();
		this.id = id;
		this.MountainName = MountainName;
		this.MountainPictSmall = MountainPictSmall;
		this.MountainPictLarge = MountainPictLarge;
		this.DescriptionMountain = DescriptionMountain;
		this.MapPict = MapPict;
		this.DescriptionMap = DescriptionMap;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMountainName() {
		return MountainName;
	}
	public void setMountainName(String mountainName) {
		MountainName = mountainName;
	}
	public String getMountainPictSmall() {
		return MountainPictSmall;
	}
	public void setMountainPictSmall(String mountainPictSmall) {
		MountainPictSmall = mountainPictSmall;
	}
	public String getMountainPictLarge() {
		return MountainPictLarge;
	}
	public void setMountainPictLarge(String mountainPictLarge) {
		MountainPictLarge = mountainPictLarge;
	}
	public String getDescriptionMountain() {
		return DescriptionMountain;
	}
	public void setDescriptionMountain(String descriptionMountain) {
		DescriptionMountain = descriptionMountain;
	}
	public String getMapPict() {
		return MapPict;
	}

	public void setMapPict(String mapPict) {
		MapPict = mapPict;
	}

	public String getDescriptionMap() {
		return DescriptionMap;
	}

	public void setDescriptionMap(String descriptionMap) {
		DescriptionMap = descriptionMap;
	}
	
}
