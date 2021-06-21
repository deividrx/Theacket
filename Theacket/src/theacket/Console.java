package theacket;

import consoleColor.AnsiFormat;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;
import static theacket.Cliente.*;

import java.util.Arrays;
import java.util.Scanner;

public class Console {

	public static Scanner userInput = new Scanner(System.in);
	public static char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static AnsiFormat title = new AnsiFormat(BRIGHT_BLUE_TEXT(), BOLD());
	public static boolean inputPoltronaIsCanceled;
	public static int num;
	public static String poltronas = "";

	public static void main(String[] args) {
		Cliente.preencherMapa();
		Cliente.peencherMapaCli();
		imprimiLogo();
		imprimiComandos();
		String comando;
		do {
			System.out.print(colorize("Cliente", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
			comando = userInput.next().toLowerCase();

			switch (comando) {
				case "comprar":
					if (!Cliente.allMatrizHasFull()) {
						compraIngresso();
					} else {
						avisoMes("O teatro está com todos os lugares de todas as sessões de todas as peças ocupados!\n");
					}
					break;
				case "valor":
					custoPoltrona();
					break;
				case "sair":
					System.out.println(colorize("Saindo do programa....", YELLOW_TEXT(), ITALIC()));
					break;
				case "ajuda":
					imprimiComandos();
					break;
				case "ingresso":
					mostraIngresso();
					break;
				case "adm":
					mostraEsta();
					break;
				default:
					errorMes("Comando \"" + comando + "\" inválido!\n");
			}

		} while (!comando.equals("sair"));
		userInput.close();
		System.exit(0);
	}

	public static void mostraIngresso() {
		String numCPF;
		do {
			System.out.print(colorize("[CPF]", CYAN_TEXT()) + " Informe o seu CPF: ");
			numCPF = userInput.next();
			if (!Cliente.validaCPF(numCPF)){
				errorMes("CPF inválido!\n");
			}

		} while(!Cliente.validaCPF(numCPF));

		boolean val = false;
		for (int m = 1; m <= mapaCliente.size(); m++) {
			for (int i = 0; i < mapaCliente.get(m).length; i++) {
				if (mapaCliente.get(m)[i][0] != null) {
					if (mapaCliente.get(m)[i][0].equals(numCPF)) {
						Ingresso.imIngresso(mapaCliente.get(m)[i][0], mapaCliente.get(m)[i][1], mapaCliente.get(m)[i][2], mapaCliente.get(m)[i][3], mapaCliente.get(m)[i][4], Double.parseDouble(mapaCliente.get(m)[i][5]));
						val = true;
						break;
					}
				}
			}
		}
		if (!val) {
			avisoMes("Este CPF não possui ingressos comprados!\n");
		}
	}


	public static void custoPoltrona() {
		System.out.println("Poltrona Plateia A:    | R$40,00");
		System.out.println("Poltrona Plateia B:    | R$60,00");
		System.out.println("Poltrona Camarote:     | R$80,00");
		System.out.println("Poltrona Frisa:        | R$120,00");
		System.out.println("Poltrona Balcão Nobre: | R$250,00");
	}

	public static void compraIngresso() {
		int peca = 0;
		char pecaChar;
		mostraPeca();
		do {
			System.out.print(colorize("[PEÇA]", CYAN_TEXT()) + " Informe a Peça: ");
			pecaChar = userInput.next().charAt(0);
			if (pecaChar != '1' && pecaChar != '2'){
				errorMes("Peça inválida!\n");
			} else {
				peca = Character.getNumericValue(pecaChar);
			}

		} while(pecaChar != '1' && pecaChar != '2');

		int sess = 0;
		char sessChar;
		mostraSess(peca);
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

		mostraArea(sess, peca);
		int p = getMatrizesSess(sess, peca);
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
					if (Cliente.allFrisasHasFull(sess, peca)) {
						errorMes(areaInvalid);
						val = false;
					}
					break;
				case '4':
					if (Cliente.allCamHasFull(sess, peca)) {
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
					if (Cliente.allFrisasHasFull(sess, peca)) {
						val = false;
					} else {
						custoCliente += compraFrisa(sess, peca);
					}
					break;
				case '4':
					if (Cliente.allCamHasFull(sess, peca)) {
						val = false;
					} else {
						custoCliente += compraCam(sess, peca);
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
					if (area != '3' && area != '4') {
						System.out.print("Deseja comprar mais poltronas dessa área: [s/n] ");
						escolha = Character.toUpperCase(userInput.next().charAt(0));

						while (escolha != 'S' && escolha != 'N') {
							errorMes("Entrada inválida! Informe novamente: ");
							escolha = Character.toUpperCase(userInput.next().charAt(0));
						}
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

			switch (peca) {
				case 1:
					Adm.ingressosPeca[0]++;
					break;
				case 2:
					Adm.ingressosPeca[1]++;
					break;
			}

			System.out.print(colorize("[INGRESSO] ", CYAN_TEXT()) + "Deseja imprimir o ingresso agora? [s/n]");
			escolha = Character.toUpperCase(userInput.next().charAt(0));

			while (escolha != 'S' && escolha != 'N') {
				errorMes("Entrada inválida! Informe novamente: ");
				escolha = Character.toUpperCase(userInput.next().charAt(0));
			}

			if (escolha == 'S') {
				Ingresso.imIngresso(numCPF, Cliente.getPeca(peca), getArea(Character.getNumericValue(area), num), poltronas, getSess(sess), custoCliente);
				poltronas = "";
			}
			int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;

			switch (peca) {
				case 1:
					switch (sess) {
						case 1:
							mapaCliente.get(1)[a][0] = numCPF;
							mapaCliente.get(1)[a][1] = Cliente.getPeca(peca);
							mapaCliente.get(1)[a][2] = getArea(Character.getNumericValue(area), num);
							mapaCliente.get(1)[a][3] = poltronas;
							mapaCliente.get(1)[a][4] = getSess(sess);
							mapaCliente.get(1)[a][5] = Double.toString(custoCliente);
							a++;
							break;
						case 2:
							mapaCliente.get(2)[b][0] = numCPF;
							mapaCliente.get(2)[b][1] = Cliente.getPeca(peca);
							mapaCliente.get(2)[b][2] = getArea(Character.getNumericValue(area), num);
							mapaCliente.get(2)[b][3] = poltronas;
							mapaCliente.get(2)[b][4] = getSess(sess);
							mapaCliente.get(2)[b][5] = Double.toString(custoCliente);
							b++;
							break;
						case 3:
							mapaCliente.get(3)[c][0] = numCPF;
							mapaCliente.get(3)[c][1] = Cliente.getPeca(peca);
							mapaCliente.get(3)[c][2] = getArea(Character.getNumericValue(area), num);
							mapaCliente.get(3)[c][3] = poltronas;
							mapaCliente.get(3)[c][4] = getSess(sess);
							mapaCliente.get(3)[c][5] = Double.toString(custoCliente);
							c++;
							break;
					}
					break;
				case 2:
					switch (sess) {
						case 1:
							mapaCliente.get(4)[a][0] = numCPF;
							mapaCliente.get(4)[a][1] = Cliente.getPeca(peca);
							mapaCliente.get(4)[a][2] = getArea(Character.getNumericValue(area), num);
							mapaCliente.get(4)[a][3] = poltronas;
							mapaCliente.get(4)[a][4] = getSess(sess);
							mapaCliente.get(4)[a][5] = Double.toString(custoCliente);
							d++;
							break;
						case 2:
							mapaCliente.get(5)[e][0] = numCPF;
							mapaCliente.get(5)[e][1] = Cliente.getPeca(peca);
							mapaCliente.get(5)[e][2] = getArea(Character.getNumericValue(area), num);
							mapaCliente.get(5)[e][3] = poltronas;
							mapaCliente.get(5)[e][4] = getSess(sess);
							mapaCliente.get(5)[e][5] = Double.toString(custoCliente);
							e++;
							break;
						case 3:
							mapaCliente.get(6)[f][0] = numCPF;
							mapaCliente.get(6)[f][1] = Cliente.getPeca(peca);
							mapaCliente.get(6)[f][2] = getArea(Character.getNumericValue(area), num);
							mapaCliente.get(6)[f][3] = poltronas;
							mapaCliente.get(6)[f][4] = getSess(sess);
							mapaCliente.get(6)[f][5] = Double.toString(custoCliente);
							f++;
							break;
					}
					break;
			}

		}
		inputPoltronaIsCanceled = false;
	}

	public static double compraFrisa(int sess, int peca) {
		mostraFrisas(sess, peca);
		int p = getMatrizesSess(sess, peca);
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
						num = 1;
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
						num = 2;
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
						num = 3;
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
						num = 4;
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
						num = 5;
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
						num = 6;
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

	public static double compraCam(int sess, int peca) {
		mostraCam(sess, peca);
		char area;
		int p = getMatrizesSess(sess, peca);
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
						num = 1;
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
						num = 2;
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
						num = 3;
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
						num = 4;
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
						num = 5;
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
									poltronas += poltrona + " ";
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

	public static void mostraArea(int sess, int peca) {
		int p = getMatrizesSess(sess, peca);
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
		if (Cliente.allFrisasHasFull(sess, peca)) {
			text += colorize(" [3] Frisas ", RED_BACK());
		} else {
			text += colorize(" [3] Frisas ", BLACK_TEXT(), GREEN_BACK());
		}
		text += "\n";
		if (Cliente.allCamHasFull(sess, peca)) {
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

	public static void mostraFrisas(int sess, int peca) {
		System.out.println(colorize("#Menu Frisas:", title));
		int p = getMatrizesSess(sess, peca);
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

	public static void mostraCam(int sess, int peca) {
		System.out.println(colorize("#Menu Camarotes:", title));
		int p = getMatrizesSess(sess, peca);
		StringBuilder text = new StringBuilder();
		int count = 1;
		for (int i = (10 + p); i <= (14 + p); i++) {
			if (Cliente.matrizHasFull(Cliente.mapa.get(i))) {
				text.append(colorize(" [" + count + "] Camarote " + count + " ", RED_BACK()));
			} else {
				text.append(colorize(" [" + count + "] Camarote " + count + " ", BLACK_TEXT(), GREEN_BACK()));
			}
			text.append("\n");
			count++;
		}
		System.out.println(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Todo ocupado | " + colorize("    ", GREEN_BACK()) + " Lugares livres");
	}

	public static void imprimiLogo() {
		System.out.println("$$$$$$$$\\ $$\\                                     $$\\                  $$\\     \n" +
				"\\__$$  __|$$ |                                    $$ |                 $$ |    \n" +
				"   $$ |   $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$$\\ $$ |  $$\\  $$$$$$\\ $$$$$$\\   \n" +
				"   $$ |   $$  __$$\\ $$  __$$\\  \\____$$\\ $$  _____|$$ | $$  |$$  __$$\\\\_$$  _|  \n" +
				"   $$ |   $$ |  $$ |$$$$$$$$ | $$$$$$$ |$$ /      $$$$$$  / $$$$$$$$ | $$ |    \n" +
				"   $$ |   $$ |  $$ |$$   ____|$$  __$$ |$$ |      $$  _$$<  $$   ____| $$ |$$\\ \n" +
				"   $$ |   $$ |  $$ |\\$$$$$$$\\ \\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ \\$$$$$$$\\  \\$$$$  |\n" +
				"   \\__|   \\__|  \\__| \\_______| \\_______| \\_______|\\__|  \\__| \\_______|  \\____/ ");
	}

	public static void imprimiComandos() {
		System.out.println(colorize("#Comandos:\n", title) +
				"comprar   comprar ingresso\n" +
				"valor     ver custo das poltronas\n" +
				"ajuda     ver os comandos disponíveis\n" +
				"adm       entrar no modo administrativo\n" +
				"Ingresso  imprime ingresso\n"+
				"sair      sair do programa");
	}

	public static void errorMes(String text) {
		System.out.print(colorize("[ERRO] " + text, RED_TEXT()));
	}

	public static void avisoMes(String text) {
		System.out.print(colorize("[AVISO] " + text, YELLOW_TEXT()));
	}

	public static void mostraSess(int peca) {
		System.out.println(colorize("#Peça " + peca + ":", title));
		System.out.println(colorize("#Menu sessão:", title));
		StringBuilder text = new StringBuilder();
		if (Cliente.sessHasFull(1, peca)) {
			text.append(colorize(" [1] Manhã ", RED_BACK()));
		} else {
			text.append(colorize(" [1] Manhã ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		if (Cliente.sessHasFull(2, peca)) {
			text.append(colorize(" [2] Tarde ", RED_BACK()));
		} else {
			text.append(colorize(" [2] Tarde ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		if (Cliente.sessHasFull(3, peca)) {
			text.append(colorize(" [3] Noite ", RED_BACK()));
		} else {
			text.append(colorize(" [3] Noite ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		System.out.print(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Toda ocupada | " + colorize("    ", GREEN_BACK()) + " Lugares livres");
	}

	public static void mostraPeca() {
		System.out.println(colorize("#Menu Peça: ", title));
		StringBuilder text = new StringBuilder();
		if (Cliente.pecaHasFull(1)) {
			text.append(colorize(" [1] " + Cliente.pecasInf[0][0] + " ", RED_BACK()));
		} else {
			text.append(colorize(" [1] " + Cliente.pecasInf[0][0] + " ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		if (Cliente.pecaHasFull(2)) {
			text.append(colorize(" [2] " + Cliente.pecasInf[1][0] + " ", RED_BACK()));
		} else {
			text.append(colorize(" [2] " + Cliente.pecasInf[1][0] + " ", BLACK_TEXT(), GREEN_BACK()));
		}
		text.append("\n");
		System.out.print(text);
		System.out.println("Legenda: ");
		System.out.println(colorize("    ", RED_BACK()) + " Toda ocupada | " + colorize("    ", GREEN_BACK()) + " Lugares livres");
	}

	public static void mostraEsta() {
		System.out.println(colorize("#Estatísticas:", title));

		//Qual a peça teve mais ingressos vendidos e menos vendidos?

		if (Adm.ingressosPeca[0] > Adm.ingressosPeca[1]) {
			System.out.println("A peça \"" + Arrays.toString(pecasInf[0]) + "\" teve mais ingressos vendidos, foram " + Adm.ingressosPeca[0] + " ingressos vendidos!");
			System.out.println("A peça \"" + Arrays.toString(pecasInf[1]) + "\" teve menos ingressos vendidos, foram " + Adm.ingressosPeca[1] + " ingressos vendidos!");
		} else if (Adm.ingressosPeca[1] > Adm.ingressosPeca[0]) {
			System.out.println("A peça \"" + Arrays.toString(pecasInf[1]) + "\" teve mais ingressos vendidos, foram " + Adm.ingressosPeca[1] + " ingressos vendidos!");
			System.out.println("A peça \"" + Arrays.toString(pecasInf[0]) + "\" teve menos ingressos vendidos, foram " + Adm.ingressosPeca[0] + " ingressos vendidos!");
		} else {
			if ((Adm.ingressosPeca[1] - Adm.ingressosPeca[0]) == 0) {
				avisoMes("Nenhum ingressso foi comprado!");
			} else {
				System.out.println("Ambas as peças \"" + Arrays.toString(pecasInf[1]) + "\" e \"" + Arrays.toString(pecasInf[0]) + "\" tiveram a mesma quantidade de ingressos vendidos!");
			}
		}

		//Qual sessão teve maior e menor ocupação de poltronas?

		for (int m = 1; m < mapa.size(); m++) {
			for (int i = 0; i < mapa.get(m).length; i++) {
				for (int j = 0; j < mapa.get(m)[0].length; j++) {
					if (m <= 14) {
						if (mapa.get(m)[i][j] == 1) {
							Adm.poltronasSess[0]++; // Manhã da peça 1
						}
					} else if (m <= 28) {
						if (mapa.get(m)[i][j] == 1) {
							Adm.poltronasSess[1]++; // Tarde da peça 1
						}
					} else if (m <= 42) {
						if (mapa.get(m)[i][j] == 1) {
							Adm.poltronasSess[2]++; // Noite da peça 1
						}
					} else if (m <= 56) {
						if (mapa.get(m)[i][j] == 1) {
							Adm.poltronasSess[3]++; // Manhã da peça 2
						}
					} else if (m <= 70) {
						if (mapa.get(m)[i][j] == 1) {
							Adm.poltronasSess[4]++; // Tarde da peça 2
						}
					} else {
						if (mapa.get(m)[i][j] == 1) {
							Adm.poltronasSess[5]++; // Noite da peça 2
						}
					}
				}
			}
		}
		String sessMaior = "";
		String sessMenor = "";
		if (Adm.poltronasSess[0] > Adm.poltronasSess[1] && Adm.poltronasSess[0] > Adm.poltronasSess[2] && Adm.poltronasSess[0] > Adm.poltronasSess[3]
				&& Adm.poltronasSess[0] > Adm.poltronasSess[4] && Adm.poltronasSess[0] > Adm.poltronasSess[5]) {
			sessMaior = "Manhã da peça " + Arrays.toString(pecasInf[0]);
		} else if (Adm.poltronasSess[1] > Adm.poltronasSess[0] && Adm.poltronasSess[1] > Adm.poltronasSess[2] && Adm.poltronasSess[1] > Adm.poltronasSess[3]
				&& Adm.poltronasSess[1] > Adm.poltronasSess[4] && Adm.poltronasSess[1] > Adm.poltronasSess[5]) {
			sessMaior = "Tarde da peça " + Arrays.toString(pecasInf[0]);
		} else if (Adm.poltronasSess[2] > Adm.poltronasSess[0] && Adm.poltronasSess[2] > Adm.poltronasSess[1] && Adm.poltronasSess[2] > Adm.poltronasSess[3]
				&& Adm.poltronasSess[2] > Adm.poltronasSess[4] && Adm.poltronasSess[2] > Adm.poltronasSess[5]) {
			sessMaior = "Noite da peça " + Arrays.toString(pecasInf[0]);
		} else if (Adm.poltronasSess[3] > Adm.poltronasSess[1] && Adm.poltronasSess[3] > Adm.poltronasSess[2] && Adm.poltronasSess[3] > Adm.poltronasSess[0]
				&& Adm.poltronasSess[3] > Adm.poltronasSess[4] && Adm.poltronasSess[3] > Adm.poltronasSess[5]) {
			sessMaior = "Manhã da peça " + Arrays.toString(pecasInf[1]);
		} else if (Adm.poltronasSess[4] > Adm.poltronasSess[0] && Adm.poltronasSess[4] > Adm.poltronasSess[2] && Adm.poltronasSess[4] > Adm.poltronasSess[3]
				&& Adm.poltronasSess[4] > Adm.poltronasSess[1] && Adm.poltronasSess[4] > Adm.poltronasSess[5]) {
			sessMaior = "Tarde da peça " + Arrays.toString(pecasInf[1]);
		} else if (Adm.poltronasSess[5] > Adm.poltronasSess[0] && Adm.poltronasSess[5] > Adm.poltronasSess[1] && Adm.poltronasSess[5] > Adm.poltronasSess[3]
				&& Adm.poltronasSess[5] > Adm.poltronasSess[4] && Adm.poltronasSess[5] > Adm.poltronasSess[2]) {
			sessMaior = "Noite da peça " + Arrays.toString(pecasInf[1]);
		}
		System.out.println("Sessão com maior ocupação: " + sessMaior);

		//Lucro médio do teatro com todas as áreas por peça.


	}
}
