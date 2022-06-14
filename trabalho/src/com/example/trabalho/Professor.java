package com.example.trabalho;

public class Professor extends Pessoa{
	private int codFuncionario;
	
	public Professor(String nome, String cpf, String email, String celular, String endereco) {
		super(nome, cpf, email, celular, endereco);
	}
	
	public Professor(int codFuncionario, String nome, String cpf, String email, String celular, String endereco) {
		super(nome, cpf, email, celular, endereco);
		this.codFuncionario = codFuncionario;
	}
	
	public int getCodFuncionario() {
		return codFuncionario;
	}	
	
	@Override
	public String toString() {
		return "Codigo de funcionario: " + getCodFuncionario() + "\n" +
				"Nome: " + getNome() + "\n" +
				"CPF: " + getCpf() + "\n" +
				"E-mail: " + getEmail() + "\n" +
				"Celular: " + getCelular() + "\n" +
				"Endereço: " + getEndereco();
	}
}