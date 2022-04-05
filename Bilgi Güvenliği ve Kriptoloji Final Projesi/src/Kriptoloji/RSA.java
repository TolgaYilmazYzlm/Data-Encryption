package Kriptoloji;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    public static int[] asalSayilar = new int[1229];
    public static int[] ucBasamakliAsalSaylar = new int[143];
    public BigInteger genelAnahtar;
    public BigInteger ozelAnahtar;
    public BigInteger e;
    public BigInteger totient;
    public BigInteger ilkAsal;
    public BigInteger ikinciAsal;
    
    public RSA() {
    	asalSayiBul();
    	anahtarUretRSA();
    }

    public static void asalSayiBul() {
        int sayac2 = 1;
        //tüm asal sayýlarý buluyoruz
        asalSayilar[0] = 2;
        for (int i = 3; i <= 10000; i++) {
            int sayac = 0;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    sayac++;
                    break;
                }
            }
            if (sayac == 0) {
                asalSayilar[sayac2] = i;
                sayac2++;
            }
        }
        int sayi = 0;
        //3 basamaklý asal sayýlarý buluyoruz
        for (int i = 0; i < asalSayilar.length; i++) {
            if (asalSayilar[i] > 99 && asalSayilar[i] < 1000) {

                ucBasamakliAsalSaylar[sayi] = asalSayilar[i];
                sayi++;
            }
        }

    }

    public void anahtarUretRSA() {
        Random rastgele = new Random();
        //p ve q rastgele seçiyoruz
        ilkAsal = BigInteger.valueOf(ucBasamakliAsalSaylar[rastgele.nextInt(142)]);
        ikinciAsal = BigInteger.valueOf(ucBasamakliAsalSaylar[rastgele.nextInt(142)]);
        while (ilkAsal == ikinciAsal) {//p ve q ayný ise q yu tekrar buluyoruz
            ikinciAsal = BigInteger.valueOf(ucBasamakliAsalSaylar[rastgele.nextInt(142)]);
        }
        BigInteger eksiBir = BigInteger.valueOf(1); // subtract ile çýkaracaðýmýz için -1 deðil 1 veriyoruz
        genelAnahtar = ilkAsal.multiply(ikinciAsal);//p ve q yu çarpýyoruz
        totient = (ilkAsal.subtract(eksiBir)).multiply(ikinciAsal.subtract(eksiBir));//p ve q nun 1 eksiðini çarpýyoruz totienti buluyoruz

        while (true) {
            e = BigInteger.valueOf(rastgele.nextInt(Integer.valueOf(totient.toString())));
            if (Integer.valueOf(e.toString()) > 100000) {
                continue; // hýzlý olmasý için e yi 100000 den aþaðýda tutuyoruz (yoksa dakikalar sürüyor)
            }
            if (totient.gcd(e).equals(eksiBir) && Integer.valueOf(e.toString()) > 1) {
                break;
            }
        }
        while (true) {
            ozelAnahtar = BigInteger.valueOf(rastgele.nextInt(Integer.valueOf(totient.toString())));

            if ((ozelAnahtar.multiply(e)).mod(totient).equals(eksiBir) && Integer.valueOf(e.toString()) > 1) {
                break;
            }
        }

    }

    public static String metniCevir(String metin) {
    	//karakterleri 3 basamaklý sayýlara çeviriyoruz
        String sayisalMetin = "";
        String tutucu = "";
        int deger;
        char[] karakterler = metin.toCharArray();
        for (int i = 0; i < karakterler.length; i++) {
            deger = (int) karakterler[i];
            if (deger < 100) {
                tutucu = "0" + String.valueOf(deger);
            } else {
                tutucu = String.valueOf(deger);
            }
            sayisalMetin += tutucu;
        }
        return sayisalMetin;
    }

    public static String RSASifrele(BigInteger genelAnahtarFun, BigInteger eFun, String metin) {
        char[] karakterDizesi = metin.toCharArray();
        int genelAnahtarInt = Integer.valueOf(genelAnahtarFun.toString());
        int eDegeri = Integer.valueOf(eFun.toString());
        int basamakSayisi = 0;
        while (genelAnahtarInt > 0) {
            genelAnahtarInt /= 10;
            basamakSayisi++;
        }
        basamakSayisi--;
        int diziUzunluk = (int) Math.ceil((double) karakterDizesi.length / (double) basamakSayisi);
        String[] stringDizesi = new String[diziUzunluk];
        for (int i = 0; i < diziUzunluk; i++) {
            stringDizesi[i] = "";
        }
        //gelen sayýsal metni n'nin basamak sayýsýnýn 1 küçüðü parçalara ayýrýyoruz
        for (int i = 0; i < karakterDizesi.length; i++) {
            stringDizesi[(int) Math.floor((double)i / (double)basamakSayisi)] += karakterDizesi[i];
        }//sonuncusu boþ kalýrsa 0 ile dolduruyoruz
        while (stringDizesi[diziUzunluk - 1].length() < basamakSayisi) {
            stringDizesi[diziUzunluk - 1] = stringDizesi[diziUzunluk - 1] + "0";
        }
        String sifreliMetin = "";
        String araci;
        int ceviri;
        BigInteger sifrelencekSayilar, tutucu;
        for (int i = 0; i < stringDizesi.length; i++) {
        	//metinleri þifreleme kýsmý
            ceviri = Integer.parseInt(stringDizesi[i]);
            sifrelencekSayilar = BigInteger.valueOf(ceviri);
            tutucu = sifrelencekSayilar.mod(genelAnahtarFun).pow(eDegeri).mod(genelAnahtarFun);
            araci = tutucu.toString();
            while (araci.length() < basamakSayisi + 1) {
                araci = "0" + araci;
            }
            sifreliMetin += araci;
        }
        String sifreliMetin2 = "";
        //þifreli metnin karakterlere çevrilmesi
        String [] sifreliMetinParca = new String[(int) ((double)sifreliMetin.length() / 2.0)];
        for(int i = 0;i<sifreliMetinParca.length;i++) {
        	sifreliMetinParca[i] = "";
        }
        char [] sifreliMetinKarakterler = sifreliMetin.toCharArray();
        for(int i = 0;i<sifreliMetinKarakterler.length;i++) {
        	sifreliMetinParca[(int) Math.floor((double)i / 2.0)] += sifreliMetinKarakterler[i];
        }
        for(int i = 0;i<sifreliMetinParca.length;i++) {
        	int a = Integer.valueOf(sifreliMetinParca[i]);
        	char b = (char) a;
        	sifreliMetin2 += b;
        }
        return sifreliMetin2;
    }

    public static String RSAdesifre(String metin, BigInteger genelAnahtar, BigInteger ozelAnahtar) {
        int genelAnahtarInt = Integer.valueOf(genelAnahtar.toString());
        int ozelAnahtarInt = Integer.valueOf(ozelAnahtar.toString());
        String karakterDonusum = "";
        char [] karakterler = metin.toCharArray();
        for(int i = 0;i<karakterler.length;i++) {
        	int a = (int) karakterler[i];
        	String b = String.valueOf(a);
        	while(b.length()<2) {
        		b = "0" + b;
        	}
        	karakterDonusum += b;
        }
        char[] karakterDizesi = karakterDonusum.toCharArray();
        int basamakSayisi = 0;
        while (genelAnahtarInt > 0) {
            genelAnahtarInt /= 10;
            basamakSayisi++;
        }
        int diziUzunluk = (int) Math.ceil((double) karakterDizesi.length / (double) basamakSayisi);
        String[] stringDizesi = new String[diziUzunluk];
        for (int i = 0; i < diziUzunluk; i++) {
            stringDizesi[i] = "";
        }//gelen metni n'nin basamak sayýsýna parçalalýyoruz
        for (int i = 0; i < karakterDizesi.length; i++) {
            stringDizesi[(int) Math.floor((double)i / (double)basamakSayisi)] += karakterDizesi[i];
        }
        String islemGorecekMetin = "";
        String araci;
        int ceviri;
        BigInteger desifrelenecekSayilar, tutucu;
        for (int i = 0; i < stringDizesi.length; i++) {
        	//deþifreleme kýsmý
            ceviri = Integer.valueOf(stringDizesi[i]);
            desifrelenecekSayilar = BigInteger.valueOf(ceviri);
            tutucu = desifrelenecekSayilar.mod(genelAnahtar).pow(ozelAnahtarInt).mod(genelAnahtar);
            araci = tutucu.toString();
            //n'nin basamak sayýsýnýn 1 eksiðinden daha küçük varsa baþýna 0 ekliyoruz
            while (araci.length() < basamakSayisi - 1) {
                araci = "0" + araci;
            }
            islemGorecekMetin += araci;
        }
        char[] karakterDizesi2 = islemGorecekMetin.toCharArray();
        int diziUzunluk2 = (int) Math.ceil((double) karakterDizesi2.length / (double) 3);
        String[] stringDizesi2 = new String[diziUzunluk2];
        for (int i = 0; i < diziUzunluk2; i++) {
            stringDizesi2[i] = "";
        }
        for (int i = 0; i < karakterDizesi2.length; i++) {
            stringDizesi2[(int) Math.floor((double)i / (double)3)] += karakterDizesi2[i];
        }
        String desifrelenenMetin = "";
        char tutucu2;
        int asciiKod;
        for (int i = 0; i < stringDizesi2.length; i++) {
        	//sayýsal deðerleri karakterlere çeviriyoruz
            asciiKod = Integer.valueOf(stringDizesi2[i]);
            tutucu2 = (char) asciiKod;
            if (i == stringDizesi2.length - 1 && asciiKod == 0) {
                break;
            }
            desifrelenenMetin += tutucu2;
        }//gelen deðer 32 den fazlaysa fazlalýk kýsmý atýyoruz (hem anahtar hem hash 32 karakter uzunluðunda)
        if(desifrelenenMetin.length()>32) {
        	char [] araci3 = desifrelenenMetin.toCharArray();
        	desifrelenenMetin = "";
        	for(int i = 0;i<32;i++) {
        		desifrelenenMetin += araci3[i];
        	}
        }
        return desifrelenenMetin;
    }

}
