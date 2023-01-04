package com.example.restapi.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="address")
@Data
@Getter
@Setter
public class AddressForStudentEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

  @Column
  private int doorNo;
  @Column
  private String streetName;
  @Column
  private String area;
  @OneToOne
  private StudentEntity stuent;
	
}
