package com.oguzkurtcebe.petclinic.dao.jpa;

import com.oguzkurtcebe.petclinic.model.Owner;

public interface RegisterRepository {
void create(Owner owner);
void update(Owner owner);
void delete(Long id);
//?????
///?????

}
