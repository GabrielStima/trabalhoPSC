package com.example.trabalho;

public class Curso {
	private int codCurso;
	private String nome;
	private int cargaHoraria;
	private String descricao;
	private int codSala;
	private int codFuncionario;
	
	public Curso(String nome, int cargaHoraria, String descricao, int codSala, int codFuncionario) {
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.descricao = descricao;
		this.codSala = codSala;
		this.codFuncionario = codFuncionario;
	}
	
	public Curso(int codCurso, String nome, int cargaHoraria, String descricao, int codSala, int codFuncionario) {
		this.codCurso = codCurso;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.descricao = descricao;
		this.codSala = codSala;
		this.codFuncionario = codFuncionario;
	}
	
	public int getCodSala() {
		return codSala;
	}
	public int getCodFuncionario() {
		return codFuncionario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}
	
	@Override
	public String toString() {
		return "Codigo do curso: " + getCodCurso() + "\n" +
				"Nome do curso: " + getNome() + "\n" +
				"Carga horaria: " + getCargaHoraria() + "\n" +
				"Descrição: " + getDescricao() + "\n" +
				"Cod da sala: " + getCodSala() + "\n" +
				"Cod do Professor: " + getCodFuncionario();
	}
}