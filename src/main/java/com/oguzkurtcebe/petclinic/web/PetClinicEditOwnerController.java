package com.oguzkurtcebe.petclinic.web;

import java.lang.invoke.MethodType;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oguzkurtcebe.petclinic.model.Owner;
import com.oguzkurtcebe.petclinic.service.PetClinicService;

@Controller
public class PetClinicEditOwnerController {

	@Autowired
	private PetClinicService petClinicService;	
	
	@RequestMapping(value="/owners/update/{id}",method=RequestMethod.GET)
	public String lodadOwner(@PathVariable Long id,ModelMap modelMap) {
		Owner owner=petClinicService.findOwner(id);
		modelMap.put("owner", owner);
		return "editOwner";
	}

	@RequestMapping(value="/owners/update/{id}",method=RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute @Valid Owner owner,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "editOwner";
		}
		
		petClinicService.updateOwner(owner);
		
		
		return "redirect:/owners";
	}

}
