package com.oguzkurtcebe.petclinic.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oguzkurtcebe.petclinic.model.Owner;
import com.oguzkurtcebe.petclinic.service.PetClinicService;

@Controller
public class PetClinicDeleteOwnerController {

	@Autowired
	private PetClinicService petClinicService;

	@RequestMapping(value = "/owners/delete/{id}", method = RequestMethod.GET)
	public String loadOwner(@PathVariable Long id, ModelMap modelMap) {
		Owner owner = petClinicService.findOwner(id);
		modelMap.put("owner", owner);

		return "deleteOwner";
	}

	@RequestMapping(value = "/owners/delete/{id}", method = RequestMethod.POST)
	public String handleFormSubmit(@PathVariable Long id,RedirectAttributes redirectAttributes) {
         petClinicService.deleteOwner(id);
         redirectAttributes.addFlashAttribute("message","Kayıt silindi id'si ile birlikte silindi..."+id);
         return "redirect:/owners";
	}

}
