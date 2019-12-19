package com.oguzkurtcebe.petclinic.model;

import java.util.Date;

public class Pet {
private Long id;
private String name;
private Date birthDate;
private Owner owner;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getBirthDate() {
	return birthDate;
}
public void setBirthDate(Date birtDate) {
	this.birthDate = birtDate;
}
public Owner getOwner() {
	return owner;
}
public void setOwner(Owner owner) {
	this.owner = owner;
}
@Override
public String toString() {
	return "Pet [id=" + id + ", name=" + name + ", birtDate=" + birthDate + ", owner=" + owner + "]";
}



}
