package Kriptoloji;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class WindowsForm extends JFrame {
	private static String simetrikAnahtar = anahtarUret.anaharGetir();//simetrik anahtar üretiliyor
	private static RSA Gonderici = new RSA();
	private static RSA Alici = new RSA();
	private static String sifreliAnahtar;
	private static String desifreEdilmisAnahtar;
	private static DES des = new DES();
	private static HashKodu hash = new HashKodu();
	private static String HashKodu1;
	private static String HashKodu2;
	private static String sifreliHashKodu;
	private static String desifreliHashKodu;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					WindowsForm frame = new WindowsForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WindowsForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1231, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Anahtar:" + simetrikAnahtar);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 84, 408, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("G\u00F6nderici:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 10, 94, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Al\u0131c\u0131:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(546, 6, 94, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("De\u015Fifre Edilmi\u015F Anahtar:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(546, 90, 512, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Genel Anahtar:(" + Gonderici.genelAnahtar + "," + Gonderici.e + ")  Özel anahtar:" + Gonderici.ozelAnahtar);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(10, 47, 488, 27);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Genel Anahtar:(" + Alici.genelAnahtar + "," + Alici.e + ")  Özel anahtar:" + Alici.ozelAnahtar);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(546, 47, 504, 27);
		contentPane.add(lblNewLabel_6);
		
		JEditorPane gonderilenMesajView = new JEditorPane();
		gonderilenMesajView.setBounds(10, 258, 336, 170);
		contentPane.add(gonderilenMesajView);
		
		JLabel lblNewLabel_7 = new JLabel("Mesaj:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(10, 228, 104, 27);
		contentPane.add(lblNewLabel_7);
		
		JEditorPane sifreliMetinView = new JEditorPane();
		sifreliMetinView.setEditable(false);
		sifreliMetinView.setBounds(546, 258, 323, 170);
		contentPane.add(sifreliMetinView);
		
		JEditorPane desifreliMetinView = new JEditorPane();
		desifreliMetinView.setBounds(879, 258, 304, 170);
		contentPane.add(desifreliMetinView);
		
		JLabel lblNewLabel_8 = new JLabel("\u015Eifreli Mesaj:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(546, 229, 177, 25);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("De\u015Fifreli Mesaj:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(879, 229, 177, 25);
		contentPane.add(lblNewLabel_9);
		
		
		
		
		JLabel lblNewLabel_3 = new JLabel("p:" + Gonderici.ilkAsal + "   q:" + Gonderici.ikinciAsal);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(114, 10, 203, 27);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_10 = new JLabel("p:" + Alici.ilkAsal + "   q:" + Alici.ikinciAsal);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(610, 10, 440, 27);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("\u015Eifreli Anahtar:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_11.setBounds(10, 122, 129, 16);
		contentPane.add(lblNewLabel_11);
		
		JEditorPane SifreliAnahtarText = new JEditorPane();
		SifreliAnahtarText.setBackground(SystemColor.control);
		SifreliAnahtarText.setEditable(false);
		SifreliAnahtarText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		SifreliAnahtarText.setBounds(10, 146, 440, 72);
		contentPane.add(SifreliAnahtarText);
		
		JLabel lblNewLabel_12 = new JLabel("Hash Kodu:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(10, 454, 488, 27);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("\u015Eifreli Hash Kodu:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(10, 487, 129, 16);
		contentPane.add(lblNewLabel_13);
		
		JEditorPane sifreliHash = new JEditorPane();
		sifreliHash.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sifreliHash.setBackground(SystemColor.control);
		sifreliHash.setBounds(10, 513, 467, 85);
		contentPane.add(sifreliHash);
		
		JLabel lblNewLabel_14 = new JLabel("Gelen Hash Kodu:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_14.setBounds(546, 456, 560, 22);
		contentPane.add(lblNewLabel_14);
		
		JButton btnNewButton = new JButton("GONDER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Gonder butonuna basýnca olanlar:
				//alýcýnýn public anahtarlarýyla (genelAnahtar = n, e=e) RSA algoritmasýnda anahtarý þifreliyoruz:
				sifreliAnahtar = Gonderici.RSASifrele(Alici.genelAnahtar, Alici.e, Gonderici.metniCevir(simetrikAnahtar));
				SifreliAnahtarText.setText(sifreliAnahtar);
				//þifrelenmiþ anahtarý alýcýnýn private anahtarý (d) ve genel anahtarý (n) ile deþifre ediyoruz
				desifreEdilmisAnahtar  = Alici.RSAdesifre(sifreliAnahtar, Alici.genelAnahtar, Alici.ozelAnahtar);
				lblNewLabel_4.setText("Deþifre Edilmiþ Anahtar: " + desifreEdilmisAnahtar);
				//girilen mesajýn alýndýðý kýsým
				String araci = gonderilenMesajView.getText().toString();
				HashKodu1 = "";
				//girilen mesajýn hash kodu çýkarýlýyor
				try {
					HashKodu1 = hash.hashKoduHesapla(araci);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sifreliHashKodu = Gonderici.RSASifrele(Gonderici.genelAnahtar, Gonderici.ozelAnahtar, Gonderici.metniCevir(HashKodu1));
				//hash kodu göndericinin özel anahtarý ile þifreleniyor:
				sifreliHash.setText(sifreliHashKodu);
				//göndericinin genel anahtarý ile þifreli hash deþifreleniyor
				desifreliHashKodu = Gonderici.RSAdesifre(sifreliHashKodu, Gonderici.genelAnahtar, Gonderici.e);
				//hash kodlarýný ekranda gösterdiðimiz kýsým:
				lblNewLabel_14.setText("Gelen Hash Kodu:" + desifreliHashKodu);
				lblNewLabel_12.setText("Hash Kodu:" + HashKodu1);
				//girilen mesajý DES ile þifrelediðimiz kýsým 
				String araci2 = des.desSifreleme(araci,simetrikAnahtar);
				sifreliMetinView.setText(araci2);
				//girilen mesajý DES ile deþifrelediðimiz kýsým
				String araci3 = des.desDesifreleme(araci2, desifreEdilmisAnahtar);
				desifreliMetinView.setText(araci3);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(375, 391, 123, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_15 = new JLabel("De\u015Fifreli Mesaj Hash Kodu:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_15.setBounds(546, 487, 593, 16);
		contentPane.add(lblNewLabel_15);
		
		JLabel hashKarsilastirma = new JLabel("");
		hashKarsilastirma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hashKarsilastirma.setBounds(546, 557, 440, 37);
		contentPane.add(hashKarsilastirma);
		
		JButton btnNewButton_1 = new JButton("KARSILASTIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HashKodu2 = "";
				try {
					HashKodu2 = hash.hashKoduHesapla(desifreliMetinView.getText().toString());
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNewLabel_15.setText("Deþifreli Mesaj Hash Kodu:" + HashKodu2);
				if(desifreliHashKodu.equals(HashKodu2)) {
					hashKarsilastirma.setText("Mesaj Ýçeriði Deðiþtirilmemiþ.");
				}else {
					hashKarsilastirma.setText("Mesaj Ýçeriði Deðiþtirilmiþ.");
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(546, 513, 177, 34);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
		
		
	}
}
