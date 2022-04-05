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
        //t�m asal say�lar� buluyoruz
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
        //3 basamakl� asal say�lar� buluyoruz
        for (int i = 0; i < asalSayilar.length; i++) {
            if (asalSayilar[i] > 99 && asalSayilar[i] < 1000) {

                ucBasamakliAsalSaylar[sayi] = asalSayilar[i];
                sayi++;
            }
        }

    }

    public void anahtarUretRSA() {
        Random rastgele = new Random();
        //p ve q rastgele se�iyoruz
        ilkAsal = BigInteger.valueOf(ucBasamakliAsalSaylar[rastgele.nextInt(142)]);
        ikinciAsal = BigInteger.valueOf(ucBasamakliAsalSaylar[rastgele.nextInt(142)]);
        while (ilkAsal == ikinciAsal) {//p ve q ayn� ise q yu tekrar buluyoruz
            ikinciAsal = BigInteger.valueOf(ucBasamakliAsalSaylar[rastgele.nextInt(142)]);
        }
        BigInteger eksiBir = BigInteger.valueOf(1); // subtract ile ��karaca��m�z i�in -1 de�il 1 veriyoruz
        genelAnahtar = ilkAsal.multiply(ikinciAsal);//p ve q yu �arp�yoruz
        totient = (ilkAsal.subtract(eksiBir)).multiply(ikinciAsal.subtract(eksiBir));//p ve q nun 1 eksi�ini �arp�yoruz totienti buluyoruz

        while (true) {
            e = BigInteger.valueOf(rastgele.nextInt(Integer.valueOf(totient.toString())));
            if (Integer.valueOf(e.toString()) > 100000) {
                continue; // h�zl� olmas� i�in e yi 100000 den a�a��da tutuyoruz (yoksa dakikalar s�r�yor)
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
    	//karakterleri 3 basamakl� say�lara �eviriyoruz
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
        //gelen say�sal metni n'nin basamak say�s�n�n 1 k����� par�alara ay�r�yoruz
        for (int i = 0; i < karakterDizesi.length; i++) {
            stringDizesi[(int) Math.floor((double)i / (double)basamakSayisi)] += karakterDizesi[i];
        }//sonuncusu bo� kal�rsa 0 ile dolduruyoruz
        while (stringDizesi[diziUzunluk - 1].length() < basamakSayisi) {
            stringDizesi[diziUzunluk - 1] = stringDizesi[diziUzunluk - 1] + "0";
        }
        String sifreliMetin = "";
        String araci;
        int ceviri;
        BigInteger sifrelencekSayilar, tutucu;
        for (int i = 0; i < stringDizesi.length; i++) {
        	//metinleri �ifreleme k�sm�
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
        //�ifreli metnin karakterlere �evrilmesi
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
        }//gelen metni n'nin basamak say�s�na par�alal�yoruz
        for (int i = 0; i < karakterDizesi.length; i++) {
            stringDizesi[(int) Math.floor((double)i / (double)basamakSayisi)] += karakterDizesi[i];
        }
        String islemGorecekMetin = "";
        String araci;
        int ceviri;
        BigInteger desifrelenecekSayilar, tutucu;
        for (int i = 0; i < stringDizesi.length; i++) {
        	//de�ifreleme k�sm�
            ceviri = Integer.valueOf(stringDizesi[i]);
            desifrelenecekSayilar = BigInteger.valueOf(ceviri);
            tutucu = desifrelenecekSayilar.mod(genelAnahtar).pow(ozelAnahtarInt).mod(genelAnahtar);
            araci = tutucu.toString();
            //n'nin basamak say�s�n�n 1 eksi�inden daha k���k varsa ba��na 0 ekliyoruz
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
        	//say�sal de�erleri karakterlere �eviriyoruz
            asciiKod = Integer.valueOf(stringDizesi2[i]);
            tutucu2 = (char) asciiKod;
            if (i == stringDizesi2.length - 1 && asciiKod == 0) {
                break;
            }
            desifrelenenMetin += tutucu2;
        }//gelen de�er 32 den fazlaysa fazlal�k k�sm� at�yoruz (hem anahtar hem hash 32 karakter uzunlu�unda)
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
