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
	
	public static void imIngresso(String cpf, String peca, String area, String poltronas, String sessao, Double valor){
		
		
		System.out.println("|------------( INGRESSO )-----------");
		System.out.println("|-----------------------------------");
		System.out.println("|(CPF):                             ");
		System.out.println("|"+Cliente.imprimeCPF(cpf)+"        ");
		System.out.println("|-----------------------------------");
		System.out.println("|(PEÇA):                            ");
		System.out.println("|"+peca+"                           ");
		System.out.println("|-----------------------------------");
		System.out.println("|(SESSÃO):                          ");
		System.out.println("|"+sessao+"                         ");
		System.out.println("|-----------------------------------");
		System.out.println("|(Área):                            ");
		System.out.println("|"+area+"                           ");
		System.out.println("|-----------------------------------");
		System.out.println("|(POLTRONA(S)):                     ");
		System.out.println("|"+poltronas+"                      ");
		System.out.println("|-----------------------------------");
		System.out.println("|(VALOR PAGO):                      ");
		System.out.println("|"+valor+"                          ");
		System.out.println("|-----------------------------------");
		System.out.println("|                                   ");
		System.out.println("|-----------------------------------");
		System.out.println("|-----------------------------------");
	
	
	}
}
