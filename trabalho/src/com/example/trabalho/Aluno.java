package com.example.trabalho;

public class Aluno extends Pessoa{
	private int matricula;

	public Aluno(String nome, String cpf, String email, String celular, String endereco) {
		super(nome, cpf, email, celular, endereco);
	}
	
	public Aluno(int matricula, String nome, String cpf, String email, String celular, String endereco) {
		super(nome, cpf, email, celular, endereco);
		this.matricula = matricula;
	}

	public int getMatricula() {
		return matricula;
	}
	
	@Override
	public String toString() {
		return "Matricula: " + getMatricula() + "\n" +
				"Nome: " + getNome() + "\n" +
				"CPF: " + getCpf() + "\n" +
				"E-mail: " + getEmail() + "\n" +
				"Celular: " + getCelular() + "\n" +
				"Endereço: " + getEndereco();
	}
}