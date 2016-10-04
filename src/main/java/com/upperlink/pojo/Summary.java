package com.upperlink.pojo;

public class Summary {
	
	private int developer;
	private String skills;
	
	public Summary() {
		
	}

	public Summary(int developer, String skills) {
		super();
		this.developer = developer;
		this.skills = skills;
	}

	public int getDeveloper() {
		return developer;
	}

	public void setDeveloper(int developer) {
		this.developer = developer;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

}
