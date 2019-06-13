package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class WallGate extends Material {

	public WallGate(int x, int y){
		super();
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/wall-gate.png"))
				.getImage().getScaledInstance(70, 64, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 70, 64);
		hold.setBounds(45, 44, 25, 20);
	}
}
