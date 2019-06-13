package little_fighters.content.materials;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Tree2 extends Material {

	public Tree2(int x, int y){
		super();
		
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/tree-2.png"))
				.getImage().getScaledInstance(122, 159, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 122, 159);
		
	
		hold.setBounds(44, 122, 30, 35);
	
		
	}
}
