package theacket;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;

import java.util.Scanner;

public class Console {

	public static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println(getLogo() + "\n" + getModos());
		String modo;
		System.out.println("" + Ingresso.getPreco("pa"));
		
        do {
        	System.out.print(colorize("[modo]", YELLOW_TEXT()) + " Informe em qual modo deseja entrar: ");
        	modo = userInput.next();
        	
        	if (!modo.equals("cli") && !modo.equals("adm") && !modo.equals("sair")) {
        		erroMes("Modo \"" + modo + "\" inv�lido!");
        	}
        	
        } while (!modo.equals("cli") && !modo.equals("adm") && !modo.equals("sair"));
        
        
        if (!modo.equals("sair")) {
        	String comando;
        	
        	do {
        		System.out.print(colorize("modo", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
        		comando = userInput.next();
        		switch (comando) {
        		case "sair":
        			System.out.println("Saindo do programa!");
        			break;
        		case "cli":
        			System.out.println("Entrando no modo cliente!");
        			break;
        		case "adm":
        			System.out.println("Entrando no modo adm!");
        			break;
        		default:
        			erroMes("Comando \"" + comando + "\" inv�lido!");
        		}
        		
        	} while (!comando.equals("sair"));
        }
	}
	
	public static String getLogo() {
		String logo = "" +
				" _____  _                         _          _   \n" +
				"|_   _|| |                       | |        | |  \n" +
				"  | |  | |__    ___   __ _   ___ | | __ ___ | |_ \n" +
				"  | |  | '_ \\  / _ \\ / _` | / __|| |/ // _ \\| __|\n" +
				"  | |  | | | ||  __/| (_| || (__ |   <|  __/| |_ \n" +
				"  \\_/  |_| |_| \\___| \\__,_| \\___||_|\\_\\\\___| \\__|\n";
	    return logo;
	}
	
	public static String getModos() {
		String text = "#Modos:\n" +
				"cli     entrar no modo cliente\n" +
				"adm     entrar no modo administrativo\n" +
				"sair    sair do programa\n";
		return text;
	}
	
	public static int validaEntradaInt(String mensagem) {
		int input;
		userInput.next();
		while (!userInput.hasNextInt()) {
			erroMes(mensagem);
		}
		input = userInput.nextInt();
		return input;
	}
	
	public static void erroMes(String texto) {
		System.out.println(colorize("[ERRO] " + texto, RED_TEXT()));
	}
	
	public static void mostraPoltrona(int[][] plateia) {
		int cadeira = 1;
		for (int i = 0; i < plateia.length; i++) {
			for (int j = 0; j < plateia[0].length; j++) {
				if (plateia[i][j] == 0) {
					System.out.print(colorize("[" + cadeira + Character.toUpperCase(geraLetra(i)) + "]", GREEN_BACK()));
				} else {
					System.out.print(colorize("[" + cadeira + Character.toUpperCase(geraLetra(i)) + "]", RED_BACK()));
				}
				cadeira++;
				System.out.print("\t");
			}
			System.out.println();
			cadeira = 1;
		}
	}
	
	public static char geraLetra(int num) {
		char[] alfabeto = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		return alfabeto[num];
	}

}