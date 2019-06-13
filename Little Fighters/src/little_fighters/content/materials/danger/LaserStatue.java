package little_fighters.content.materials.danger;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import little_fighters.map.GameMachine;

public class LaserStatue extends Danger {

	public LaserStatue(int x, int y) {
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/laserStatue.png")).getImage()
				.getScaledInstance(30, 78, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 30, 78);
		hold.setBounds(0, 58, 28, 20);
		hold.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	@Override
	public void action(GameMachine gameMachine) {

		Thread thread = new Thread(new Runnable() {

	

			@Override
			public void run() {

				Laser laser = new Laser(aprc.getX() + 12, aprc.getY() + 2,gameMachine);
				laser.setLeft(true);
				laser.action(gameMachine);
				gameMachine.getContentPane().add(laser.getAprc());
				gameMachine.getContentPane().setComponentZOrder(laser.getAprc(), 0);
		


				while (!gameMachine.isGameOver()) {
					
					laser.getAprc().setBounds(0, 0, 0, 0);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

					laser.getAprc().setBounds(aprc.getX() + 12, aprc.getY() +2, aprc.getWidth(), 20);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();

	}
}
