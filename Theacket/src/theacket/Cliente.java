package theacket;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Cliente {

	public static Map<Integer, Integer[][]> mapa = new HashMap<>();
	public static String[][] pecasInf = {{"Romeu e Julieta (William Shakespeare)"}, {"Hairspray (John Waters)"}};
	public static Map<Integer, String[][]> mapaCliente = new HashMap<>();

	public static void preencherMapa() {
		int epCam = 10, epFrisa = 5, c = 1;
		//Peça 1
		//Sessão manhã:
		mapa.put(1, new Integer[5][5]); //Plateia A
		mapa.put(2, new Integer[10][10]); //Plateia B
		mapa.put(3, new Integer[5][10]); //Balcão Nobre
		mapa.put(4, new Integer[c][epFrisa]); //Frisa 1
		mapa.put(5, new Integer[c][epFrisa]); //Frisa 2
		mapa.put(6, new Integer[c][epFrisa]); //Frisa 3
		mapa.put(7, new Integer[c][epFrisa]); //Frisa 4
		mapa.put(8, new Integer[c][epFrisa]); //Frisa 5
		mapa.put(9, new Integer[c][epFrisa]); //Frisa 6
		mapa.put(10, new Integer[c][epCam]); //camarote 1
		mapa.put(11, new Integer[c][epCam]); //camarote 2
		mapa.put(12, new Integer[c][epCam]); //camarote 3
		mapa.put(13, new Integer[c][epCam]); //camarote 4
		mapa.put(14, new Integer[c][epCam]); //camarote 5

		//Sessão tarde:
		mapa.put(15, new Integer[5][5]); //Plateia A
		mapa.put(16, new Integer[10][10]); //Plateia B
		mapa.put(17, new Integer[5][10]); //Balcão Nobre
		mapa.put(18, new Integer[c][epCam]); //Frisa 1
		mapa.put(19, new Integer[c][epFrisa]); //Frisa 2
		mapa.put(20, new Integer[c][epFrisa]); //Frisa 3
		mapa.put(21, new Integer[c][epFrisa]); //Frisa 4
		mapa.put(22, new Integer[c][epFrisa]); //Frisa 5
		mapa.put(23, new Integer[c][epFrisa]); //Frisa 6
		mapa.put(24, new Integer[c][epCam]); //camarote 1
		mapa.put(25, new Integer[c][epCam]); //camarote 2
		mapa.put(26, new Integer[c][epCam]); //camarote 3
		mapa.put(27, new Integer[c][epCam]); //camarote 4
		mapa.put(28, new Integer[c][epCam]); //camarote 5

		//Sessão noite:
		mapa.put(29, new Integer[5][5]); //Plateia A
		mapa.put(30, new Integer[10][10]); //Plateia B
		mapa.put(31, new Integer[5][10]); //Balcão Nobre
		mapa.put(32, new Integer[c][epFrisa]); //Frisa 1
		mapa.put(33, new Integer[c][epFrisa]); //Frisa 2
		mapa.put(34, new Integer[c][epFrisa]); //Frisa 3
		mapa.put(35, new Integer[c][epFrisa]); //Frisa 4
		mapa.put(36, new Integer[c][epFrisa]); //Frisa 5
		mapa.put(37, new Integer[c][epFrisa]); //Frisa 6
		mapa.put(38, new Integer[c][epCam]); //camarote 1
		mapa.put(39, new Integer[c][epCam]); //camarote 2
		mapa.put(40, new Integer[c][epCam]); //camarote 3
		mapa.put(41, new Integer[c][epCam]); //camarote 4
		mapa.put(42, new Integer[c][epCam]); //camarote 5

		//Peça 2
		//Sessão manhã:
		mapa.put(43, new Integer[5][5]); //Plateia A
		mapa.put(44, new Integer[10][10]); //Plateia B
		mapa.put(45, new Integer[5][10]); //Balcão Nobre
		mapa.put(46, new Integer[c][epFrisa]); //Frisa 1
		mapa.put(47, new Integer[c][epFrisa]); //Frisa 2
		mapa.put(48, new Integer[c][epFrisa]); //Frisa 3
		mapa.put(49, new Integer[c][epFrisa]); //Frisa 4
		mapa.put(50, new Integer[c][epFrisa]); //Frisa 5
		mapa.put(51, new Integer[c][epFrisa]); //Frisa 6
		mapa.put(52, new Integer[c][epCam]); //camarote 1
		mapa.put(53, new Integer[c][epCam]); //camarote 2
		mapa.put(54, new Integer[c][epCam]); //camarote 3
		mapa.put(55, new Integer[c][epCam]); //camarote 4
		mapa.put(56, new Integer[c][epCam]); //camarote 5

		//Sessão tarde:
		mapa.put(57, new Integer[5][5]); //Plateia A
		mapa.put(58, new Integer[10][10]); //Plateia B
		mapa.put(59, new Integer[5][10]); //Balcão Nobre
		mapa.put(60, new Integer[c][epFrisa]); //Frisa 1
		mapa.put(61, new Integer[c][epFrisa]); //Frisa 2
		mapa.put(62, new Integer[c][epFrisa]); //Frisa 3
		mapa.put(63, new Integer[c][epFrisa]); //Frisa 4
		mapa.put(64, new Integer[c][epFrisa]); //Frisa 5
		mapa.put(65, new Integer[c][epFrisa]); //Frisa 6
		mapa.put(66, new Integer[c][epCam]); //camarote 1
		mapa.put(67, new Integer[c][epCam]); //camarote 2
		mapa.put(68, new Integer[c][epCam]); //camarote 3
		mapa.put(69, new Integer[c][epCam]); //camarote 4
		mapa.put(70, new Integer[c][epCam]); //camarote 5

		//Sessão noite:
		mapa.put(71, new Integer[5][5]); //Plateia A
		mapa.put(72, new Integer[10][10]); //Plateia B
		mapa.put(73, new Integer[5][10]); //Balcão Nobre
		mapa.put(74, new Integer[c][epFrisa]); //Frisa 1
		mapa.put(75, new Integer[c][epFrisa]); //Frisa 2
		mapa.put(76, new Integer[c][epFrisa]); //Frisa 3
		mapa.put(77, new Integer[c][epFrisa]); //Frisa 4
		mapa.put(78, new Integer[c][epFrisa]); //Frisa 5
		mapa.put(79, new Integer[c][epFrisa]); //Frisa 6
		mapa.put(80, new Integer[c][epCam]); //camarote 1
		mapa.put(81, new Integer[c][epCam]); //camarote 2
		mapa.put(82, new Integer[c][epCam]); //camarote 3
		mapa.put(83, new Integer[c][epCam]); //camarote 4
		mapa.put(84, new Integer[c][epCam]); //camarote 5

		//Resolver o NullPointerException
		for (int a = 1; a <= mapa.size(); a++) {
			for (int i = 0; i < mapa.get(a).length; i++) {
				for (int j = 0; j < mapa.get(a)[0].length; j++) {
					mapa.get(a)[i][j] = 0;
				}
			}
		}
	}

	public static void peencherMapaCli() {
		int a = 255, b = 6;
		//Peça 1
		//Sessão manhã:
		mapaCliente.put(1,new String[a][b]);
		//Sessão tarde:
		mapaCliente.put(2,new String[a][b]);
		//Sessão noite:
		mapaCliente.put(3,new String[a][b]);
		//Peça 2
		//Sessão manhã:
		mapaCliente.put(4,new String[a][b]);
		//Sessão tarde:
		mapaCliente.put(5,new String[a][b]);
		//Sessão noite:
		mapaCliente.put(6,new String[a][b]);


	}

	public static String getPeca(int peca){
		return pecasInf[peca - 1][0];
	}

	public static String getSess(int sess) {
		String sessText = "";
		switch (sess) {
			case 1:
				sessText = "Manhã";
				break;
			case 2:
				sessText = "Tarde";
				break;
			case 3:
				sessText = "Noite";
				break;
		}
		return sessText;
	}

	public static String getArea(int area, int num) {
		String areaText = "";
		switch (area) {
			case 1:
				areaText = "Plateia A";
				break;
			case 2:
				areaText = "Plateia B";
				break;
			case 3:
				areaText = "Frisa " + num;
				break;
			case 4:
				areaText = "Camarote " + num;
				break;
			case 5:
				areaText = "Balcão Nobre";
				break;
		}
		return areaText;
	}

	public static boolean allMatrizHasFull() {
		boolean val = true;
		for (int a = 1; a <= mapa.size(); a++) {
			for (int i = 0; i < mapa.get(a).length; i++) {
				for (int j = 0; j < mapa.get(a)[0].length; j++) {
					if (mapa.get(a)[i][j] == 0) {
						val = false;
						break;
					}
				}
			}
		}
		return val;
	}

	public static boolean matrizHasFull(Integer[][] matriz) {
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

	public static boolean allFrisasHasFull(int sess, int peca) {
		boolean val = true;
		int p = getMatrizesSess(sess, peca);
		for (int a = (4 + p); a <= (9 + p); a++) {
			for (int i = 0; i < mapa.get(a).length; i++) {
				for (int j = 0; j < mapa.get(a)[0].length; j++) {
					if (mapa.get(a)[i][j] == 0) {
						val = false;
						break;
					}
				}
			}
		}
		return val;
	}

	public static boolean allCamHasFull(int sess, int peca) {
		int p = getMatrizesSess(sess, peca);
		boolean val = true;
		for (int a = (10 + p); a <= (14 + p); a++) {
			for (int i = 0; i < mapa.get(a).length; i++) {
				for (int j = 0; j < mapa.get(a)[0].length; j++) {
					if (mapa.get(a)[i][j] == 0) {
						val = false;
						break;
					}
				}
			}
		}
		return val;
	}

	public static int getMatrizesSess(int sess, int peca) {
		int a = 0;
		switch (peca) {
			case 1:
				switch (sess) {
					case 1:
						a = 0;
						break;
					case 2:
						a = 14;
						break;
					case 3:
						a = 28;
						break;
				}
				break;
			case 2:
				switch (sess) {
					case 1:
						a = 42;
						break;
					case 2:
						a = 56;
						break;
					case 3:
						a = 70;
						break;
				}
				break;
		}
		return a;
	}

	public static boolean sessHasFull(int sess, int peca) {
		boolean val = true;
		int p = getMatrizesSess(sess, peca);
		for (int a = (1 + p); a <= (14 + p); a++) {
			for (int i = 0; i < mapa.get(a).length; i++) {
				for (int j = 0; j < mapa.get(a)[0].length; j++) {
					if (mapa.get(a)[i][j] == 0) {
						val = false;
						break;
					}
				}
			}
		}
		return val;
	}

	public static int getMatrizPeca(int peca) {
		int a = 0;
		switch (peca) {
			case 1:
				a = 0;
				break;
			case 2:
				a = 42;
				break;
		}
		return a;
	}

	public static boolean pecaHasFull(int peca) {
		boolean val = true;
		int p = getMatrizPeca(peca);
		for (int a = (1 + p); a <= (42 + p); a++) {
			for (int i = 0; i < mapa.get(a).length; i++) {
				for (int j = 0; j < mapa.get(a)[0].length; j++) {
					if (mapa.get(a)[i][j] == 0) {
						val = false;
						break;
					}
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

