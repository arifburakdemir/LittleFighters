package little_fighters.content.materials.danger;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import little_fighters.map.GameMachine;

public class FireStatue extends Danger {

	public FireStatue(int x, int y) {
		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/fireStatue.png")).getImage()
				.getScaledInstance(34, 78, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 34, 78);
		hold.setBounds(7, 58, 22, 20);

	}

	@Override
	public void action(GameMachine gameMachine) {

		Thread thread = new Thread(new Runnable() {

	

			@Override
			public void run() {

				ArrayList<Fire> fires = new ArrayList<>();
				Fire fire = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire.setLeft(true);
				fire.action(gameMachine);
				gameMachine.getContentPane().add(fire.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire.getAprc(), 0);
				fires.add(fire);

				Fire fire2 = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire2.setRight(true);
				fire2.action(gameMachine);
				gameMachine.getContentPane().add(fire2.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire2.getAprc(), 0);
				fires.add(fire2);

				Fire fire3 = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire3.setBottom(true);
				fire3.action(gameMachine);
				gameMachine.getContentPane().add(fire3.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire3.getAprc(), 0);
				fires.add(fire3);

				Fire fire4 = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire4.setTop(true);
				fire4.action(gameMachine);
				gameMachine.getContentPane().add(fire4.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire4.getAprc(), 0);
				fires.add(fire4);

				Fire fire5 = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire5.setLeft(true);
				fire5.setTop(true);
				fire5.action(gameMachine);
				gameMachine.getContentPane().add(fire5.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire5.getAprc(), 0);
				fires.add(fire5);

				Fire fire6 = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire6.setRight(true);
				fire6.setTop(true);
				fire6.action(gameMachine);
				gameMachine.getContentPane().add(fire6.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire6.getAprc(), 0);
				fires.add(fire6);

				Fire fire7 = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire7.setBottom(true);
				fire7.setRight(true);
				fire7.action(gameMachine);
				gameMachine.getContentPane().add(fire7.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire7.getAprc(), 0);
				fires.add(fire7);

				Fire fire8 = new Fire(aprc.getX() + 10, aprc.getY() + 10,gameMachine);
				fire8.setBottom(true);
				fire8.setLeft(true);
				fire8.action(gameMachine);
				gameMachine.getContentPane().add(fire8.getAprc());
				gameMachine.getContentPane().setComponentZOrder(fire8.getAprc(), 0);
				fires.add(fire8);

				while (!gameMachine.isGameOver()) {
					
					for(int counter = 0 ; counter < fires.size() ; counter++){
						fires.get(counter).action(gameMachine);
					}
					
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
