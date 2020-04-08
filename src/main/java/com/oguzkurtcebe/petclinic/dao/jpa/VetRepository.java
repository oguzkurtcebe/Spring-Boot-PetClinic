package com.oguzkurtcebe.petclinic.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oguzkurtcebe.petclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long>{

}
