package com.example.trabalho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
	public static boolean status = false;
	
	public static java.sql.Connection getConnection() {
		Connection connection = null;
		
		try {
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			String serverName = "localhost";	
	        String mydatabase ="escola";
	        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	        String username = "root";
	        String password = "root";
	        
	        connection = DriverManager.getConnection(url, username, password);
	        
	        if (connection != null) {
	            status = true;
	        } else {
	            status = false;
	        }
	        
	        return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
            return null;
		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
		}
	}	

	public static boolean statusConection() {		
        return status;
    }	

	public static boolean fecharConexao(java.sql.Connection connection) {
		 try {
			 connection.close();
	            return true;
	        } catch (SQLException e) {
	            return false;
	        }
	}

	public static boolean postAluno(Aluno aluno, java.sql.Connection connection) throws Exception {
		try {
			String sql = "INSERT INTO aluno"
					+ "(nome, cpf, endereco, email, celular) VALUES"
					+ "(?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getCpf());
			ps.setString(3, aluno.getEndereco());
			ps.setString(4, aluno.getEmail());
			ps.setString(5, aluno.getCelular());
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static boolean postProfessor(Professor professor, java.sql.Connection connection) throws Exception {
		try {
			String sql = "INSERT INTO professor"
					+ "(nome, cpf, endereco, email, celular) VALUES"
					+ "(?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, professor.getNome());
			ps.setString(2, professor.getCpf());
			ps.setString(3, professor.getEndereco());
			ps.setString(4, professor.getEmail());
			ps.setString(5, professor.getCelular());
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static boolean postSala(Sala sala, java.sql.Connection connection) throws Exception {
		try {
			String sql = "INSERT INTO sala"
					+ "(nome, local, capacidade) VALUES"
					+ "(?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, sala.getNome());
			ps.setString(2, sala.getLocal());
			ps.setInt(3, sala.getCapacidade());
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static boolean postCurso(Curso curso, java.sql.Connection connection) throws Exception {
		try {
			String sql = "INSERT INTO curso"
					+ "(nome, cargaHorario , descricao, codSala, codFuncionario) VALUES"
					+ "(?,?,?,?,?)";
			
			PreparedStatement  ps = connection.prepareStatement(sql);
			ps.setString(1, curso.getNome());
			ps.setInt(2, curso.getCargaHoraria());
			ps.setString(3, curso.getDescricao());
			ps.setInt(4, curso.getCodSala());
			ps.setInt(5, curso.getCodFuncionario());
			
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static boolean postVincularAluno(int matricula, int codCurso, java.sql.Connection connection) throws Exception {
		try {
			String sqlCapacidadeSala = "SELECT S.capacidade FROM sala as S "
					+ "INNER JOIN curso AS C on C.codSala = S.codSala "
					+ "WHERE C.codCurso = " + codCurso;
			String sqlNumeroAtualAlunos = "SELECT matricula FROM aluno_has_curso " +
					"WHERE codCurso = " + codCurso;
			String sqlVinculaAluno = "INSERT INTO aluno_has_curso "
					+ "(matricula, codCurso) VALUES"
					+ "(?,?)";
			PreparedStatement ps = connection.prepareStatement(sqlCapacidadeSala);
			ResultSet rs;
			
			rs = ps.executeQuery();
			int capacidade = 0;
			
			while(rs.next())
		      {
				capacidade = rs.getInt(1);
		      }
			
			ps = connection.prepareStatement(sqlNumeroAtualAlunos);
			rs = ps.executeQuery();
			
			ArrayList<Integer> alunosDoCurso = new ArrayList<Integer>();
			
			while(rs.next())
		      {
				alunosDoCurso.add(rs.getInt(1));
		      }
			
			
			if(capacidade > alunosDoCurso.size()) {
				ps = connection.prepareStatement(sqlVinculaAluno);
				ps.setInt(1, matricula);
				ps.setInt(2, codCurso);
				ps.executeUpdate();
				
				return true;
			} else {
				System.out.println("\nCapacidade maxima");
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ArrayList<Aluno> getAlunos(java.sql.Connection connection) throws Exception {
		try {
			String sql = "SELECT * FROM aluno";
			ArrayList<Aluno> alunos = new ArrayList<Aluno>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	alunos.add(
	    			new Aluno(
    					rs.getInt(1),
		    			rs.getString(2),
		    			rs.getString(3),
		    			rs.getString(5),
		    			rs.getString(6),
		    			rs.getString(4)
	    			));
		      }
		     
			return alunos;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ArrayList<Professor> getProfessores(java.sql.Connection connection) throws Exception {
		try {
			String sql = "SELECT * FROM professor";
			ArrayList<Professor> professores = new ArrayList<Professor>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	 professores.add(
	    			new Professor(
    					rs.getInt(1),
		    			rs.getString(2),
		    			rs.getString(3),
		    			rs.getString(5),
		    			rs.getString(6),
		    			rs.getString(4)
	    			));
		      }
		     
			return professores;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ArrayList<Sala> getSalas(java.sql.Connection connection) throws Exception {
		try {
			String sql = "SELECT * FROM sala";
			ArrayList<Sala> salas = new ArrayList<Sala>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	 salas.add(
	    			new Sala(
    					rs.getInt(1),
		    			rs.getString(2),
		    			rs.getString(3),
		    			rs.getInt(4)
	    			));
		      }

			return salas;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static ArrayList<Curso> getCursos(java.sql.Connection connection) throws Exception {
		try {
			String sql = "SELECT * FROM curso";
			ArrayList<Curso> cursos = new ArrayList<Curso>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	 cursos.add(
	    			new Curso(
    					rs.getInt(1),
    					rs.getString(2),
    					rs.getInt(3),
		    			rs.getString(4),
		    			rs.getInt(5),
		    			rs.getInt(6)
	    			));
		      }

			return cursos;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static ArrayList<Aluno> getAlunosByCurso(int codCurso, java.sql.Connection connection) throws Exception {
		try {
			String sql = "SELECT " +
					"A.matricula, A.nome, A.cpf, A.endereco, A.email, A.celular " + 
					"FROM aluno as A " +
					"INNER JOIN aluno_has_curso as has on A.matricula = has.matricula " +
					"WHERE has.codCurso = " + codCurso;
			
			ArrayList<Aluno> alunos = new ArrayList<Aluno>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	alunos.add(
	    			new Aluno(
    					rs.getInt(1),
		    			rs.getString(2),
		    			rs.getString(3),
		    			rs.getString(5),
		    			rs.getString(6),
		    			rs.getString(4)
	    			));
		      }
		     
			return alunos;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static Professor getProfessorByCurso(int codCurso, java.sql.Connection connection) throws Exception {
		try {
			String sql = "SELECT " +
					"P.codFuncionario, P.nome, P.cpf, P.endereco, P.email, P.celular " + 
					"FROM professor as P " +
					"INNER JOIN curso as C on P.codFuncionario = C.codFuncionario " +
					"WHERE C.codCurso = " + codCurso;
			
			ArrayList<Professor> professor = new ArrayList<Professor>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	 professor.add(
	    			new Professor(
    					rs.getInt(1),
		    			rs.getString(2),
		    			rs.getString(3),
		    			rs.getString(5),
		    			rs.getString(6),
		    			rs.getString(4)
	    			));
		      }
		     
			return professor.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static Sala getSalaByCurso(int codCurso, java.sql.Connection connection) throws Exception {
		try {
			String sql = "SELECT " +
					"S.codSala, S.nome, S.local, S.capacidade " + 
					"FROM sala as S " +
					"INNER JOIN curso as C on S.codSala = C.codSala " +
					"WHERE C.codCurso = " + codCurso;
			
			ArrayList<Sala> sala = new ArrayList<Sala>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	 sala.add(
	    			new Sala(
    					rs.getInt(1),
		    			rs.getString(2),
		    			rs.getString(3),
		    			rs.getInt(4)
	    			));
		      }
		     
			return sala.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("resource")
	public static ArrayList<Curso> getCursoForVincularAluno(int matricula, java.sql.Connection connection) throws Exception {
		try {
			String sqlCodCurso = "SELECT codCurso FROM " +
					"aluno_has_curso as HAS " + 
					"WHERE HAS.matricula = " + matricula;
			ArrayList<Integer> cursosVinculados = new ArrayList<Integer>();
			ArrayList<Curso> cursosDisponiveis = new ArrayList<Curso>();
			ResultSet rs;
			
			PreparedStatement ps = connection.prepareStatement(sqlCodCurso);
			rs = ps.executeQuery();
			
		     while(rs.next())
		      {
		    	 cursosVinculados.add(rs.getInt(1));
		      }
		     
		     if(cursosVinculados.size() != 0) {
		    	String sql = "SELECT * FROM curso WHERE ";
					
		    	 for (int index = 0; index < cursosVinculados.size(); index++) {
					if(index != 0) {
						sql += " AND codCurso != " + cursosVinculados.get(index);
					} else {
						sql += "codCurso != " + cursosVinculados.get(index);
					}
				}		

		    	ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();
		    	 
			     while(rs.next())
			      {
			    	 cursosDisponiveis.add(
		    			new Curso(
	    					rs.getInt(1),
	    					rs.getString(2),
	    					rs.getInt(3),
			    			rs.getString(4),
			    			rs.getInt(5),
			    			rs.getInt(6)
		    			));
			      }
		     } else {
		    	String sql = "SELECT * FROM curso";
		    	ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();
				
			     while(rs.next())
			      {
			    	 cursosDisponiveis.add(
		    			new Curso(
	    					rs.getInt(1),
	    					rs.getString(2),
	    					rs.getInt(3),
			    			rs.getString(4),
			    			rs.getInt(5),
			    			rs.getInt(6)
		    			));
			      }
		     }
		     
			return cursosDisponiveis;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
