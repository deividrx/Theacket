package theacket;

import static consoleColor.Ansi.colorize;
import static consoleColor.Attribute.*;

public class ConsoleMessage {

	public static void erroMes(String texto) {
		System.out.println(colorize("[ERRO] " + texto, RED_TEXT()));
	}

}
