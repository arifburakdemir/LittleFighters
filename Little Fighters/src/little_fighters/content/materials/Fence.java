package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Fence extends Material {

	public Fence(int x, int y){
		super();
		
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/fence.png"))
				.getImage().getScaledInstance(93, 28, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 93, 28);
		hold.setBounds(2, 21, 89, 7);

	
		
	}
}
