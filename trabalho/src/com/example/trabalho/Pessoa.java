package com.example.trabalho;

public abstract class Pessoa {
	private String nome;
	private String cpf;
	private String email;
	private String celular;
	private String endereco;
	
	public Pessoa(String nome, String cpf, String email, String celular, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.celular = celular;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}