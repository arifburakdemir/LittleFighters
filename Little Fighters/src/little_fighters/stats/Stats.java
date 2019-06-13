package little_fighters.stats;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import little_fighters.LittleFighters;
import little_fighters.map.GameMachine;
import little_fighters.map.Map;
import little_fighters.map.editor.MapEditor;
import main.StartMenu;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stats extends LittleFighters {

	public JPanel container = new JPanel();

	JLabel healt = new JLabel();
	JLabel healt2 = new JLabel();
	JLabel timer = new JLabel("00:00");

	public static int WIDTH = 1280;
	public static int HEIGHT = 752;

	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int MARGINX = (int) (SCREENSIZE.getWidth() - WIDTH) / 2;
	public static int MARGINY = (int) (SCREENSIZE.getHeight() - HEIGHT) / 2;

	public Stats(GameMachine gameMachine) {

		container.setBounds(0, HEIGHT - 89, WIDTH, 67);
		container.setBorder(BorderFactory.createLineBorder(Color.black));
		container.setLayout(null);

		JLabel icon = new JLabel();
		icon.setBounds(6, 6, 29, 55);
		icon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/knight.png")).getImage()
				.getScaledInstance(29, 55, Image.SCALE_DEFAULT)));
		container.add(icon);

		healt.setOpaque(true);
		healt.setBackground(Color.RED);
		healt.setBounds(47, 18, 200, 30);
		container.add(healt);

		healt2.setOpaque(true);
		healt2.setBackground(Color.WHITE);
		healt2.setBounds(47, 18, 200, 30);
		container.add(healt2);
		timer.setForeground(Color.WHITE);

		timer.setBounds(259, 25, 36, 16);
		container.add(timer);

		JLabel skill = new JLabel("");
		skill.setBackground(Color.RED);
		skill.setBounds(307, 8, 50, 50);
		container.add(skill);

		JButton exit_btn = new JButton("Exit");
		exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameMachine.getjFrame().dispose();
			}
		});
		exit_btn.setBounds(1219, 6, 55, 55);
		container.add(exit_btn);

		JButton replay_btn = new JButton("Replay");
		replay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameMachine.endGame();
				Map map = new Map(gameMachine.getMaterials(),gameMachine.getDangers());
				map.play(gameMachine.getPlayer().getNickName());
			}
		});
		replay_btn.setBounds(970, 6, 55, 55);
		container.add(replay_btn);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameMachine.endGame();
				StartMenu startMenu = new StartMenu();
				startMenu.setVisible(true);
			}
		});
		btnMainMenu.setBounds(1128, 6, 79, 55);
		container.add(btnMainMenu);

		JButton btnMapEditor = new JButton("Map Editor");
		btnMapEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameMachine.endGame();
				MapEditor mapEditor = new MapEditor(gameMachine.getMaterials() , gameMachine.getDangers());
				mapEditor.setVisible(true);
			}
		});
		btnMapEditor.setBounds(1037, 6, 79, 55);
		container.add(btnMapEditor);

		JLabel lblLittleFighter = new JLabel("Little Fighters");
		lblLittleFighter.setForeground(Color.WHITE);
		lblLittleFighter.setFont(new Font("Kokonor", Font.PLAIN, 39));
		lblLittleFighter.setHorizontalAlignment(SwingConstants.CENTER);
		lblLittleFighter.setBounds(479, 6, 322, 55);
		container.add(lblLittleFighter);

		JLabel background = new JLabel();
		background.setBounds(0, 0, container.getWidth(), container.getHeight());
		background.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/bar_background.jpg")).getImage()
				.getScaledInstance(container.getWidth(), container.getHeight(), Image.SCALE_DEFAULT)));
		container.add(background);

	}

	public void updateTime(int time) {
		int minutues = 0;
		while (time >= 60) {
			time -= 60;
			minutues++;
		}

		String timeStr = "";
		if (minutues < 10) {
			timeStr += "0";
		}
		
		timeStr += minutues + ":";
		if (time < 10) {
			timeStr += "0";
		}
		timeStr += time + "";
		
		timer.setText(timeStr);
	}
	
	public String getTime(){
		return timer.getText();
	}

	public void updateHealth(int health) {
		int each = 200 / 100;
		int total = health * each;

		healt.setBounds(healt.getX(), healt.getY(), total, healt.getHeight());

	}
}
