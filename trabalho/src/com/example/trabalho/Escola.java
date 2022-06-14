package com.example.trabalho;

import java.util.ArrayList;
import java.util.Scanner;

public class Escola {
	public static boolean criarAluno(Scanner scanIn, java.sql.Connection connection) {
		String nome;
		String cpf;
		String email; 
		String celular; 
		String endereco;
		
		System.out.print("Digite o nome do aluno: ");
		nome = scanIn.nextLine();
		do {
			System.out.print("Digite o CPF do aluno (APENAS NUMEROS): ");
			cpf = scanIn.nextLine();			
		} while (Helper.verificarApenasNumeros(cpf) == false);
		System.out.print("Digite o email do aluno: ");
		email = scanIn.nextLine();
		do {			
			System.out.print("Digite o numero de celular do aluno (APENAS NUMEROS): ");
			celular = scanIn.nextLine();
		} while (Helper.verificarApenasNumeros(celular) == false);
		System.out.print("Digite o endereco do aluno: ");
		endereco = scanIn.nextLine();
		
		Aluno aluno = new Aluno(nome, cpf, email, celular, endereco);
		
		try {
			return Database.postAluno(aluno, connection);
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean criarProfessor(Scanner scanIn, java.sql.Connection connection) {
		String nome;
		String cpf;
		String email; 
		String celular; 
		String endereco;
		
		System.out.print("Digite o nome do professor: ");
		nome = scanIn.nextLine();
		do {			
			System.out.print("Digite o CPF do professor: ");
			cpf = scanIn.nextLine();
		} while (Helper.verificarApenasNumeros(cpf) == false);
		System.out.print("Digite o email do professor: ");
		email = scanIn.nextLine();
		do {			
			System.out.print("Digite o numero de celular do professor: ");
			celular = scanIn.nextLine();
		} while (Helper.verificarApenasNumeros(celular) == false);
		System.out.print("Digite o endereco do professor: ");
		endereco = scanIn.nextLine();
		
		Professor professor = new Professor(nome, cpf, email, celular, endereco);
		
		try {
			return Database.postProfessor(professor, connection);
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean criarCurso(Scanner scanIn, java.sql.Connection connection) {
		String nome;
		int cargaHoraria;
		String descricao;
		int opSala;
		int opProfessor;
		

		try {
			ArrayList<Sala> salasDisponiveis = Database.getSalas(connection);
			ArrayList<Professor> professoresDisponiveis = Database.getProfessores(connection);	
			
			if(salasDisponiveis.size() != 0) {
				if(professoresDisponiveis.size() != 0) {
					System.out.print("Digite o nome do curso: ");
					nome = scanIn.nextLine();
					System.out.print("Digite a carga horaria do curso: ");
					cargaHoraria = Integer.parseInt(scanIn.nextLine());
					System.out.print("Digite a descricao do curso: ");
					descricao = scanIn.nextLine();
					
					System.out.println("-------------------");
										
					for (int index = 0; index < salasDisponiveis.size(); index++) {
						System.out.println();
						System.out.println(index + ")");
						System.out.println(salasDisponiveis.get(index).toString());
					}
					
					System.out.println("-------------------");
					System.out.println();

					do {
						System.out.print("Escolha a sala digitando o numero acima dela: ");
						opSala = Integer.parseInt(scanIn.nextLine());
						
						if((opSala < 0) || (opSala > (salasDisponiveis.size() - 1))) {
							System.out.println("Opcao invalida");
						}
					} while ((opSala < 0) || (opSala > (salasDisponiveis.size() - 1)));
					System.out.println("-------------------");
					
					for (int index = 0; index < professoresDisponiveis.size(); index++) {
						System.out.println();
						System.out.println(index + ")");
						System.out.println(professoresDisponiveis.get(index).toString());
					}
					
					System.out.println("-------------------");
					System.out.println();
					
					do {
						System.out.print("Escolha o professor digitando o numero acima dele(a): ");
						opProfessor = Integer.parseInt(scanIn.nextLine());
						
						if((opProfessor < 0) || (opProfessor > (professoresDisponiveis.size() - 1))) {
							System.out.println("Opcao invalida");
						}
					} while ((opProfessor < 0) || (opProfessor > (professoresDisponiveis.size() - 1)));
							
					Curso curso = new Curso(nome, cargaHoraria, descricao, salasDisponiveis.get(opSala).getCodSala(), professoresDisponiveis.get(opProfessor).getCodFuncionario());
					
					try {
						return Database.postCurso(curso, connection);
					} catch (Exception e) {
						return false;
					}
				} else {
					System.out.println("Não ha professores cadastrados");
					return false;
				}
			}else {
				System.out.println("Não ha salas disponiveis para um novo curso");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro em buscar professores e salas por favor tente mais tarde");
			return false;
		}		
	}
	
	public static boolean criarSala(Scanner scanIn, java.sql.Connection connection) {
		String nome; 
		String local; 
		int capacidade;
		
		System.out.print("Digite o nome da sala: ");
		nome = scanIn.nextLine();
		System.out.print("Digite o local da sala: ");
		local = scanIn.nextLine();
		System.out.print("Digite a capacidade da sala: ");
		capacidade = Integer.parseInt(scanIn.nextLine());
		
		Sala sala = new Sala(nome, local, capacidade);
		
		try {
			return Database.postSala(sala, connection);
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean vincularAluno(Scanner scanIn, java.sql.Connection connection) {
		int opAluno = 0;
		int opCurso = 0;

		try {
			ArrayList<Aluno> listaAlunos = Database.getAlunos(connection);
			
				if(listaAlunos.size() != 0) {
					System.out.println("-------------------");
										
					for (int index = 0; index < listaAlunos.size(); index++) {
						System.out.println();
						System.out.println(index + ")");
						System.out.println(listaAlunos.get(index).toString());
					}
					System.out.println("-------------------");
					System.out.println();
					do {
						System.out.print("Escolha o aluno digitando o numero acima dele(a): ");
						opAluno = Integer.parseInt(scanIn.nextLine());
						
						if((opAluno < 0) || (opAluno > (listaAlunos.size() - 1))) {
							System.out.println("Opcao invalida");
						}
					} while ((opAluno < 0) || (opAluno > (listaAlunos.size() - 1)));
					System.out.println("-------------------");
					
					ArrayList<Curso> listaCursos = Database.getCursoForVincularAluno(listaAlunos.get(opAluno).getMatricula(), connection);
					
					if(listaCursos.size() != 0) {
						for (int index = 0; index < listaCursos.size(); index++) {
							System.out.println();
							System.out.println(index + ")");
							System.out.println(listaCursos.get(index).toString());
						}
						
						System.out.println("-------------------");
						System.out.println();
						
						do {
							System.out.print("Escolha o curso digitando o numero acima dele: ");
							opCurso = Integer.parseInt(scanIn.nextLine());
							
							if((opCurso < 0) || (opCurso > (listaCursos.size() - 1))) {
								System.out.println("Opcao invalida");
							}
						} while ((opCurso < 0) || (opCurso > (listaCursos.size() - 1)));
					} else {
						System.out.println("Não ha cursos cadastrados");
						return false;
					}
												
					try {
						return Database.postVincularAluno(listaAlunos.get(opAluno).getMatricula(), listaCursos.get(opCurso).getCodCurso(), connection);
					} catch (Exception e) {
						return false;
					}
				} else {
					System.out.println("Não ha alunos cadastrados");
					return false;
				}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void listarCursos(java.sql.Connection connection) {
		try {
			ArrayList<Curso> cursos = Database.getCursos(connection);
			
			if(cursos.size() != 0) {
				for (Curso curso : cursos) {
					Professor professor = Database.getProfessorByCurso(curso.getCodCurso(), connection);
					Sala sala = Database.getSalaByCurso(curso.getCodCurso(), connection);
					ArrayList<Aluno> alunos = Database.getAlunosByCurso(curso.getCodCurso(), connection);
					
					System.out.println();
					System.out.println(curso.toString());
					System.out.println("\n------Professor------");
					System.out.println(professor.toString());
					System.out.println("---------------------");
					System.out.println("\n--------Sala---------");
					System.out.println(sala.toString());
					System.out.println("---------------------");
					System.out.println("\n-------Alunos--------");
					if(alunos.size() != 0) {
						for (Aluno aluno : alunos) {
							System.out.println(aluno.toString());
							System.out.println("---------------------");
						}
					} else {
						System.out.println("Não ha alunos nesse curso");
					}
					System.out.println("---------------------");
				}
			} else {
				System.out.println("Não ha cursos existentes");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro em listar os cursos");
		}
	}
	
	public static void menu() {
		System.out.println("------Menu------");
		System.out.println("1 - Cadastrar novo aluno");
		System.out.println("2 - Cadastrar novo professor");
		System.out.println("3 - Cadastrar novo curso");
		System.out.println("4 - Cadastrar nova sala");
		System.out.println("5 - Vincular aluno a um curso");
		System.out.println("6 - Listar cursos");
		System.out.println("0 - Fechar o app");
		System.out.println("----------------");
	}
	
	public static void main(String[] args) {
		int op = 0;
		Scanner scanIn = new Scanner(System.in);
		java.sql.Connection connection = Database.getConnection();
		boolean statusConection = Database.statusConection();
		
		if(statusConection) {
			do {
				menu();
				op = Integer.parseInt(scanIn.nextLine());
				
				switch (op) {
				case 1:
					if(criarAluno(scanIn, connection)) {
						System.out.println("\nAluno cadastrado\n");
					} else {
						System.out.println("\nErro ao cadastrar aluno\n");							
					}
					break;
				case 2:
					if(criarProfessor(scanIn, connection)) {
						System.out.println("\nProfessor cadastrado\n");
					} else {
						System.out.println("\nErro ao cadastrar professor\n");							
					}
					break;
				case 3:
					if(criarCurso(scanIn, connection)) {
						System.out.println("\nCurso cadastrado\n");
					} else {
						System.out.println("\nErro ao cadastrar curso\n");							
					}
					break;
				case 4:
					if(criarSala(scanIn, connection)) {
						System.out.println("\nSala cadastrada\n");
					} else {
						System.out.println("\nErro ao cadastrar sala\n");							
					}
					break;
				case 5:
					if(vincularAluno(scanIn, connection)) {
						System.out.println("\nAluno vinculado com sucesso\n");
					} else {
						System.out.println("\nErro ao vincular aluno\n");							
					}
					break;
				case 6:
					listarCursos(connection);
					break;
				case 0:
					break;
				default:
					System.out.println("Opcao invalida");
					break;
				}
			} while (op != 0);
			
			scanIn.close();
			System.out.println("Até mais");
		} else {
			System.out.println("Erro de conexao com o banco");
		}
		
		Database.fecharConexao(connection);
	}
}
