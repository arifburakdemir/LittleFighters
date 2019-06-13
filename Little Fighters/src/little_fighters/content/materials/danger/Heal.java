package little_fighters.content.materials.danger;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import little_fighters.map.GameMachine;

public class Heal extends Danger {

	private int startingX = 0;
	private int startingY = 0;

	private Heal that = this;

	private boolean left = false;
	private boolean top = false;
	private boolean right = false;
	private boolean bottom = false;
	private GameMachine gameMachine;
	
	private boolean stay = true;
	

	Thread thread = new Thread(new Runnable() {

		@Override
		public void run() {

			while (!gameMachine.isGameOver() && stay) {
				if (gameMachine.aprcCollide(gameMachine.getPlayer(), that)) {
					gameMachine.healPlayer(20);
					aprc.setBounds(0, 0, 0, 0);
					stay=false;
				}

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}
	});

	public Heal(int x, int y, GameMachine gameMachine) {
		this.gameMachine = gameMachine;

		startingX = x;
		startingY = y;

		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/heal.png")).getImage()
				.getScaledInstance(15, 15, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 15, 15);

		hold.setBounds(0, 0, 0, 0);
		hold.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	@Override
	public void action(GameMachine gameMachine) {
		aprc.setBounds(startingX, startingY, 15, 15);
		if (!thread.isAlive()) {
			thread.start();
		}
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

}
