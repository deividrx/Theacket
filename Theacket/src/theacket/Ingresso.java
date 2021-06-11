package theacket;

public class Ingresso {
	
	public static double getPreco(String local) {
		double preco;
		
		switch (local) {
		case "pa":
			preco = 40;
			break;
		case "pb":
			preco = 60;
			break;
		case "c":
			preco = 80;
			break;
		case "f":
			preco = 120;
			break;
		case "bn":
			preco = 250;
			break;
		default:
			preco = 0;
		}
		
		return preco;
	}
	
	
	
}
