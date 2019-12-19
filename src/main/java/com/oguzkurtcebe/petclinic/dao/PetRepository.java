package com.oguzkurtcebe.petclinic.dao;

import java.util.List;

import com.oguzkurtcebe.petclinic.model.Pet;

public interface PetRepository {

	Pet findById(Long id);
	List<Pet>findByOwnerId(Long ownerId);
	void create(Pet pet);
	void delete(Long id);
	Pet Update(Pet pet);
	void deleteByOwnerId(Long ownerId);// owner Id si verilen bütün pet kayıtlarını silecek
	
	
	
}
