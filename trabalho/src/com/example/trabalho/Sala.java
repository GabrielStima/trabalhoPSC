package com.example.trabalho;

public class Sala {
	private int codSala;
	private String nome;
	private String local;
	private int capacidade;
	
	public Sala(String nome, String local, int capacidade) {
		this.nome = nome;
		this.local = local;
		this.capacidade = capacidade;
	}
	
	public Sala(int codSala, String nome, String local, int capacidade) {
		this.codSala = codSala;
		this.nome = nome;
		this.local = local;
		this.capacidade = capacidade;
	}

	public int getCodSala() {
		return codSala;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	@Override
	public String toString() {
		return "Codigo da sala: " + getCodSala() + "\n" +
				"Nome da sala: " + getNome() + "\n" +
				"Local: " + getLocal() + "\n" +
				"Capacidade: " + getCapacidade();
	}
}