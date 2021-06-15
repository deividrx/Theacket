package theacket;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;
import java.util.Scanner;

public class Console {

	public static Scanner userInput = new Scanner(System.in);
	public static char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static String logo = " _____  _                         _          _   \n" +
			"|_   _|| |                       | |        | |  \n" +
			"  | |  | |__    ___   __ _   ___ | | __ ___ | |_ \n" +
			"  | |  | '_ \\  / _ \\ / _` | / __|| |/ // _ \\| __|\n" +
			"  | |  | | | ||  __/| (_| || (__ |   <|  __/| |_ \n" +
			"  \\_/  |_| |_| \\___| \\__,_| \\___||_|\\_\\\\___| \\__|\n";
	public static String modos = "Códigos:\n" +
			" [1]     entrar no modo cliente\n" +
			" [2]     entrar no modo administrativo\n" +
			" [3]     sair do programa\n";

	public static String comandosCli = "Comandos:\n" +
			"comin       comprar ingresso\n" +
			"CustPol     ver custo das poltronas\n" +
			"sair        sair do programa\n";
	public static String areas = "Áreas:\n" +
			" [1]        Plateia A\n" +
			" [2]        Plateia B\n" +
			" [3]        Frisas\n" +
			" [4]        Camarotes\n" +
			" [5]        Balcão Nobre\n";

	public static void main(String[] args) {
		System.out.print(logo + modos);
		int codigo;

		do {
			System.out.print(colorize("[modo]", YELLOW_TEXT()) + " Informe em qual modo deseja entrar: ");
			codigo = validaEntradaInt();

			if (codigo != 1 && codigo != 2 && codigo != 3) {
				errorMes("Modo \"" + codigo + "\" inválido!\n");
			}

		} while (codigo != 1 && codigo != 2 && codigo != 3);

		String comando;
		if (codigo == 1) {
			System.out.print(comandosCli);
			do {
        		System.out.print(colorize("cliente", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
        		comando = userInput.next();

        		switch (comando) {
					case "comin":
						System.out.print(areas);
						int area;
						do {
							System.out.print("Informe a área: ");
							area = validaEntradaInt();

							if (area != 1 && area != 2 && area != 3 && area != 4 && area != 5) {
								errorMes("Área \"" + area + "\" inválido!\n");
							}

						} while (area != 1 && area != 2 && area != 3 && area != 4 && area != 5);

						switch (area) {
							case 1:
								mostraPoltrona(Cliente.plateiaA, "Plateia A");
								inputPoltrona(Cliente.plateiaA, "Informe a Poltrona: ");
								break;
							case 2:
								break;
						}





						break;
					case "":
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
			errorMes("Entrada inválida! Informe novamente: ");
			userInput.next();
		}
		input = userInput.nextInt();
		return input;
	}

	public static void mostraPoltrona(int[][] plateia, String plateiaText) {
		int cadeira = 1;
		System.out.println(plateiaText + ":");
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


}
