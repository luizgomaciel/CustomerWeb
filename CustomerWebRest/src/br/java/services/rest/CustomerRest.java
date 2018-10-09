package br.java.services.rest;

import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.java.model.bean.Customer;
import br.java.model.controller.CustomerModel;

@RestController
@RequestMapping(value = "/rest/customerservice")
@ResponseStatus(HttpStatus.OK)
public class CustomerRest {

	@Autowired
	private CustomerModel ajax;

	@RequestMapping(path = "/includesearchcustomer/{json}", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<Object> cadastro(HttpServletRequest request, @PathVariable String json)
			throws Exception {

		Decoder dec = Base64.getDecoder();
		json = new String(dec.decode(json), "UTF-8");
		Customer cliente = new Customer();
		cliente = cliente.fromJson(new JSONObject(json));
		return ajax.cadastraCliente(cliente);
	}

	@RequestMapping(path = "/includesearchcustomer/", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<Object> consulta(HttpServletRequest request) throws Exception {

		return ajax.consultaClientes();
	}
}
