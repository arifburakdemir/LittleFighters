package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Tent extends Material {

	public Tent(int x, int y){
		super();
		
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/tent.png"))
				.getImage().getScaledInstance(146, 160, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 146, 160);
		hold.setBounds(0, 60, 143, 100);

	
		
	}
}
