package Kriptoloji;

public class DES {
    private static int[] genisletmePermutasyonu = { 31, 0, 1, 2, 3, 4, 3, 4, 5, 6, 7, 8, 7, 8, 9, 10, 11, 12, 11,
                    12,
                    13, 14, 15, 16, 15, 16, 17, 18, 19, 20, 19, 20, 21, 22, 23, 24, 23, 24, 25, 26, 27, 28, 27, 28,
                    29, 30, 31,
                    0 };// do�ru
    private static int[][] sKutulari = {
                    { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13,
                                    1, 10, 6, 12, 11, 9, 5, 3, 8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5,
                                    0, 15, 12, 8, 2, 4, 9, 1,
                                    7, 5, 11, 3, 14, 10, 0, 6, 13 }, // do�ru
                    { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10,
                                    6, 9, 11,
                                    5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4,
                                    2, 11, 6, 7, 12, 0,
                                    5, 14, 9 }, // do�ru
                    { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14,
                                    12, 11, 15,
                                    1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8,
                                    7, 4, 15, 14, 3, 11,
                                    5, 2, 12 }, // do�ru
                    { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12,
                                    1, 10, 14,
                                    9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13,
                                    8, 9, 4, 5, 11, 12,
                                    7, 2, 14 }, // do�ru
                    { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15,
                                    10, 3, 9, 8,
                                    6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2,
                                    13, 6, 15, 0, 9,
                                    10, 4, 5, 3 }, // do�ru
                    { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14,
                                    0, 11, 3,
                                    8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15,
                                    10, 11, 14, 1, 7, 6,
                                    0, 8, 13 }, // do�ru
                    { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12,
                                    2, 15, 8,
                                    6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10,
                                    7, 9, 5, 0, 15, 14,
                                    2, 3, 12 }, // do�ru
                    { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11,
                                    0, 14, 9,
                                    2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8,
                                    13, 15, 12, 9, 0, 3,
                                    5, 6, 11 } };// do�ru
    private static int[] pPermutasyonu = { 15, 6, 19, 20, 28, 11, 27, 16, 0, 14, 22, 25, 4, 17, 30, 9, 1, 7, 23, 13,
                    31,
                    26, 2, 8, 18, 12, 29, 5, 21, 10, 3, 24 }; // do�ru

