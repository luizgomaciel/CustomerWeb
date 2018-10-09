package br.java.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.java.model.bean.Customer;
import br.java.model.dao.CustomerDAO;

@Service("customerModel")
public class CustomerModel {

	@Autowired
	private CustomerDAO dbQuery;

	public ResponseEntity<Object> consultaClientes() {
		List<Customer> clientes = null;
		try {
			clientes = dbQuery.searchCustomers();
		} catch (Exception e) {
		}
		return new ResponseEntity<Object>(clientes, addAccessControllAllowOrigin(), HttpStatus.OK);
	}

	private HttpHeaders addAccessControllAllowOrigin() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		return headers;
	}

	public ResponseEntity<Object> cadastraCliente(Customer cliente) {
		try {
			dbQuery.includeCustomer(cliente);
		} catch (Exception e) {
		}
		return new ResponseEntity<Object>("", addAccessControllAllowOrigin(), HttpStatus.OK);
	}

}
