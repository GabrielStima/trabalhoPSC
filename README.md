# Escola - Trabalho de PSC
## Instruções para rodar

Caso não tenha o MySQL instalado em sua maquina é possível realizar as instalações necessárias usando esse tutorial: [Como Instalar MYSQL](https://www.youtube.com/watch?v=zpssr3u1EO8)

 1. Para iniciar confira se o seu MySQL esteja rodando na sua maquina;
 2. Utilize o manipulador de SQL que quiser para escrever os **scripts**;
 3. Para iniciar execute os seguintes comandos SQL no seu LOCALHOST na porta 3306:

	 3.1.  `CREATE DATABASE escola;`
	
	 3.2. 
```
CREATE TABLE aluno(
	matricula INTEGER PRIMARY KEY auto_increment, 
    nome VARCHAR(100), 
    cpf CHAR(11), 
    endereco VARCHAR(100), 
    email VARCHAR(100), 
    celular CHAR(14)
    );
```
3.3.
```
CREATE TABLE professor(
	codFuncionario INTEGER PRIMARY KEY auto_increment, 
    nome VARCHAR(100), 
    cpf CHAR(11), 
    endereco VARCHAR(100), 
    email VARCHAR(100), 
    celular CHAR(14)
    );
```
3.4.
```
CREATE TABLE sala(
	codSala INTEGER PRIMARY KEY auto_increment, 
    nome VARCHAR(100), 
    local VARCHAR(100), 
    capacidade INTEGER
	);
```
3.5.
```
CREATE TABLE curso(
	codCurso INTEGER PRIMARY KEY auto_increment, 
    nome VARCHAR(100), 
    cargaHorario INTEGER, 
    descricao VARCHAR(255),
    codSala INTEGER,
    codFuncionario INTEGER,
    FOREIGN KEY(codSala) REFERENCES sala(codSala),
    FOREIGN KEY(codFuncionario) REFERENCES professor(codFuncionario)
	);
```
3.6.
```
CREATE TABLE aluno_has_curso(
	matricula INTEGER,
    codCurso INTEGER,
    FOREIGN KEY (matricula) REFERENCES aluno (matricula),
    FOREIGN KEY (codCurso) REFERENCES curso (codCurso)
	);
```
4. Caso tenha que mudar o nome da DATABASE ou do SERVER NAME não se esqueça de alterar no arquivo `Database.java`;
5. No arquivo `Database.java` mude as Strings `username` e `password`  com suas respectivas credenciais de entrado do banco de dados;
6. Após isso configurado confira se no arquivo `Database.java` irá ter algum problema de importação da lib `java.sql` caso tenha:
	6.1. [Clique aqui](https://dev.mysql.com/downloads/connector/j/), e baixe o conector J para configurar a biblioteca de conexão
	6.2. Após baixar seja pelo MySql installer ou por zip;
	6.3. Clique com o botão direito no projeto, vá na opção `Build Path` e clique em `Configure Build Path`;
	6.4. Irá abri uma tela já na visão de `Java Build Path`, clique na aba `Libraries` e caso o conector não exista no Classpath, clique nesse item e nas opção que iram aparecer, clique em `Add External JARs`, e encontre o caminha para o `mysql-connector-java-8*.jar` selecione e clique em abrir;
	6.5. Após isso clique em `Apply and Close`;
	6.6. E por fim vá no arquivo `module-info.java` e escrava dentro do objeto `requires java.sql;`, com isso o erro da lib deve desaparecer;
7. E é só iniciar no arquivo `Escola.java` e utilizar o programa;
