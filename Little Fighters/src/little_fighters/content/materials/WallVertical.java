package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class WallVertical extends Material {

	public WallVertical(int x, int y){
		super();
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/wall-vertical.png"))
				.getImage().getScaledInstance(18, 96, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 18, 96);
		hold.setBounds(0, 43, 12, 53);
	}
}
