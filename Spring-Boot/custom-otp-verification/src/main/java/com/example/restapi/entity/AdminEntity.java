package com.example.restapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="admin")
@Getter
@Setter
public class AdminEntity {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column
	private String name;
	@Column
	private int age;
	
	public AdminEntity() {
		
	}
	public AdminEntity(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	
	
}
