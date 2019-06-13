package little_fighters.content.materials.danger;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import little_fighters.map.GameMachine;
import little_fighters.map.Map;

public class Fire extends Danger {

	private int startingX = 0;
	private int startingY = 0;

	private Fire that = this;

	private boolean left = false;
	private boolean top = false;
	private boolean right = false;
	private boolean bottom = false;
	private GameMachine gameMachine ;

	Thread thread = new Thread(new Runnable() {

		@Override
		public void run() {

			int x = 0;
			int y = 0;

			if (bottom) {
				y = +1;
			} else if (top) {
				y = -1;
			}
			if (right) {
				x = -1;
			} else if (left) {
				x = 1;
			}

			while (!gameMachine.isGameOver()) {

				aprc.setBounds(getX() + x, getY() + y, getWidth(), getHeight());
				if(gameMachine.aprcCollide(gameMachine.getPlayer(), that)){
					gameMachine.damgePlayer(5);
					
				}
					
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			aprc.setBounds(0, 0, 0, 0);

		}
	});
	public Fire(int x, int y,GameMachine gameMachine) {
		this.gameMachine = gameMachine;

		startingX = x;
		startingY = y;

		aprc.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/fire.png")).getImage()
				.getScaledInstance(10, 10, Image.SCALE_DEFAULT)));
		aprc.setBounds(x, y, 10, 10);

		hold.setBounds(0, 0, 0, 0);
		hold.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	@Override
	public void action(GameMachine gameMachine) {
		aprc.setBounds(startingX, startingY, 10, 10);
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
