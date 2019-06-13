package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Well extends Material {

	public Well(int x, int y){
		super();
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/well.png"))
				.getImage().getScaledInstance(50, 43, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 50, 43);
		hold.setBounds(0, 21, 50, 22);
	}
}
