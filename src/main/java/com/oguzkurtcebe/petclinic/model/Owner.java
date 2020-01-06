package com.oguzkurtcebe.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="t_owner")
@XmlRootElement
public class Owner extends BaseEntity {

@Column(name="first_name")
@NotEmpty
private String firstName;

@Column(name="last_name")
@NotEmpty
private String lastName;

@OneToMany(mappedBy="owner")
private Set<Pet>pets=new HashSet<>();


public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

@XmlTransient
@JsonIgnore
public Set<Pet> getPets() {
	return pets;
}

public void setPets(Set<Pet> pets) {
	this.pets = pets;
}

@Override
public String toString() {
	return "Owner [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
}

}
