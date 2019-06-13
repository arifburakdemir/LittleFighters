package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Tree1 extends Material {

	public Tree1(int x, int y){
		super();
		
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/tree-1.png"))
				.getImage().getScaledInstance(115, 157, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 115, 157);
		
	
		hold.setBounds(45, 117, 30, 35);
	
		
	}
}
