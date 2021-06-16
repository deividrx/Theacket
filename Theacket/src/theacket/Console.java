package theacket;

import consoleColor.AnsiFormat;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;

import java.util.Scanner;

public class Console {

	public static Scanner userInput = new Scanner(System.in);
	public static char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static AnsiFormat title = new AnsiFormat(BRIGHT_BLUE_TEXT(), BOLD());


	public static void main(String[] args) {
		imprimiLogo();
		imprimiComandos();
		String comando;
		do {
			System.out.print(colorize("Cliente", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
			comando = userInput.next();

			switch (comando) {
				case "comin":
					compraIngresso();
					break;
				case "sair":
					System.out.println("Saindo do programa....");
					break;
				case "ajuda":
					imprimiComandos();
					break;
				default:
					errorMes("Comando \"" + comando + "\" inválido!\n");
			}

		} while (!comando.equals("sair"));
	}

	public static void compraIngresso() {
		String numCPF;
		do {
			System.out.print(colorize("[CPF]", CYAN_TEXT()) + " Informe o seu CPF: ");
			numCPF = userInput.next();

			if (!Cliente.validaCPF(numCPF)){
				errorMes("CPF inválido!\n");
			}

		} while(!Cliente.validaCPF(numCPF));

		mostraArea();
		int area;
		boolean val = true;
		do {
			String areaInvalid = "Área cheia!\n";
			System.out.print(colorize("[ÁREA]", CYAN_TEXT()) + " Informe a área desejada: ");
			area = validaEntradaInt();
			switch (area) {
				case 1:
					if (Cliente.matrizIsFull(Cliente.plateiaA)) {
					 errorMes(areaInvalid);
					 val = false;
					}
					break;
				case 2:
					if (Cliente.matrizIsFull(Cliente.plateiaB)) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case 5:
					if (Cliente.matrizIsFull(Cliente.BalcaoNobre)) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				default:
					errorMes("Área \"" + area + "\" inválida!\n");
					val = false;
			}


		} while (!val);

		char escolha = 'N';
		val = true;
		do {
			switch (area) {
				case 1:
					if (Cliente.matrizIsFull(Cliente.plateiaA)) {
						val = false;
					} else {
						System.out.println(colorize("#Plateia A:", title));
						mostraPoltrona(Cliente.plateiaA);
						inputPoltrona(Cliente.plateiaA);
					}
					break;
				case 2:
					if (Cliente.matrizIsFull(Cliente.plateiaB)) {
						val = false;
					} else {
						System.out.println(colorize("#Plateia B:", title));
						mostraPoltrona(Cliente.plateiaB);
						inputPoltrona(Cliente.plateiaB);
					}
					break;
				case 5:
					if (Cliente.matrizIsFull(Cliente.BalcaoNobre)) {
						val = false;
					} else {
						System.out.println(colorize("#Balcão Nobre:", title));
						mostraPoltrona(Cliente.BalcaoNobre);
						inputPoltrona(Cliente.BalcaoNobre);
					}
					break;
			}

			if (val) {
				System.out.print("Deseja comprar mais poltronas dessa área: [s/n] ");
				escolha = Character.toUpperCase(userInput.next().charAt(0));

				while (escolha != 'S' && escolha != 'N') {
					errorMes("Entrada inválida! Informe novamente: ");
					escolha = Character.toUpperCase(userInput.next().charAt(0));
				}
			} else {
				avisoMes("Área cheia!\n");
				escolha = 'N';
			}

		} while (escolha == 'S');
	}

	public static void errorMes(String text) {
		System.out.print(colorize("[ERRO] " + text, RED_TEXT()));
	}

	public static void avisoMes(String text) {
		System.out.print(colorize("[AVISO] " + text, YELLOW_TEXT()));
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
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Ocupado | " + colorize("    ", GREEN_BACK()) + " Livre");
	}

	public static void inputPoltrona(int[][] plateia) {
		boolean val ;
		String polInvalid = "Poltrona inválida!\n";
		do {
			System.out.print(colorize("[POLTRONA]", CYAN_TEXT()) + " Informe a Poltrona: ");
			String poltrona = userInput.next().toUpperCase();

			if (poltrona.length() < 2) {
				errorMes(polInvalid);
				val = false;
			} else {
				String num = poltrona.substring(0, poltrona.length() - 1);
				char letra = poltrona.charAt(poltrona.length() - 1);
				int indexLine = 0;
				int indexColumn;
				Scanner sc = new Scanner(num);
				int count = 0;

				for (int i = 0; i < alfabeto.length; i++) {
					if (letra == Character.toUpperCase(alfabeto[i])) {
						indexLine = i;
						count++;
					}
				}

				if (count == 1) {
					if (indexLine > (plateia.length - 1)) {
						errorMes(polInvalid);
						val = false;
					} else if (!sc.hasNextInt()) {
						errorMes(polInvalid);
						val = false;
						sc.next();
					} else {
						indexColumn = Integer.parseInt(num);
						indexColumn--;
						sc.next();
						if (indexColumn > plateia[0].length) {
							errorMes(polInvalid);
							val = false;
						} else {
							if (plateia[indexLine][indexColumn] == 0) {
								plateia[indexLine][indexColumn] = 1;
								val = true;
							} else {
								errorMes(polInvalid);
								val = false;
							}
						}
					}

				} else {
					errorMes(polInvalid);
					val = false;
				}
				sc.close();
			}
		} while (!val);
	}

	public static void mostraArea() {
		System.out.println(colorize("#Menu Áreas:", title));
		String text = "";
		if (Cliente.matrizIsFull(Cliente.plateiaA)) {
			text += colorize(" [1] Plateia A ", RED_BACK());
		} else {
			text += colorize(" [1] Plateia A ", BLACK_TEXT(), GREEN_BACK());
		}
		text += "\n";
		if (Cliente.matrizIsFull(Cliente.plateiaA)) {
			text += colorize(" [2] Plateia B ", RED_BACK());
		} else {
			text += colorize(" [2] Plateia B ", BLACK_TEXT(), GREEN_BACK());
		}
		text += "\n";
		if (Cliente.matrizIsFull(Cliente.plateiaA)) {
			text += colorize(" [5] Balcão Nobre ", RED_BACK());
		} else {
			text += colorize(" [5] Balcão Nobre ", BLACK_TEXT(), GREEN_BACK());
		}
		System.out.println(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Todo ocupado | " + colorize("    ", GREEN_BACK()) + " Lugares livres");

		//System.out.println(" [1] Plateia A    | " +
		//		"[2] Plateia B\n" +
		//		" [3] Frisas       | " +
		//		"[4] Camarotes\n" +
		//		" [5] Balcão Nobre | " +
		//		"[6] Cancelar");
	}

	public static void imprimiLogo() {
		System.out.println(" _____  _                         _          _   \n" +
				"|_   _|| |                       | |        | |  \n" +
				"  | |  | |__    ___   __ _   ___ | | __ ___ | |_ \n" +
				"  | |  | '_ \\  / _ \\ / _` | / __|| |/ // _ \\| __|\n" +
				"  | |  | | | ||  __/| (_| || (__ |   <|  __/| |_ \n" +
				"  \\_/  |_| |_| \\___| \\__,_| \\___||_|\\_\\\\___| \\__|");
	}

	public static void imprimiComandos() {
		System.out.println(colorize("#Comandos:\n", title) +
				"comin    comprar ingresso\n" +
				"custPol  ver custo das poltronas\n" +
				"ajuda    ver os comandos disponíveis\n" +
				"sair     sair do programa");
	}

	public static void imprimiAreas() {
		System.out.println(colorize("#Menu Áreas:", title) +
				"\n" +
				colorize(" [1]     Plateia A      ", GREEN_BACK()) + "| [2]     Plateia B\n" +
				" [3]     Frisas         | [4]     Camarotes\n" +
				" [5]     Balcão Nobre   | [6]     Cancelar");
	}
}
