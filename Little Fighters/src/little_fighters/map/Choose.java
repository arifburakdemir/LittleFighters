package little_fighters.map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;

import org.json.JSONException;

import little_fighters.content.creatures.Assasin;
import little_fighters.content.creatures.Chest;
import little_fighters.content.creatures.Creature;
import little_fighters.content.creatures.IronMan;
import little_fighters.content.creatures.SnowWhite;
import little_fighters.content.creatures.Worrior;
import little_fighters.json.JSONMap;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Choose extends JFrame {

	JPanel contentPane = new JPanel();
	ArrayList<Map> maps = new ArrayList<>();

	Map selectedMap;
	public static int WIDTH = 337;
	public static int HEIGHT = 254;

	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int MARGINX = (int) (SCREENSIZE.getWidth() - WIDTH) / 2;
	public static int MARGINY = (int) (SCREENSIZE.getHeight() - HEIGHT) / 2;

	ArrayList<Creature> characters = new ArrayList<>();
	Creature selectedChr;

	private JTextField name_tf;

	public Choose() {
		this.setBounds(MARGINX, MARGINY, 347, 309);
		this.setContentPane(contentPane);

		contentPane.setLayout(null);

		JList<String> map_list = new JList<String>();
		map_list.setBounds(6, 79, 167, 202);
		contentPane.add(map_list);

		JButton play_btn = new JButton("Play");
		play_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedChr == null) {
					JOptionPane.showMessageDialog(null, "Pls Select Character.");
				}
				else if (name_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Pls Write Yout Nickname");
				}
				else if (map_list.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Pls Select Map");
				} else {
					dispose();
					selectedChr.getAprc().setBorder(null);
					maps.get(map_list.getSelectedIndex()).setPlayer(selectedChr);
					maps.get(map_list.getSelectedIndex()).play(name_tf.getText());
				}
			}
		});
		play_btn.setBounds(185, 183, 117, 29);
		contentPane.add(play_btn);

		name_tf = new JTextField();
		name_tf.setBounds(185, 145, 130, 26);
		contentPane.add(name_tf);
		name_tf.setColumns(10);
		System.out.println("READDEN ONCE");
		try {
			maps = JSONMap.read();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | IOException
				| JSONException e) {
			e.printStackTrace();
		}
		DefaultListModel<String> defaultListModel = new DefaultListModel<>();
		for (int counter = 0; counter < maps.size(); counter++) {
			defaultListModel.addElement("Map " + (counter + 1));
		}

		map_list.setModel(defaultListModel);

		JLabel nickName_lb = new JLabel("Nickname : ");
		nickName_lb.setBounds(185, 117, 130, 16);
		contentPane.add(nickName_lb);
		addCharacters();

		for (int counter = 0; counter < characters.size(); counter++) {
			addChooseListener(characters.get(counter).getAprc());
		}

	}

	public void addCharacters() {
		Creature worrior = new Worrior();
		characters.add(worrior);
		contentPane.add(worrior.getAprc());

		Creature assasin = new Assasin();
		assasin.getAprc().setBounds(41, 10, assasin.getAprc().getWidth(), assasin.getAprc().getHeight());
		characters.add(assasin);
		contentPane.add(assasin.getAprc());

		Creature iroman = new IronMan();
		iroman.getAprc().setBounds(77, 10, iroman.getAprc().getWidth(), iroman.getAprc().getHeight());
		characters.add(iroman);
		contentPane.add(iroman.getAprc());

		Creature chest = new Chest();
		chest.getAprc().setBounds(113, 10, chest.getAprc().getWidth(), chest.getAprc().getHeight());
		characters.add(chest);
		contentPane.add(chest.getAprc());

		Creature snowWhite = new SnowWhite();
		snowWhite.getAprc().setBounds(149, 10, snowWhite.getAprc().getWidth(), snowWhite.getAprc().getHeight());
		characters.add(snowWhite);
		contentPane.add(snowWhite.getAprc());
	}

	public void addChooseListener(JLabel label) {
		label.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				for (int counter = 0; counter < characters.size(); counter++) {
					characters.get(counter).getAprc().setBorder(null);
				}
				label.setBorder(BorderFactory.createLineBorder(Color.black));
				for (int counter = 0; counter < characters.size(); counter++) {
					if (characters.get(counter).getAprc().getBorder() != null) {
						selectedChr = characters.get(counter);
						break;
					}
					;
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
}
