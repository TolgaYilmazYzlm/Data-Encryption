package Kriptoloji;

import java.util.Random;

public class anahtarUret {
	
	public static Random rand = new Random();
	
	public static String anaharGetir() {
		String anahtar = "";
		int sayiTutucu;
		char karakterTutucu;
		while(anahtar.length()<32) {
			//simetrik þifreleme için 32 karakter uzunluðunda ingiliz alfabesi ve rakamlarý içeren þifre üretimi
			sayiTutucu = rand.nextInt(3);
			karakterTutucu = karakterDondur(sayiTutucu);
			anahtar += karakterTutucu;
		}
		return anahtar;
	}
	
	public static char karakterDondur(int gelenSayi) {
		//sayýnýn ascii karþýlýðýna çevrildiði kýsým
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
