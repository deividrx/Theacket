package theacket;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;
import java.util.Scanner;

public class Console {

	public static Scanner userInput = new Scanner(System.in);
	public static char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

	public static void main(String[] args) {
		System.out.print(getLogo() + "\n" + getModos());
		String modo;

		int[][] teste = new int[10][5];
		for (int i = 0; i < 20; i++) {
			mostraPoltrona(teste);
			inputPoltrona(teste,"Informe uma poltrona: " );

		}


		do {
			System.out.print(colorize("[modo]", YELLOW_TEXT()) + " Informe em qual modo deseja entrar: ");
			modo = userInput.next();

			if (!modo.equals("cli") && !modo.equals("adm") && !modo.equals("sair")) {
				errorMes("Modo \"" + modo + "\" inválido!");
			}

		} while (!modo.equals("cli") && !modo.equals("adm") && !modo.equals("sair"));

		String comando;
		if (modo.equals("cli")) {

        	do {
				System.out.println(getComandosCli());
        		System.out.print(colorize("cliente", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
        		comando = userInput.next();
        		switch (comando) {
					case "comin":
						System.out.println("");
						break;
        			default:
        				errorMes("Comando \"" + comando + "\" inválido!");
        		}

        	} while (!comando.equals("sair"));
        }
	}

	public static void errorMes(String text) {
		System.out.print(colorize("[ERRO] " + text, RED_TEXT()));
	}

	public static int validaEntradaInt() {
		int input;
		while (!userInput.hasNextInt()) {
			errorMes("Entrada inválida!");
			userInput.next();
		}
		input = userInput.nextInt();
		return input;
	}

	public static void mostraPoltrona(int[][] plateia) {
		int cadeira = 1;
		for (int i = 0; i < plateia.length; i++) {
			for (int j = 0; j < plateia[0].length; j++) {
				String text = "[" + cadeira + Character.toUpperCase(alfabeto[i]) + "]";

				if (plateia[i][j] == 0) {
					System.out.printf("%s  ", colorize(text, BLACK_TEXT() ,GREEN_BACK()));
				} else {
					System.out.printf("%s  ", colorize(text, RED_BACK()));
				}
				cadeira++;
			}
			System.out.println();
			cadeira = 1;
		}
		System.out.println("\nLegenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Ocupado | " + colorize("    ", GREEN_BACK()) + " Livre");
	}

	public static void inputPoltrona(int[][] plateia, String text) {
		boolean val;
		do {
			System.out.print(text);
			String poltrona = userInput.next().toUpperCase();

			if (poltrona.length() < 2) {
				errorMes("Poltrona inválida!");
				val = false;
			} else {
				String num = poltrona.substring(0, poltrona.length() - 1);
				char letra = poltrona.charAt(poltrona.length() - 1);
				int indexLine = 0;
				int indexColumn;
				Scanner sc = new Scanner(num);

				for (int i = 0; i < alfabeto.length; i++) {
					if (letra == Character.toUpperCase(alfabeto[i])) {
						indexLine = i;
					}
				}

				if (indexLine > (plateia.length - 1)) {
					errorMes("Poltrona inválida!");
					val = false;
				} else if (!sc.hasNextInt()) {
					errorMes("Poltrona inválida!");
					val = false;
					sc.next();
				} else {
					indexColumn = Integer.parseInt(num);
					indexColumn--;
					sc.next();

					if (indexColumn > plateia[0].length) {
						errorMes("Poltrona inválida!");
						val = false;
					} else {
						if (plateia[indexLine][indexColumn] == 0) {
							plateia[indexLine][indexColumn] = 1;
							val = true;
						} else {
							errorMes("Poltrona já ocupada!");
							val = false;
						}
 					}
				}
				sc.close();
			}
			System.out.println();
		} while (!val);
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

	public static String getComandosCli() {
		return "Modos:\n" +
				"comin       comprar ingresso\n" +
				"CustPol     ver custo das poltronas\n" +
				"sair        sair do programa\n";
	}

}
