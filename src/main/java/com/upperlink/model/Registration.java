package com.upperlink.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Registration")
public class Registration {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DEVELOPER_ID")
	private Developer developerId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SKILL_ID")
	private Skills skill;

	public Registration() {
		// TODO Auto-generated constructor stub
	}
	
	public Registration( Developer developerId, Skills skill) {
		super();
		this.developerId = developerId;
		this.skill = skill;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Developer getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Developer developerId) {
		this.developerId = developerId;
	}

	public Skills getSkill() {
		return skill;
	}

	public void setSkill(Skills skill) {
		this.skill = skill;
	}

}
