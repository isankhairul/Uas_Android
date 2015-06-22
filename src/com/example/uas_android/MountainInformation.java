package com.example.uas_android;

public class MountainInformation {

	private int id;
	private String MountainName;
	private String MountainPictSmall;
	private String MountainPictLarge;
	private String DescriptionMountain;
	
	public MountainInformation(int id, String MountainName, String MountainPictSmall, String MountainPictLarge, String DescriptionMountain) {
		super();
		this.id = id;
		this.MountainName = MountainName;
		this.MountainPictSmall = MountainPictSmall;
		this.MountainPictLarge = MountainPictLarge;
		this.DescriptionMountain = DescriptionMountain;
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
		
}
