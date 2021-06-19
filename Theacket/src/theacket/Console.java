package theacket;

import consoleColor.AnsiFormat;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;
import static theacket.Cliente.getMatrizesSess;

import java.util.Scanner;

public class Console {

	public static Scanner userInput = new Scanner(System.in);
	public static char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static AnsiFormat title = new AnsiFormat(BRIGHT_BLUE_TEXT(), BOLD());
	public static boolean inputPoltronaIsCanceled;

	public static void main(String[] args) {
		Cliente.preecherMapa();
		imprimiLogo();
		imprimiComandos();
		String comando;
		do {
			System.out.print(colorize("Cliente", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
			comando = userInput.next();

			switch (comando) {
				case "comin":
					if (!Cliente.allMatrizHasFull()) {
						compraIngresso();
					} else {
						avisoMes("O teatro está com todos os lugares de todas as sessões de todas as peças ocupados!\n");
					}
					break;
				case "custPol":
					custoPoltrona();
					break;
				case "sair":
					System.out.println(colorize("Saindo do programa....", YELLOW_TEXT(), ITALIC()));
					break;
				case "ajuda":
					imprimiComandos();
					break;
				default:
					errorMes("Comando \"" + comando + "\" inválido!\n");
			}

		} while (!comando.equals("sair"));
		userInput.close();
		System.exit(0);
	}

	public static void custoPoltrona() {
		System.out.println("Poltrona Plateia A:    | R$40,00");
		System.out.println("Poltrona Plateia B:    | R$60,00");
		System.out.println("Poltrona Camarote:     | R$80,00");
		System.out.println("Poltrona Frisa:        | R$120,00");
		System.out.println("Poltrona Balcão Nobre: | R$250,00");
	}

	public static void compraIngresso() {
		int sess = 0;
		char sessChar;
		mostraSess();
		do {
			System.out.print(colorize("[SESS]", CYAN_TEXT()) + " Informe a Sessão: ");
			sessChar = userInput.next().charAt(0);
			if (sessChar != '1' && sessChar != '2' && sessChar != '3'){
				errorMes("Sessão inválida!\n");
			} else {
				sess = Character.getNumericValue(sessChar);
			}
		} while(sessChar != '1' && sessChar != '2' && sessChar != '3');

		String numCPF;
		do {
			System.out.print(colorize("[CPF]", CYAN_TEXT()) + " Informe o seu CPF: ");
			numCPF = userInput.next();

			if (!Cliente.validaCPF(numCPF)){
				errorMes("CPF inválido!\n");
			}

		} while(!Cliente.validaCPF(numCPF));

		mostraArea(sess);
		int p = getMatrizesSess(sess);
		char area;
		boolean val = true;
		do {
			String areaInvalid = "Área cheia!\n";
			System.out.print(colorize("[ÁREA]", CYAN_TEXT()) + " Informe a área desejada: ");
			area = userInput.next().charAt(0);

			switch (area) {
				case '1':
					if (Cliente.matrizHasFull(Cliente.mapa.get(1 + p))) {
					 errorMes(areaInvalid);
					 val = false;
					}
					break;
				case '2':
					if (Cliente.matrizHasFull(Cliente.mapa.get(2 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '3':
					if (Cliente.allFrisasHasFull(sess)) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '4':
					if (Cliente.allCamHasFull(sess)) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '5':
					if (Cliente.matrizHasFull(Cliente.mapa.get(3 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				default:
					errorMes("Área \"" + area + "\" inválida!\n");
					val = false;
			}

		} while (!val);

		double custoCliente = 0;
		char escolha = 'N';
		val = true;
		do {
			switch (area) {
				case '1':
					if (Cliente.matrizHasFull(Cliente.mapa.get(1 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Plateia A:", title));
						mostraPoltrona(Cliente.mapa.get(1 + p));
						inputPoltrona(Cliente.mapa.get(1 + p));
						custoCliente += Ingresso.getPreco("pa");
					}
					break;
				case '2':
					if (Cliente.matrizHasFull(Cliente.mapa.get(2 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Plateia B:", title));
						mostraPoltrona(Cliente.mapa.get(2 + p));
						inputPoltrona(Cliente.mapa.get(2 + p));
						custoCliente += Ingresso.getPreco("pb");
					}
					break;
				case '3':
					if (Cliente.allFrisasHasFull(sess)) {
						val = false;
					} else {
						custoCliente += compraFrisa(sess);
					}
					break;
				case '4':
					if (Cliente.allCamHasFull(sess)) {
						val = false;
					} else {
						custoCliente += compraCam(sess);
					}
					break;
				case '5':
					if (Cliente.matrizHasFull(Cliente.mapa.get(3 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Balcão Nobre:", title));
						mostraPoltrona(Cliente.mapa.get(3 + p));
						inputPoltrona(Cliente.mapa.get(3 + p));
						custoCliente += Ingresso.getPreco("bn");
					}
					break;
			}

			if (!inputPoltronaIsCanceled) {
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
			}

		} while (escolha == 'S');

		if (!inputPoltronaIsCanceled) {
			System.out.println(colorize("#Formas de pagamento:", title));
			System.out.println("[0] Cartão | [1] Boleto | [2] Bitcoin ");
			System.out.println("Total a pagar: R$ " + custoCliente);
			System.out.print(colorize("[PAY]", GREEN_TEXT()) + " Informe a forma de pagamento: ");
			char pay = userInput.next().charAt(0);

			while (pay != '0' && pay != '1' && pay != '2') {
				errorMes("Forma de pagamento inválido! Informe novamente: ");
				pay = userInput.next().charAt(0);
			}

			System.out.println(colorize("[PAY] Pagamento concluído!", GREEN_TEXT()));
		}
		inputPoltronaIsCanceled = false;
	}

	public static double compraFrisa(int sess) {
		mostraFrisas(sess);
		int p = getMatrizesSess(sess);
		char area;
		boolean val = true;
		String areaInvalid = "Frisa cheia!\n";
		do {
			System.out.print(colorize("[FRISA]", CYAN_TEXT()) + " Informe a frisa desejada: ");
			area = userInput.next().charAt(0);
			switch (area) {
				case '1':
					if (Cliente.matrizHasFull(Cliente.mapa.get(4 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '2':
					if (Cliente.matrizHasFull(Cliente.mapa.get(5 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '3':
					if (Cliente.matrizHasFull(Cliente.mapa.get(6 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '4':
					if (Cliente.matrizHasFull(Cliente.mapa.get(7 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '5':
					if (Cliente.matrizHasFull(Cliente.mapa.get(8 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '6':
					if (Cliente.matrizHasFull(Cliente.mapa.get(9 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				default:
					errorMes("Área \"" + area + "\" inválida!\n");
					val = false;
			}

		} while (!val);

		double custoCliente = 0;
		char escolha = 'N';
		val = true;
		do {
			switch (area) {
				case '1':
					if (Cliente.matrizHasFull(Cliente.mapa.get(4 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Frisa 1:", title));
						mostraPoltrona(Cliente.mapa.get(4 + p));
						inputPoltrona(Cliente.mapa.get(4 + p));
						custoCliente += Ingresso.getPreco("f");
					}
					break;
				case '2':
					if (Cliente.matrizHasFull(Cliente.mapa.get(5 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Frisa 2:", title));
						mostraPoltrona(Cliente.mapa.get(5 + p));
						inputPoltrona(Cliente.mapa.get(5 + p));
						custoCliente += Ingresso.getPreco("f");
					}
					break;
				case '3':
					if (Cliente.matrizHasFull(Cliente.mapa.get(6 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Frisa 3:", title));
						mostraPoltrona(Cliente.mapa.get(6 + p));
						inputPoltrona(Cliente.mapa.get(6 + p));
						custoCliente += Ingresso.getPreco("f");
					}
					break;
				case '4':
					if (Cliente.matrizHasFull(Cliente.mapa.get(7 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Frisa 4:", title));
						mostraPoltrona(Cliente.mapa.get(7 + p));
						inputPoltrona(Cliente.mapa.get(7 + p));
						custoCliente += Ingresso.getPreco("f");
					}
					break;
				case '5':
					if (Cliente.matrizHasFull(Cliente.mapa.get(8 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Frisa 5:", title));
						mostraPoltrona(Cliente.mapa.get(8 + p));
						inputPoltrona(Cliente.mapa.get(8 + p));
						custoCliente += Ingresso.getPreco("f");
					}
					break;
				case '6':
					if (Cliente.matrizHasFull(Cliente.mapa.get(9 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Frisa 6:", title));
						mostraPoltrona(Cliente.mapa.get(9 + p));
						inputPoltrona(Cliente.mapa.get(9 + p));
						custoCliente += Ingresso.getPreco("f");
					}
					break;
			}

			if (!inputPoltronaIsCanceled) {
				if (val) {
					System.out.print("Deseja comprar mais poltronas dessa frisa: [s/n] ");
					escolha = Character.toUpperCase(userInput.next().charAt(0));

					while (escolha != 'S' && escolha != 'N') {
						errorMes("Entrada inválida! Informe novamente: ");
						escolha = Character.toUpperCase(userInput.next().charAt(0));
					}
				} else {
					avisoMes(areaInvalid);
					escolha = 'N';
				}
			}

		} while (escolha == 'S');
		return custoCliente;
	}

	public static double compraCam(int sess) {
		mostraCam(sess);
		char area;
		int p = getMatrizesSess(sess);
		boolean val = true;
		String areaInvalid = "Camarote cheio!\n";
		do {
			System.out.print(colorize("[FRISA]", CYAN_TEXT()) + " Informe o camarote desejado: ");
			area = userInput.next().charAt(0);
			switch (area) {
				case '1':
					if (Cliente.matrizHasFull(Cliente.mapa.get(10 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '2':
					if (Cliente.matrizHasFull(Cliente.mapa.get(11 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '3':
					if (Cliente.matrizHasFull(Cliente.mapa.get(12 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '4':
					if (Cliente.matrizHasFull(Cliente.mapa.get(13 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '5':
					if (Cliente.matrizHasFull(Cliente.mapa.get(14 + p))) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				default:
					errorMes("Área \"" + area + "\" inválida!\n");
					val = false;
			}

		} while (!val);

		double custoCliente = 0;
		char escolha = 'N';
		val = true;
		do {
			switch (area) {
				case '1':
					if (Cliente.matrizHasFull(Cliente.mapa.get(10 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Camarote 1:", title));
						mostraPoltrona(Cliente.mapa.get(10 + p));
						inputPoltrona(Cliente.mapa.get(10 + p));
						custoCliente += Ingresso.getPreco("c");
					}
					break;
				case '2':
					if (Cliente.matrizHasFull(Cliente.mapa.get(11 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Camarote 2:", title));
						mostraPoltrona(Cliente.mapa.get(11 + p));
						inputPoltrona(Cliente.mapa.get(11 + p));
						custoCliente += Ingresso.getPreco("c");
					}
					break;
				case '3':
					if (Cliente.matrizHasFull(Cliente.mapa.get(12 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Camarote 3:", title));
						mostraPoltrona(Cliente.mapa.get(12 + p));
						inputPoltrona(Cliente.mapa.get(12 + p));
						custoCliente += Ingresso.getPreco("c");
					}
					break;
				case '4':
					if (Cliente.matrizHasFull(Cliente.mapa.get(13 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Camarote 4:", title));
						mostraPoltrona(Cliente.mapa.get(13 + p));
						inputPoltrona(Cliente.mapa.get(13 + p));
						custoCliente += Ingresso.getPreco("c");
					}
					break;
				case '5':
					if (Cliente.matrizHasFull(Cliente.mapa.get(14 + p))) {
						val = false;
					} else {
						System.out.println(colorize("#Camarote 5:", title));
						mostraPoltrona(Cliente.mapa.get(14 + p));
						inputPoltrona(Cliente.mapa.get(14 + p));
						custoCliente += Ingresso.getPreco("c");
					}
					break;
			}
			if (!inputPoltronaIsCanceled) {
				if (val) {
					System.out.print("Deseja comprar mais poltronas desse camarote: [s/n] ");
					escolha = Character.toUpperCase(userInput.next().charAt(0));

					while (escolha != 'S' && escolha != 'N') {
						errorMes("Entrada inválida! Informe novamente: ");
						escolha = Character.toUpperCase(userInput.next().charAt(0));
					}
				} else {
					avisoMes(areaInvalid);
					escolha = 'N';
				}
			}

		} while (escolha == 'S');
		return custoCliente;
	}

	public static void mostraPoltrona(Integer[][] plateia) {
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
		System.out.println("cancel     para cancelar a compra do ingresso");
	}

	public static void inputPoltrona(Integer[][] plateia) {
		boolean val;
		String polInvalid = "Poltrona inválida!\n";
		do {
			System.out.print(colorize("[POLTRONA]", CYAN_TEXT()) + " Informe a Poltrona: ");
			String poltrona = userInput.next().toUpperCase();

			if (poltrona.equalsIgnoreCase("cancel")) {
				avisoMes("Cancelando a operação...\n");
				inputPoltronaIsCanceled = true;
				val = true;
			} else {
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
			}
		} while (!val);
	}

	public static void mostraArea(int sess) {
		int p = getMatrizesSess(sess);
		System.out.println(colorize("#Menu Áreas:", title));
		String text = "";
		if (Cliente.matrizHasFull(Cliente.mapa.get(1 + p))) {
			text += colorize(" [1] Plateia A ", RED_BACK());
		} else {
			text += colorize(" [1] Plateia A ", BLACK_TEXT(), GREEN_BACK());
		}
		text += "\n";
		if (Cliente.matrizHasFull(Cliente.mapa.get(2 + p))) {
			text += colorize(" [2] Plateia B ", RED_BACK());
		} else {
			text += colorize(" [2] Plateia B ", BLACK_TEXT(), GREEN_BACK());
		}
		text += "\n";
		if (Cliente.allFrisasHasFull(sess)) {
			text += colorize(" [3] Frisas ", RED_BACK());
		} else {
			text += colorize(" [3] Frisas ", BLACK_TEXT(), GREEN_BACK());
		}
		text += "\n";
		if (Cliente.allCamHasFull(sess)) {
			text += colorize(" [4] Camarotes ", RED_BACK());
		} else {
			text += colorize(" [4] Camarotes ", BLACK_TEXT(), GREEN_BACK());
		}
		text += "\n";
		if (Cliente.matrizHasFull(Cliente.mapa.get(3 + p))) {
			text += colorize(" [5] Balcão Nobre ", RED_BACK());
		} else {
			text += colorize(" [5] Balcão Nobre ", BLACK_TEXT(), GREEN_BACK());
		}
		System.out.println(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Todo ocupado | " + colorize("    ", GREEN_BACK()) + " Lugares livres");
	}

	public static void mostraFrisas(int sess) {
		System.out.println(colorize("#Menu Frisas:", title));
		int p = getMatrizesSess(sess);
		StringBuilder text = new StringBuilder();
		int count = 1;
		for (int i = (4 + p); i <= (9 + p); i++) {
			if (Cliente.matrizHasFull(Cliente.mapa.get(i))) {
				text.append(colorize(" [" + count + "] Frisa " + count + " ", RED_BACK()));
			} else {
				text.append(colorize(" [" + count + "] Frisa " + count + " ", BLACK_TEXT(), GREEN_BACK()));
			}
			text.append("\n");
			count++;
		}
		System.out.print(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Todo ocupado | " + colorize("    ", GREEN_BACK()) + " Lugares livres");
	}

	public static void mostraCam(int sess) {
		System.out.println(colorize("#Menu Camarotes:", title));
		int p = getMatrizesSess(sess);
		StringBuilder text = new StringBuilder();
		int count = 1;
		for (int i = (10 + p); i <= (14 + p); i++) {
			if (Cliente.matrizHasFull(Cliente.mapa.get(i))) {
				text.append(colorize(" [" + count + "] Camarote " + count + " ", RED_BACK()));
			} else {
				text.append(colorize(" [" + count + "] Frisa " + count + " ", BLACK_TEXT(), GREEN_BACK()));
			}
			text.append("\n");
			count++;
		}
		System.out.println(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Todo ocupado | " + colorize("    ", GREEN_BACK()) + " Lugares livres");
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
				"adm      entrar no modo administrativo\n" +
				"sair     sair do programa");
	}

	public static void errorMes(String text) {
		System.out.print(colorize("[ERRO] " + text, RED_TEXT()));
	}

	public static void avisoMes(String text) {
		System.out.print(colorize("[AVISO] " + text, YELLOW_TEXT()));
	}

	public static void mostraSess() {
		int peca = 1;
		System.out.println(colorize("#Peça " + peca + ":", title));
		System.out.println(colorize("#Menu sessão:", title));
		StringBuilder text = new StringBuilder();
		if (Cliente.sessHasFull(1)) {
			text.append(colorize(" [1] Manhã ", RED_BACK()));
		} else {
			text.append(colorize(" [1] Manhã ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		if (Cliente.sessHasFull(2)) {
			text.append(colorize(" [2] Tarde ", RED_BACK()));
		} else {
			text.append(colorize(" [2] Tarde ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		if (Cliente.sessHasFull(3)) {
			text.append(colorize(" [3] Noite ", RED_BACK()));
		} else {
			text.append(colorize(" [3] Noite ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		System.out.print(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Toda ocupada | " + colorize("    ", GREEN_BACK()) + " Lugares livres");
	}
}
