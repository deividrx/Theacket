package theacket;

import java.util.InputMismatchException;

public class Cliente {

	public static int epCam = 10;
	public static int epfrisa = 5;
	public static int[][] plateiaA = new int[5][5];
	public static int[][] plateiaB = new int[10][10];
	public static int[][] BalcaoNobre = new int[5][10];
	public static int[][] cam1 = new int[1][epCam];
	public static int[][] cam2 = new int[1][epCam];
	public static int[][] cam3 = new int[1][epCam];
	public static int[][] cam4 = new int[1][epCam];
	public static int[][] cam5 = new int[1][epCam];
	public static int[][] frisa1 = new int[1][epfrisa];
	public static int[][] frisa2 = new int[1][epfrisa];
	public static int[][] frisa3 = new int[1][epfrisa];
	public static int[][] frisa4 = new int[1][epfrisa];
	public static int[][] frisa5  = new int[1][epfrisa];
	public static int[][] frisa6 = new int[1][epfrisa];

	public static boolean matrizIsFull(int[][] matriz) {
		boolean val = true;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz[i][j] == 0) {
					val = false;
					break;
				}
			}
		}
		return val;
	}



	public static boolean validaCPF(String CPF) {
	    
		if (CPF.equals("00000000000") ||
			CPF.equals("11111111111") ||
			CPF.equals("22222222222") || CPF.equals("33333333333") ||
			CPF.equals("44444444444") || CPF.equals("55555555555") ||
			CPF.equals("66666666666") || CPF.equals("77777777777") ||
			CPF.equals("88888888888") || CPF.equals("99999999999") ||
			(CPF.length() != 11)) {
			return(false);
		}
		
        char dig10, dig11;
        int sm, i, r, num, peso;
        
        try {
            sm = 0;
            peso = 10;
            
            for (i = 0; i < 9; i++) {
            	num = CPF.charAt(i) - 48;
            	sm = sm + (num * peso);
            	peso = peso - 1;
            }
            
	        r = 11 - (sm % 11);
	        
            if ((r == 10) || (r == 11)) {
            	dig10 = '0';
            } else {
            	dig10 = (char)(r + 48); 
            }
            
	        sm = 0;
            peso = 11;
            
            for(i = 0; i < 10; i++) {
            	num = (CPF.charAt(i) - 48);
            	sm = sm + (num * peso);
            	peso = peso - 1;
	        }
            
            r = 11 - (sm % 11);
            
            if ((r == 10) || (r == 11)) {
            	dig11 = '0';
            } else {
            	dig11 = (char)(r + 48);
            }

			return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
            
	    } catch (InputMismatchException erro) {
	    	return(false);
	    }
    }
	public static String imprimeCPF(String CPF) {
	    return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
}

