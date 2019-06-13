package little_fighters.content.creatures;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Worrior extends Creature {

	public Worrior() {
		super();
		// ---

		damageAnim.start();

		this.icon = new ImageIcon(new ImageIcon(getClass().getResource("/images/creature/darkknight.png")).getImage());
		// --- GORUNTU EKLEME VE GORUNTUYU BOYA GORE UZATMA
		JLabel borderAnim = new JLabel("");
		borderAnim.setBounds(0, 10, 31, 45);
		animation.setBounds(0, 0, 128, 192);
		animation.setIcon(icon);
		borderAnim.add(animation);

		aprc.add(borderAnim);

		// --- GORUNTUNUN BOYUNU BELİRLEDİK
		aprc.setBounds(10, 10, 31, 55);

		// --- YUKARDAN BOSLUK BIRAKTIK NİCKNAME İCİN

		// --- NİCKNAME'İN BOYUTUNU VE DURUCAĞI YERİ BELİRTTİK
		nickName_l.setBounds(0, 0, 29, 10);

		// --- HOLD ICIN YERINI BOYUNU
		hold.setBounds(0, 45, 31, 10);

		// --- KARAKTER OZELLİKLEİRNİ EKLEDİK
		maxSpeed = 6;
		maxHealth = 100;

	}

}
