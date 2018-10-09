package br.java.model.bean;

import java.io.Serializable;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = -6136319818419614978L;
	private Long id;
	private String razao;
	private String nome;
	private String cpfcnpj;
	private String telefone;
	private String stats;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static Customer getInstance() {
		return new Customer();
	}

	public Customer fromJson(JSONObject json) {
		Customer customer = Customer.getInstance();
		customer.setNome(json.getString("nome"));
		customer.setRazao(json.getString("razao"));
		customer.setStats(json.getString("status"));
		customer.setTelefone(json.getString("telefone"));

		return customer;
	}

}
