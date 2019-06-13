package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Wood extends Material {

	public Wood(int x, int y){
		super();
		
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/wood.png"))
				.getImage().getScaledInstance(53, 40, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 53, 40);
		hold.setBounds(0, 13, 53, 27);

	
		
	}
}
