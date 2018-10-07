package br.java.services.rest;

import org.springframework.beans.factory.annotation.Autowired;

import br.java.model.controller.CustomerModel;

@RestController
@RequestMapping(value = "/rest/customerservice")
@ResponseStatus(HttpStatus.OK)
public class CustomerRest {
	
	@Autowired
	private CustomerModel ajax;
	
	

}
