package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Wall extends Material {

	public Wall(int x, int y){
		super();
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/wall.png"))
				.getImage().getScaledInstance(246, 64, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 246, 64);
		hold.setBounds(0, 44, 239, 20);
	}
}