    private static int[] anahtarKaydirmaSayi = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 }; // do�ru

    private static int[] anahtarSikistirmaPerm = { 13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7,
                    15,
                    6, 26, 19, 12, 1, 40, 51, 30, 36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45,
                    41, 49, 35,
                    28, 31 }; // do�ru
    private static int[] anahtarPermutasyonu = { 56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50,
                    42,
                    34, 26, 18, 10, 2, 59, 51, 43, 35, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5,
                    60, 52, 44,
                    36, 28, 20, 12, 4, 27, 19, 11, 3 }; // do�ru
    private String ikilikAnahtar;
    private String[] anahtarPermSonucu = new String[4];
    private String[] anahtarSol = new String[4];
    private String[] anahtarSag = new String[4];
    private String[][] anahtarSolKaydirilmis = new String[4][16];
    private String[][] anahtarSagKaydirilmis = new String[4][16];
    private String[][] donguyeGirecekAnahtarlar = new String[4][16];
    private String[] metinParcali;
    private String ikilikMetin;
    private String SifreliMetin;
    public String sifreliMetinDonusum = "";

    public String desSifreleme(String metin, String anahtar) {
    		//anahtar� ikilik say�ya �eviriyoruz
            anahtarIkilikCevir(anahtar);
            //64 bitlik anahtar girdisinden 56 tanesini se�iyoruz
            anahtarPermutFonk();
            //anahtar� ikiye b�l�p kayd�rmalar� yap�yoruz
            anahtarKaydirma();
            //se�ilen 56 bitten d�ng�ye girecek olan 48 tanesini se�me k�sm�
            anahtarSikistirmaPermFonk();

            //metini ikilik say�ya �eviriyoruz
            metinIkilikCevirSifreleme(metin);
            //metni 64 bit par�alara b�l�yoruz
            metinParcala();
            
            //�ifreleme yap�lan k�s�m
            sifrele();
            
            //gelen �ifreli de�eri karakterlere �evirdi�imiz k�s�m
            String[] sifreliMetinParcala = new String[SifreliMetin.length() / 8];
            for (int i = 0; i < sifreliMetinParcala.length; i++) {
                    sifreliMetinParcala[i] = "";
            }
            char[] sifreliMetinKarakterleri = SifreliMetin.toCharArray();
            for (int i = 0; i < sifreliMetinParcala.length; i++) {
                    for (int j = 0; j < 8; j++) {
                            sifreliMetinParcala[i] += sifreliMetinKarakterleri[(i * 8) + j];
                    }
            }
            sifreliMetinDonusum = "";
            for (int i = 0; i < sifreliMetinParcala.length; i++) {
                    String tutucu = sayiCevir.onlukTabanaCevir(sifreliMetinParcala[i]);
                    int araci = Integer.valueOf(tutucu);
                    char b = (char) araci;
                    sifreliMetinDonusum += b;
            }
            return sifreliMetinDonusum;

    }

    public String desDesifreleme(String metin, String anahtar) {
    		//anahtar� ikilik say�ya �eviriyoruz
            anahtarIkilikCevir(anahtar);
            //64 bitlik anahtar girdisinden 56 tanesini se�iyoruz
            anahtarPermutFonk();
            //anahtar� ikiye b�l�p kayd�rmalar� yap�yoruz
            anahtarKaydirma();
            //se�ilen 56 bitten d�ng�ye girecek olan 48 tanesini se�me k�sm�
            anahtarSikistirmaPermFonk();
            
            //gelen metni ikilik tabana �eviriyoruz
            metinIkilikCevirdeSifreleme(metin);
            //metni 64 bitlik par�alara b�l�yoruz
            metinParcala();
            //de�ifreleme k�sm�
            desifrele();
            
            //gelen de�ifrelenmi� metni karakterlere �eviriyoruz
            String[] sifreliMetinParcala = new String[SifreliMetin.length() / 16];
            for (int i = 0; i < sifreliMetinParcala.length; i++) {
                    sifreliMetinParcala[i] = "";
            }
            char[] sifreliMetinKarakterleri = SifreliMetin.toCharArray();
            for (int i = 0; i < sifreliMetinParcala.length; i++) {
                    for (int j = 0; j < 16; j++) {
                            sifreliMetinParcala[i] += sifreliMetinKarakterleri[(i * 16) + j];
                    }
            }
            sifreliMetinDonusum = "";
            for (int i = 0; i < sifreliMetinParcala.length; i++) {
                    String tutucu = sayiCevir.onlukTabanaCevir(sifreliMetinParcala[i]);
                    int araci = Integer.valueOf(tutucu);
                    if (araci == 0) {
                            continue;
                    }
                    char b = (char) araci;
                    sifreliMetinDonusum += b;
            }
            return sifreliMetinDonusum;

    }

    public void sifrele() {
            SifreliMetin = "";
            for (int i = 0; i < metinParcali.length; i++) {
                    String metinSol = "";
                    String metinSag = "";
                    //�ifrelenecek ilk 64 biti 2 par�aya ay�r�yoruz
                    char[] parcaTutucu = metinParcali[i].toCharArray();
                    for (int j = 0; j < 64; j++) {
                            if (j < 32) {
                                    metinSol += parcaTutucu[j];
                            } else {
                                    metinSag += parcaTutucu[j];
                            }

                    }
                    //gelen anahtar�n hangi 64 bitini kullanaca��m�z� se�mek i�in:
                    int anahtarDongu = i % 4;
                    
                    //16 d�ng�:
                    for (int j = 0; j < 16; j++) {
                    		//sag k�sm� 48 bite geni�letiyoruz
                            String genisSag = genisletmePermFonk(metinSag);
                            //anahtar ile XOR a sokuyoruz
                            String genisSagXOR = XORFonk(genisSag, donguyeGirecekAnahtarlar[anahtarDongu][j]);

                            
                            //48 bitlik de�eri tekrar 32 bite �evirmek i�in SBOX a sokuyoruz
                            String darSagSbox = sBoxFonk(genisSagXOR);
                            //p permutasyonu ile bitleri kar��t�r�yoruz
                            String darSagpPermut = pPermutasyonuFonk(darSagSbox);
                            //metnin solu ile XOR a sokuyoruz
                            String darSagXORsol = XORFonk(darSagpPermut, metinSol);
                            
                            //sonraki d�ng�ye girecek par�alar� ayarl�yoruz
                            metinSol = metinSag;
                            metinSag = darSagXORsol;
                    }
                    String bitisik = metinSag + metinSol;

                    SifreliMetin += bitisik;
            }
    }

    public void desifrele() {
            SifreliMetin = "";
            for (int i = 0; i < metinParcali.length; i++) {
                    String metinSol = "";
                    String metinSag = "";
                    //gelen 64 bitlik par�ay� ikiye b�l�yoruz
                    char[] parcaTutucu = metinParcali[i].toCharArray();
                    for (int j = 0; j < 64; j++) {
                            if (j < 32) {
                                    metinSol += parcaTutucu[j];
                            } else {
                                    metinSag += parcaTutucu[j];
                            }

                    }
                    //hangi anahtar�n kullan�laca��n� se�iyoruz:
                    int anahtarDongu = i % 4;
                    //16 d�ng�:
                    for (int j = 15; j > -1; j--) {
                    		//sa� k�sm� 48 bite geni�letiyoruz
                            String genisSag = genisletmePermFonk(metinSag);
                            //Anahtar ile XOR a sokuyoruz ama �ifrelemenin tersi s�rada
                            String genisSagXOR = XORFonk(genisSag, donguyeGirecekAnahtarlar[anahtarDongu][j]);
                            //48 bitlik de�eri SBOX ile 32 bit e d���yoruz
                            String darSagSbox = sBoxFonk(genisSagXOR);
                            //p permutasyonu ile 32 biti kar��t�r�yoruz
                            String darSagpPermut = pPermutasyonuFonk(darSagSbox);
                            //metnin sol k�sm� ile XOR a sokuyoruz
                            String darSagXORsol = XORFonk(darSagpPermut, metinSol);
                            //sonraki d�ng�ye girecek olan de�erleri ayarl�yoruz
                            metinSol = metinSag;
                            metinSag = darSagXORsol;
                    }
                    String bitisik = metinSag + metinSol;
                    SifreliMetin += bitisik;
            }
    }

    public void anahtarIkilikCevir(String anahtar) {
    	//anahtar�n ikilik tabana �evrildi�i fonksiyon
            ikilikAnahtar = "";
            char[] anahtarKarakterleri = anahtar.toCharArray();
            for (int i = 0; i < anahtar.length(); i++) {
                    int araci = (int) anahtarKarakterleri[i];
                    String araci2 = String.valueOf(araci);
                    araci2 = sayiCevir.ikilikTabanaCevir(araci2);
                    while (araci2.length() < 8) {
                            araci2 = "0" + araci2;
                    }
                    ikilikAnahtar += araci2;
            }

    }

    public void anahtarPermutFonk() {
    	//64 bitten 56 bitin se�ildi�i fonksiyon
            for (int i = 0; i < 4; i++) {
                    anahtarPermSonucu[i] = "";
            }
            char[] ikilikAnahtarKarakterleri = ikilikAnahtar.toCharArray();
            String[] araciAnahtar = new String[4];
            for (int i = 0; i < 4; i++) {
                    araciAnahtar[i] = "";
                    for (int j = 0; j < 64; j++) {
                            araciAnahtar[i] += ikilikAnahtarKarakterleri[(i * 64) + j];
                    }
            }
            for (int j = 0; j < 4; j++) {
                    char[] araciKarakterler = araciAnahtar[j].toCharArray();
                    for (int i = 0; i < 56; i++) {
                            anahtarPermSonucu[j] += araciKarakterler[anahtarPermutasyonu[i]];
                    }
            }
    }

    public void anahtarKaydirma() {
    	//56 bitin ikiye b�l�n�p kayd�r�ld��� b�l�m
            for (int i = 0; i < 4; i++) {
                    anahtarSag[i] = "";
                    anahtarSol[i] = "";
            }

            for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 16; i++) {
                            anahtarSolKaydirilmis[j][i] = "";
                            anahtarSagKaydirilmis[j][i] = "";
                    }
            }

            for (int i = 0; i < 4; i++) {
                    char[] araciKarakterler = anahtarPermSonucu[i].toCharArray();
                    for (int j = 0; j < 56; j++) {
                            if (j < 28) {
                                    anahtarSol[i] += araciKarakterler[j];
                            } else {
                                    anahtarSag[i] += araciKarakterler[j];
                            }
                    }
            }
            for (int i = 0; i < 4; i++) {
                    char[] anahtarSolKarakterleri = anahtarSol[i].toCharArray();
                    char[] anahtarSagKarakterleri = anahtarSag[i].toCharArray();
                    for (int j = 0; j < 16; j++) {
                            for (int k = 0; k < anahtarKaydirmaSayi[j]; k++) {
                                    char solTutucu = anahtarSolKarakterleri[0];
                                    char sagTutucu = anahtarSagKarakterleri[0];
                                    for (int n = 0; n < 27; n++) {

                                            anahtarSolKarakterleri[n] = anahtarSolKarakterleri[n + 1];
                                            anahtarSagKarakterleri[n] = anahtarSagKarakterleri[n + 1];
                                    }

                                    anahtarSolKarakterleri[27] = solTutucu;
                                    anahtarSagKarakterleri[27] = sagTutucu;
                            }
                            for (int k = 0; k < 28; k++) {
                                    anahtarSolKaydirilmis[i][j] += anahtarSolKarakterleri[k];
                                    anahtarSagKaydirilmis[i][j] += anahtarSagKarakterleri[k];
                            }
                    }
            }

    }

    public void anahtarSikistirmaPermFonk() {
    	//anahtarlar�n s�k��t�r�l�p 48 bit oldu�u -  d�ng�lerde kullan�lacak anahtarlar�n se�ildi�i fonksiyon
            for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 16; j++) {
                            donguyeGirecekAnahtarlar[i][j] = "";
                    }
            }
            for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 16; j++) {
                            String anahtarBirlesimi = anahtarSolKaydirilmis[i][j] + anahtarSagKaydirilmis[i][j];
                            char[] birlesmisAnahtarKarakterleri = anahtarBirlesimi.toCharArray();
                            for (int k = 0; k < 48; k++) {
                                    donguyeGirecekAnahtarlar[i][j] += birlesmisAnahtarKarakterleri[anahtarSikistirmaPerm[j]];
                            }
                    }
            }

    }

    public void metinIkilikCevirSifreleme(String metin) {
    	//�ifrelenecek metnin karakterlerinin 16 bitlik (t�rk�e karakterleri almas� i�in) hallerine �evrildi�i yer
            ikilikMetin = "";
            char[] metinKarakterleri = metin.toCharArray();
            for (int i = 0; i < metinKarakterleri.length; i++) {
                    int tutucu = (int) metinKarakterleri[i];
                    String tutucu2 = String.valueOf(tutucu);
                    tutucu2 = sayiCevir.ikilikTabanaCevir(tutucu2);
                    while (tutucu2.length() < 16) {
                            tutucu2 = "0" + tutucu2;
                    }
                    ikilikMetin += tutucu2;
            }
    }
    
    public void metinIkilikCevirdeSifreleme(String metin) {
    	//�ifreli metnin karakterlerinin 8 bit e �evrildi�i k�s�m 
        ikilikMetin = "";
        char[] metinKarakterleri = metin.toCharArray();
        for (int i = 0; i < metinKarakterleri.length; i++) {
                int tutucu = (int) metinKarakterleri[i];
                String tutucu2 = String.valueOf(tutucu);
                tutucu2 = sayiCevir.ikilikTabanaCevir(tutucu2);
                while (tutucu2.length() < 8) {
                        tutucu2 = "0" + tutucu2;
                }
                ikilikMetin += tutucu2;
        }
}

    public void metinParcala() {
    	//metnin 64 bitlik par�alara ayr�ld��� fonksiyon
            metinParcali = new String[(int) Math.ceil((double) ikilikMetin.length() / 64.0)];
            for (int i = 0; i < metinParcali.length; i++) {
                    metinParcali[i] = "";
            }
            while (true) {
                    if (ikilikMetin.length() % 64 == 0) {
                            break;
                    } else {
                            ikilikMetin += "0";
                    }
            }
            char[] ikilikMetinKarakterleri = ikilikMetin.toCharArray();
            for (int i = 0; i < metinParcali.length; i++) {
                    for (int j = 0; j < 64; j++) {
                            metinParcali[i] += ikilikMetinKarakterleri[(i * 64) + j];
                    }
            }
    }

    public String genisletmePermFonk(String metinSag) {
    	//metnin 48 bite geni�letildi�i geni�letme permutasyonu fonksiyonu
            String sonuc = "";
            char[] metinSagKarakterleri = metinSag.toCharArray();
            for (int i = 0; i < 48; i++) {
                    sonuc += metinSagKarakterleri[genisletmePermutasyonu[i]];
            }

            return sonuc;
    }

    public String XORFonk(String sagMetin, String anahtar) {
    	//gelen de�erleri XOR layan fonksiyon
            String sonuc = "";
            char[] sagMetinKarakterleri = sagMetin.toCharArray();
            char[] anahtarKarakteri = anahtar.toCharArray();
            for (int i = 0; i < sagMetin.length(); i++) {
                    if (sagMetinKarakterleri[i] == anahtarKarakteri[i]) {
                            sonuc += "0";
                    } else {
                            sonuc += "1";
                    }
            }
            return sonuc;
    }

    public String sBoxFonk(String gelenMetin) {
    	//48 biti 32 bit e �evirdi�imiz SBOX fonksiyonu
            String sonuc = "";
            char[] gelenMetinKarakterleri = gelenMetin.toCharArray();
            String[] parcalanmisMetin = new String[8];
            for (int i = 0; i < 8; i++) {
                    parcalanmisMetin[i] = "";
            }
            for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 6; j++) {
                            parcalanmisMetin[i] += gelenMetinKarakterleri[(i * 6) + j];
                    }
            }

            for (int i = 0; i < 8; i++) {
                    String tutucu = parcalanmisMetin[i];
                    char[] tutucuKarakterleri = tutucu.toCharArray();

                    String satir = "", sutun = "";
                    satir += tutucuKarakterleri[0];
                    satir += tutucuKarakterleri[5];

                    sutun += tutucuKarakterleri[1];
                    sutun += tutucuKarakterleri[2];
                    sutun += tutucuKarakterleri[3];
                    sutun += tutucuKarakterleri[4];

                    satir = sayiCevir.onlukTabanaCevir(satir);
                    sutun = sayiCevir.onlukTabanaCevir(sutun);
                    
                    
                    
                    int satir1 = Integer.valueOf(satir);
                    int sutun1 = Integer.valueOf(sutun);
                    int sboxGelen = sKutulari[i][(satir1 * 16) + sutun1];

                    String strSboxGelen = String.valueOf(sboxGelen);
                    strSboxGelen = sayiCevir.ikilikTabanaCevir(strSboxGelen);
                    while (strSboxGelen.length() < 4) {
                            strSboxGelen = "0" + strSboxGelen;
                    }

                    sonuc += strSboxGelen;

            }

            return sonuc;
    }

    public String pPermutasyonuFonk(String gelenMetin) {
    	//32 biti kar��t�rd���m�z p permutasyonu fonksiyonu
            String sonuc = "";
            char[] gelenMetinKarakterleri = gelenMetin.toCharArray();
            for (int i = 0; i < 32; i++) {
                    sonuc += gelenMetinKarakterleri[pPermutasyonu[i]];
            }
            return sonuc;
    }

    

}
