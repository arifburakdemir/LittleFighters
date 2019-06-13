package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import little_fighters.map.Choose;
import little_fighters.map.GameMachine;
import little_fighters.map.Map;
import little_fighters.map.editor.MapEditor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StartMenu extends JFrame {

	private JPanel contentPane;
	StartMenu startMenu;

	public static int WIDTH = 500;
	public static int HEIGHT = 233;

	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int MARGINX = (int) (SCREENSIZE.getWidth() - WIDTH) / 2;
	public static int MARGINY = (int) (SCREENSIZE.getHeight() - HEIGHT) / 2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu frame = new StartMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource("/images/creature/icon.png")));
		startMenu = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setBounds(MARGINX, MARGINY, WIDTH, HEIGHT);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel title_label = new JLabel("Little Fighters version 1");
		title_label.setFont(new Font("Kokonor", Font.PLAIN, 34));
		title_label.setBounds(73, 19, 354, 70);
		contentPane.add(title_label);

		JButton startGame_btn = new JButton("Start Game");
		startGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choose choose = new Choose();
				choose.setVisible(true);
				dispose();
			}
		});
		startGame_btn.setBounds(191, 98, 117, 29);
		contentPane.add(startGame_btn);
		
		JButton mpEditor_btn = new JButton("Map Editor");
		mpEditor_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapEditor mapEditor = new MapEditor();
				mapEditor.setVisible(true);
				setVisible(false);
			}
		});
		mpEditor_btn.setBounds(191, 139, 117, 29);
		contentPane.add(mpEditor_btn);



	}
}
