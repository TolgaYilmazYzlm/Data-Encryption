package Kriptoloji;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashKodu {
	
	public String hashKoduHesapla(String metin) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(metin.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hashKodu = bigInt.toString(16);
        while (hashKodu.length() < 32) {
        	hashKodu = "0" + hashKodu;
        }
		return hashKodu;
	}

}
