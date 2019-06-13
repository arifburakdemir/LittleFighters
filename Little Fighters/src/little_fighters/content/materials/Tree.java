package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Tree extends Material {

	public Tree(int x, int y){
		super();
		
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/tree.png"))
				.getImage().getScaledInstance(170, 206, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 170, 206);
		
	
		hold.setBounds(60, 140, 50, 50);
	
		
	}
}
