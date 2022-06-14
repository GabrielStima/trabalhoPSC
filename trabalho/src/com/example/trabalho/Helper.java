package com.example.trabalho;

public class Helper {
	public static boolean verificarApenasNumeros(String input) {
		if(!input.matches("\\d+")) {
			System.out.println("Por favor digite apenas numeros\n");
		}
		return input.matches("\\d+");
	}
}
