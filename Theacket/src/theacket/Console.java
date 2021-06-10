package theacket;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.BLUE_TEXT;
import static consoleColor.Attribute.GREEN_TEXT;

import java.util.Scanner;

public class Console {

	public static void main(String[] args) {
		
		System.out.println(getLogo() + "\n" + getComandosModo());
        Scanner input = new Scanner(System.in);
        String comando;
        
        do {
        	System.out.print(colorize("modo", GREEN_TEXT()) + "@" + colorize("Theacket", BLUE_TEXT()) + "~$ ");
        	comando = input.nextLine();
        	
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
        		ConsoleMessage.erroMes("Comando \"" + comando + "\" inválido!");
        	}
        	
        } while (!comando.equals("sair"));
        
        
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
	
	public static String getComandosModo() {
		String text = "Comandos:\n" +
				"cli     entrar no modo cliente\n" +
				"adm     entrar no modo administrativo\n" +
				"sair    sair do programa\n";
	    return text;
	}

}
