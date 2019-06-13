package little_fighters.map.editor;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import org.json.JSONException;

import little_fighters.content.materials.*;
import little_fighters.content.materials.danger.Danger;
import little_fighters.content.materials.danger.FireStatue;
import little_fighters.json.JSONMap;
import little_fighters.map.Map;
import main.StartMenu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MapEditor extends JFrame {

	JPanel contentPane = new JPanel();

	JPanel map = new JPanel();
	JLabel background = new JLabel();
	Tools tools;

	public boolean delete = false;

	public MapEditor(ArrayList<Material> materials, ArrayList<Material> dangers) {
		this.setBounds(0, 0, 1280, 700);
		this.setContentPane(contentPane);

		contentPane.setLayout(null);

		map.setLayout(null);
		map.setBounds(0, 0, 1280, 800);
		background.setBounds(0, 0, 1280, 700);
		map.add(background);
		background.setIcon(new ImageIcon(getClass().getResource("/images/grass.png")));

		contentPane.add(map);
		tools = new Tools(this);
		tools.setVisible(true);
		tools.dangers = dangers;
		tools.materials = materials;
		updateMap();
	}

	public void updateMap() {
		for (int counter = 0; counter < tools.dangers.size(); counter++) {
			Components components = new Components(tools.dangers.get(counter), false, this);
			map.add(components.getAprc());
		}
		for (int counter = 0; counter < tools.materials.size(); counter++) {
			Components components = new Components(tools.materials.get(counter), false, this);
			map.add(components.getAprc());
		}
		zOrder();
		repaint();

	}

	public MapEditor() {
		this.setBounds(0, 0, 1280, 700);
		this.setContentPane(contentPane);

		contentPane.setLayout(null);

		map.setBounds(0, 0, 1280, 800);
		map.add(background);
		background.setIcon(new ImageIcon(getClass().getResource("/images/grass.png")));

		contentPane.add(map);
		tools = new Tools(this);
		tools.setVisible(true);

	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public void removeMetarialFromMap(Components comp) {
		for (int counter = 0; counter < tools.map_items.size(); counter++) {
			if (tools.map_items.get(counter) == comp) {
				map.remove(tools.map_items.get(counter).getAprc());
				tools.materials.remove(counter);
				tools.map_items.remove(counter);
			}
		}
		repaint();
	}

	public void addMetarialToMap(Components comp) {
		tools.map_items.add(comp);
		tools.materials.add(comp.getMetarial());
		this.map.add(comp.getAprc());
		this.map.setComponentZOrder(comp.getAprc(), 0);
		this.map.repaint();

		String str = Danger.class.toString().replaceAll(".Danger", "");
		if (comp.getMetarial().getClass().toString().contains(str)) {
			System.out.println("aynıııı");
			tools.dangers.add(comp.getMetarial());
		}
	}

	public void zOrder() {
		Component[] components = map.getComponents();
		for (int counter = 0; counter < components.length; counter++) {
			for (int counter2 = 0; counter2 < components.length; counter2++) {
				Component mt1 = components[counter];
				Component mt2 = components[counter2];
				if (mt1.getY() + mt1.getHeight() < mt2.getY() + mt2.getHeight()) {
					components[counter] = mt2;
					components[counter2] = mt1;
				}
			}
		}
		for (int counter = components.length - 1; counter > -1; counter--) {
			map.setComponentZOrder(components[counter], -counter + components.length - 1);
		}
		map.setComponentZOrder(background, components.length - 1);
	}

}
