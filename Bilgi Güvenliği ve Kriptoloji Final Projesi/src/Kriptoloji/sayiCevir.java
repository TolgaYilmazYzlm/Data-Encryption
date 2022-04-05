package Kriptoloji;

public class sayiCevir {
	public static String ikilikTabanaCevir(String gelenSayi) {
		String sonuc = "";
		int sayi = Integer.parseInt(gelenSayi);
		if (sayi == 0) {
            return "0";
        }
		int kalan;
		while(sayi > 0) {
			kalan = sayi%2;
			sayi = (sayi-kalan)/2;
			sonuc = String.valueOf(kalan) + sonuc;
		}
		return sonuc;
	}
	
	public static String onlukTabanaCevir(String gelenSayi) {
		return String.valueOf(Integer.valueOf(gelenSayi,2));
	}
}
