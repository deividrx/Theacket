package theacket;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;
import java.util.Scanner;

public class Console {

	public static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.print(getLogo() + "\n" + getModos());
		String modo;

        do {
        	System.out.print(colorize("[modo]", YELLOW_TEXT()) + " Informe em qual modo deseja entrar: ");
        	modo = userInput.next();

        	if (!modo.equals("cli") && !modo.equals("adm") && !modo.equals("sair")) {
        		erroMes("Modo \"" + modo + "\" inválido!");
        	}

        } while (!modo.equals("cli") && !modo.equals("adm") && !modo.equals("sair"));

        if (modo.equals("cli")) {
        	String comando;
        	
        	do {
        		System.out.print(colorize("cliente", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
        		comando = userInput.next();
        		switch (comando) {
        		default:
        			erroMes("Comando \"" + comando + "\" inválido!");
        		}
        		
        	} while (!comando.equals("sair"));
        }
	}
	
	public static String getLogo() {
		return " _____  _                         _          _   \n" +
				"|_   _|| |                       | |        | |  \n" +
				"  | |  | |__    ___   __ _   ___ | | __ ___ | |_ \n" +
				"  | |  | '_ \\  / _ \\ / _` | / __|| |/ // _ \\| __|\n" +
				"  | |  | | | ||  __/| (_| || (__ |   <|  __/| |_ \n" +
				"  \\_/  |_| |_| \\___| \\__,_| \\___||_|\\_\\\\___| \\__|\n";
	}
	
	public static String getModos() {
		return "Modos:\n" +
				"cli     entrar no modo cliente\n" +
				"adm     entrar no modo administrativo\n" +
				"sair    sair do programa\n";
	}

	public static void erroMes(String text) {
		System.out.println(colorize("[ERRO] " + text, RED_TEXT()));
	}

	public static int validaEntradaInt(String mensagem) {
		int input;
		while (!userInput.hasNextInt()) {
			erroMes(mensagem);
			userInput.next();
		}
		input = userInput.nextInt();
		return input;
	}




	public static char geraLetra(int num) {
		char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		return alfabeto[num];
	}

	public static void mostraPoltrona(int[][] plateia) {
		int cadeira = 1;
		for (int i = 0; i < plateia.length; i++) {
			for (int j = 0; j < plateia[0].length; j++) {
				if (plateia[i][j] == 0) {
					System.out.printf("%s  ", colorize("[" + cadeira + Character.toUpperCase(geraLetra(i)) + "]", BLACK_TEXT() ,GREEN_BACK()));
				} else {
					System.out.printf("%s  ", colorize("[" + cadeira + Character.toUpperCase(geraLetra(i)) + "]", RED_BACK()));
				}
				cadeira++;
			}
			System.out.println();
			cadeira = 1;
		}
		System.out.println("\nLegenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Ocupado | " + colorize("    ", GREEN_BACK()) + " Livre");
	}

	public static int recebePoltrona(String poltrona) {


		return 0;
	}

}
