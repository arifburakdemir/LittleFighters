package little_fighters.map;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.StartMenu;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver extends JFrame {
	
	JPanel contentPane = new JPanel();
	public static int WIDTH = 500;
	public static int HEIGHT = 233;

	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int MARGINX = (int) (SCREENSIZE.getWidth() - WIDTH) / 2;
	public static int MARGINY = (int) (SCREENSIZE.getHeight() - HEIGHT) / 2;

	public GameOver(String tim,GameMachine gameOver) {
		
		this.setContentPane(contentPane);
		this.setBounds(MARGINX, MARGINY, WIDTH, HEIGHT);
		
		contentPane.setLayout(null);
		
		JLabel gameOver_lb = new JLabel("GAME OVER");
		gameOver_lb.setFont(new Font("Kokonor", Font.PLAIN, 32));
		gameOver_lb.setBounds(143, 34, 214, 37);
		contentPane.add(gameOver_lb);
		
		JLabel youWon_lb = new JLabel("YOU WON");
		youWon_lb.setFont(new Font("Kokonor", Font.PLAIN, 13));
		youWon_lb.setBounds(215, 68, 69, 16);
		contentPane.add(youWon_lb);
		
		JLabel time_lb = new JLabel("Time : " + tim);
		time_lb.setBounds(144, 110, 214, 16);
		contentPane.add(time_lb);
		
		JButton mainMenu_btn = new JButton("Main Menu");
		mainMenu_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameOver.getjFrame().dispose();
				dispose();
				StartMenu startMenu = new StartMenu();
				startMenu.setVisible(true);
			}
		});
		mainMenu_btn.setBounds(143, 139, 117, 29);
		contentPane.add(mainMenu_btn);
		
		JButton exit_btn = new JButton("Exit");
		exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameOver.getjFrame().dispose();
				dispose();
			}
		});
		exit_btn.setBounds(272, 138, 117, 29);
		contentPane.add(exit_btn);

	}
}
