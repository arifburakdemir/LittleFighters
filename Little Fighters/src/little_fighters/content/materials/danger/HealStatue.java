package little_fighters.content.materials.danger;

import java.awt.Image;

import javax.swing.ImageIcon;

import little_fighters.map.GameMachine;

public class HealStatue extends Danger {

	public HealStatue(int x, int y) {
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/heal.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 0, 0);
		hold.setBounds(0, 0, 0, 0);

	}

	@Override
	public void action(GameMachine gameMachine) {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				Heal heal = new Heal(getX(), getY(), gameMachine);
				heal.setLeft(true);
				heal.action(gameMachine);
				gameMachine.getContentPane().add(heal.getAprc());
				gameMachine.getContentPane().setComponentZOrder(heal.getAprc(), 0);

			}
		});
		thread.start();

	}
}
