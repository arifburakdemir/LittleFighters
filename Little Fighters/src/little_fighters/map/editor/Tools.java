package little_fighters.map.editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONException;

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
import little_fighters.content.materials.danger.FireStatue;
import little_fighters.content.materials.danger.Heal;
import little_fighters.content.materials.danger.HealStatue;
import little_fighters.content.materials.danger.LaserStatue;
import little_fighters.json.JSONMap;
import main.StartMenu;

public class Tools extends JFrame{

	JPanel contentPane= new JPanel();
	
	public static int WIDTH = 500;
	public static int HEIGHT = 500;

	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int MARGINX = (int) (SCREENSIZE.getWidth() - WIDTH) / 2;
	public static int MARGINY = (int) (SCREENSIZE.getHeight() - HEIGHT) / 2;
	
	
	ArrayList<Components> components = new ArrayList<>();
	ArrayList<Components> map_items = new ArrayList<>();
	
	ArrayList<Material> materials = new ArrayList<>();
	ArrayList<Material> dangers = new ArrayList<>();
	

	private final JButton zOrder_btn = new JButton("Z ORDERED");
	private final JLabel delete_lb = new JLabel("Delete");
	private final JButton save_btn = new JButton("Save");
	
	MapEditor mapEditor;
	private final JButton btnMainMenu = new JButton("Main Menu");

	
	public Tools(MapEditor mapEditor){
		this.mapEditor = mapEditor;
		this.setBounds(0, 0, 1280, 259);
		this.setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		addContents();

		JPanel panel = new JPanel();
		int startingX = 6;
		int startingY = 6;

		int marginTop = 20;
		int paddinYCounter = startingY;

		for (int counter = 0; counter < this.components.size(); counter++) {

			int width = this.components.get(counter).getAprc().getWidth();
			int height = this.components.get(counter).getAprc().getHeight();

			this.components.get(counter).getAprc().setBounds(startingX, paddinYCounter, width, height);
			panel.add(this.components.get(counter).getAprc());
			paddinYCounter += height + counter * marginTop;
		}

		panel.setBounds(6, 6, 250, 1280);
		repaint();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
		scrollPane.setBounds(6, 6, 1154, 220);
		contentPane.add(scrollPane);
		
		zOrder_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapEditor.zOrder();
				mapEditor.repaint();
			}
		});
		zOrder_btn.setBounds(1172, 17, 102, 35);

		contentPane.add(zOrder_btn);


		delete_lb.setForeground(Color.blue);
		delete_lb.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (mapEditor.delete) {
					mapEditor.delete = false;
					delete_lb.setForeground(Color.blue);
				} else {
					mapEditor.delete = true;
					delete_lb.setForeground(Color.red);
				}
			}
		});
		delete_lb.setBounds(1169, 167, 117, 59);

		contentPane.add(delete_lb);
		save_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JSONMap jsonMap = new JSONMap(dangers, materials);
				try {
					jsonMap.write();
				} catch (IOException | JSONException e1) {
					e1.printStackTrace();
				}
				dispose();
				mapEditor.dispose();
				StartMenu startMenu = new StartMenu();
				startMenu.setVisible(true);
			}
		});
		save_btn.setBounds(1172, 64, 102, 35);
		
		contentPane.add(save_btn);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mapEditor.dispose();
				StartMenu startMenu = new StartMenu();
				startMenu.setVisible(true);
			}
		});
		btnMainMenu.setBounds(1172, 111, 102, 44);
		
		contentPane.add(btnMainMenu);
		
	}
	
	public void addContents() {
		this.components.add(new Components(new Cart(0, 0), true, mapEditor));
		this.components.add(new Components(new Fence(0, 0), true, mapEditor));
		this.components.add(new Components(new Tent(0, 0), true, mapEditor));
		this.components.add(new Components(new Tree(0, 0), true, mapEditor));
		this.components.add(new Components(new Tree1(0, 0), true, mapEditor));
		this.components.add(new Components(new Tree2(0, 0), true, mapEditor));
		this.components.add(new Components(new Wall(0, 0), true, mapEditor));
		this.components.add(new Components(new WallGate(0, 0), true, mapEditor));
		this.components.add(new Components(new WallVertical(0, 0), true, mapEditor));
		this.components.add(new Components(new Well(0, 0), true, mapEditor));
		this.components.add(new Components(new Wood(0, 0), true, mapEditor));
		this.components.add(new Components(new FireStatue(0, 0), true, mapEditor));
		this.components.add(new Components(new LaserStatue(0, 0), true, mapEditor));
		HealStatue healStatue =new HealStatue(0, 0);
		healStatue.setWidth(15);
		healStatue.setHeight(15);
		this.components.add(new Components(healStatue, true, mapEditor));
		}
	
}
