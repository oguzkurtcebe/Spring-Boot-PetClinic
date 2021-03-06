package com.oguzkurtcebe.petclinic.web;

import java.net.URI;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oguzkurtcebe.petclinic.exception.OwnerNotFoundException;
import com.oguzkurtcebe.petclinic.model.Owner;
<<<<<<< HEAD
import com.oguzkurtcebe.petclinic.service.PetClinicMailService;
import com.oguzkurtcebe.petclinic.service.PetClinicService;
import com.oguzkurtcebe.petclinic.service.PetClinicServiceImpl;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {
	@Autowired
	PetClinicService petClinicservice;
    


	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}", produces = "application/json")
	public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable("id") Long id) {
		try {
			Owner owner = petClinicservice.findOwner(id);
			Link self = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withSelfRel();
			Link create = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner").withRel("create");
			Link update = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id)
					.withRel("update");
			Link delete = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id)
					.withRel("delete");

			Resource<Owner> resource = new Resource<Owner>(owner, self, create, update, delete);

			return ResponseEntity.ok(resource);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
		try {
			petClinicservice.findOwner(id);
			petClinicservice.deleteOwner(id);
			return ResponseEntity.ok().build();
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
	public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {

		try {
			Owner owner = petClinicservice.findOwner(id);
			owner.setFirstName(ownerRequest.getFirstName());
			owner.setLastName(ownerRequest.getLastName());
			petClinicservice.updateOwner(owner);
			return ResponseEntity.ok().build();

		} catch (OwnerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/owner")
	public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
		
		try {
			petClinicservice.createOwner(owner);
			Long id = owner.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			
			return ResponseEntity.created(location).build();
			
		}catch(ConstraintViolationException ex) {
			return  ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
		
		
		
		catch (Exception e) {
			
			  
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Cacheable("allOwners")
	@RequestMapping(method = RequestMethod.GET, value = "/owners")
	public ResponseEntity<List<Owner>> getOwners() {
		System.out.println(">>>>inside getOwnerss...");
		List<Owner> owner = petClinicservice.findOwners();
		return ResponseEntity.ok(owner);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner")
	public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastname) {
		
		try {
			List<Owner> owner = petClinicservice.findOwners(lastname);return ResponseEntity.ok(owner);
		} catch (Exception e) {
				
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
	public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
		try {
			Owner owner = petClinicservice.findOwner(id);
			return ResponseEntity.ok(owner);
		} catch (OwnerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}

	}
	
=======
import com.oguzkurtcebe.petclinic.service.PetClinicService;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {
	@Autowired
	PetClinicService petClinicservice;

	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}", produces = "application/json")
	public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable("id") Long id) {
		try {
			Owner owner = petClinicservice.findOwner(id);
			Link self = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withSelfRel();
			Link create = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner").withRel("create");
			Link update = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id)
					.withRel("update");
			Link delete = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id)
					.withRel("delete");

			Resource<Owner> resource = new Resource<Owner>(owner, self, create, update, delete);

			return ResponseEntity.ok(resource);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
		try {
			petClinicservice.findOwner(id);
			petClinicservice.deleteOwner(id);
			return ResponseEntity.ok().build();
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
	public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {

		try {
			Owner owner = petClinicservice.findOwner(id);
			owner.setFirstName(ownerRequest.getFirstName());
			owner.setLastName(ownerRequest.getLastName());
			petClinicservice.updateOwner(owner);
			return ResponseEntity.ok().build();

		} catch (OwnerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/owner")
	public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
		try {
			petClinicservice.createOwner(owner);
			Long id = owner.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		}catch(ConstraintViolationException ex) {
			return  ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
		
		
		
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Cacheable("allOwners")
	@RequestMapping(method = RequestMethod.GET, value = "/owners")
	public ResponseEntity<List<Owner>> getOwners() {
		System.out.println(">>>>inside getOwnerss...");
		List<Owner> owner = petClinicservice.findOwners();
		return ResponseEntity.ok(owner);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner")
	public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastname) {
		List<Owner> owner = petClinicservice.findOwners(lastname);
		return ResponseEntity.ok(owner);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
	public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
		try {
			Owner owner = petClinicservice.findOwner(id);
			return ResponseEntity.ok(owner);
		} catch (OwnerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}

	}
>>>>>>> branch 'master' of https://github.com/oguzkurtcebe/Spring-Boot-PetClinic.git
}
