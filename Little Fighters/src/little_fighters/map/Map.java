package little_fighters.map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import little_fighters.content.materials.Cart;
import little_fighters.content.materials.Fence;
import little_fighters.content.materials.Material;
import little_fighters.content.materials.Tent;
import little_fighters.content.materials.Tree;
import little_fighters.content.materials.Tree1;
import little_fighters.content.materials.Tree2;
import little_fighters.content.materials.Wall;
import little_fighters.content.materials.WallGate;
import little_fighters.content.materials.WallVertical;
import little_fighters.content.materials.Well;
import little_fighters.content.materials.Wood;
import little_fighters.content.materials.danger.Fire;

public class Map extends GameMachine {

	public Map(ArrayList<Material> materials, ArrayList<Material> dangers) {
		this.dangers = dangers;
		this.materials = materials;
	}

	public void play(String nickName) {
		// -----PLAYER ISLEMLERI
		run(nickName);

		for (int counter = 0; counter < materials.size(); counter++) {
			contentPane.add(materials.get(counter).getAprc());
		}

		background.setIcon(new ImageIcon(getClass().getResource("/images/grass.png")));

		gameMachine();
		danger();
		zOrder();
		getContentPane().repaint();

	}
}
