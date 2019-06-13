package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Cart extends Material {

	public Cart(int x, int y){
		super();
		
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/cart.png"))
				.getImage().getScaledInstance(90, 150, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 90, 150);

		hold.setBounds(0, 40, 90, 100);

	
		
	}
}
