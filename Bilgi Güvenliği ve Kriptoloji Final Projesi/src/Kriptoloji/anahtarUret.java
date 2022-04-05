package Kriptoloji;

import java.util.Random;

public class anahtarUret {
	
	public static Random rand = new Random();
	
	public static String anaharGetir() {
		String anahtar = "";
		int sayiTutucu;
		char karakterTutucu;
		while(anahtar.length()<32) {
			//simetrik �ifreleme i�in 32 karakter uzunlu�unda ingiliz alfabesi ve rakamlar� i�eren �ifre �retimi
			sayiTutucu = rand.nextInt(3);
			karakterTutucu = karakterDondur(sayiTutucu);
			anahtar += karakterTutucu;
		}
		return anahtar;
	}
	
	public static char karakterDondur(int gelenSayi) {
		//say�n�n ascii kar��l���na �evrildi�i k�s�m
		int  sayiTutucu_2;
		if(gelenSayi == 0) {
			sayiTutucu_2 = rand.nextInt(10) + 48;
			return (char)sayiTutucu_2;
		}else if(gelenSayi == 1) {
			sayiTutucu_2 = rand.nextInt(26) + 65;
			return (char)sayiTutucu_2;
		}else if(gelenSayi == 2) {
			sayiTutucu_2 = rand.nextInt(26) + 97;
			return (char)sayiTutucu_2;
		}else {
			return ' ';
		}
	}
	
}
