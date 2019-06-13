package little_fighters.map;

import javax.swing.JFrame;
import javax.swing.JLabel;

import little_fighters.LittleFighters;
import little_fighters.content.creatures.Creature;
import little_fighters.content.Content;
import little_fighters.content.creatures.Worrior;
import little_fighters.content.materials.Material;
import little_fighters.content.materials.Tent;
import little_fighters.content.materials.danger.Danger;
import little_fighters.stats.Stats;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameMachine extends LittleFighters {

	GameMachine that = this;
	private Creature player = new Worrior();

	protected ArrayList<Creature> creatures = new ArrayList<>();
	protected ArrayList<Material> materials = new ArrayList<>();
	protected ArrayList<Material> dangers = new ArrayList<>();

	protected Material tent = new Tent(0, 0);

	protected JFrame jFrame = new JFrame();
	protected JPanel contentPane = new JPanel();
	protected JLabel background = new JLabel();
	protected JLabel fps = new JLabel("");
	protected Stats stats = new Stats(this);

	protected int time = 0;

	protected boolean gameOver = false;
	protected boolean showGameOver = true;

	public static int WIDTH = 1280;
	public static int HEIGHT = 752;

	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int MARGINX = (int) (SCREENSIZE.getWidth() - WIDTH) / 2;
	public static int MARGINY = (int) (SCREENSIZE.getHeight() - HEIGHT) / 2;

	public GameMachine() {

	}

	public void run(String nickName) {
		// -- GÖRÜNTUY AYARLARI
		jFrame.setVisible(true);
		jFrame.setContentPane(contentPane);
		jFrame.requestFocus();
		jFrame.requestFocusInWindow();
		jFrame.setBounds(MARGINX, MARGINY, WIDTH, HEIGHT);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.setUp(false);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.setDown(false);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.setRight(false);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.setLeft(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.setUp(true);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.setDown(true);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.setRight(true);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.setLeft(true);
				}

			}
		});

		contentPane.setLayout(null);

		background.setBounds(0, 0, 1280, 800);
		contentPane.add(background);

		contentPane.add(stats.container);

		fps.setBounds(500, 10, 50, 50);
		contentPane.add(fps);

		player.setNickName(nickName);
		contentPane.add(player.getAprc());

		for (int counter = 0; counter < materials.size(); counter++) {
			if (materials.get(counter).getClass().equals(tent.getClass())) {
				tent = materials.get(counter);
			}
		}

	}

	public void gameMachine() {
		jFrame.repaint();
		basicPhysics();
	}

	public void danger() {

		for (int counter = 0; counter < dangers.size(); counter++) {
			((Danger) dangers.get(counter)).action(this);
		}

	}

	public void basicPhysics() {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!gameOver) {

					if (!player.isAlive()) {
						gameOver = true;
					}

					player.setmUp(true);
					player.setmDown(true);
					player.setmRight(true);
					player.setmLeft(true);

					
					
					
					if (player.getCrSpeed() == player.getMaxSpeed()) {
						// ------ PLAYER - MATERİAL - COLLİDE - ZORDER
						for (int counter = 0; counter < materials.size(); counter++) {
							moveCheck(player, materials.get(counter));
							zOrder(player, materials.get(counter));
						}
						if (player.isUp() && player.ismUp()) {
							player.setY(player.getY() - 1);
						}
						if (player.isDown() && player.ismDown()) {
							player.setY(player.getY() + 1);
						}
						if (player.isLeft() && player.ismLeft()) {
							player.setX(player.getX() - 1);
						}
						if (player.isRight() && player.ismRight()) {
							player.setX(player.getX() + 1);
						}
						

						if (zOrder(player, tent)) {
							gameOver = true;
						}

						player.setCrSpeed(0);
						if (player.getAnimCount() == player.getMaxSpeed() + 5) {
							player.moveAnimation();
							player.setAnimCount(0);
						}
						player.setAnimCount(player.getAnimCount() + 1);

					}
					player.setCrSpeed(player.getCrSpeed() + 1);

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (showGameOver) {
					GameOver gameOver = new GameOver(stats.getTime(),that);
					gameOver.setVisible(true);
				}
			}
		});
		thread.start();

		Thread timer = new Thread(new Runnable() {

			@Override
			public void run() {

				while (!gameOver) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					time++;
					stats.updateTime(time);
				}
			}
		});
		timer.start();
	}

	// -------------
	// ------------- carpisma ve z ekseni
	// -------------
	public void moveCheck(Creature player, Content crMt) {

		// ------------- Y EKSENINDEKI HARETEKRLERI KISITLAMAK
		// ICIN
		if (player.getWidth_h() < crMt.getWidth_h()) {
			if ((player.getX_h() < crMt.rightPoint_h() && player.getX_h() > crMt.getX_h())
					|| (player.rightPoint_h() < crMt.rightPoint_h() && player.rightPoint_h() > crMt.getX_h())) {
				// USTE GITMESINI ENGELLEMEK ICIN
				if (player.getY_h() == crMt.bottomPoint_h() && player.bottomPoint_h() > crMt.getY_h()) {
					player.setmUp(false);
				}
				// ALTA GITMESINI ENGELLEMEK ICIN
				if (player.bottomPoint_h() == crMt.getY_h() && player.getY_h() < crMt.bottomPoint_h()) {
					player.setmDown(false);
				}
			}
		} else {
			if (player.getX_h() < crMt.rightPoint_h() && player.rightPoint_h() > crMt.getX_h()) {
				// USTE GITMESINI ENGELLEMEK ICIN
				if (player.getY_h() == crMt.bottomPoint_h() && player.bottomPoint_h() > crMt.getY_h()) {
					player.setmUp(false);
				}
				// ALTA GITMESINI ENGELLEMEK ICIN
				if (player.bottomPoint_h() == crMt.getY_h() && player.getY_h() < crMt.bottomPoint_h()) {
					player.setmDown(false);
				}
			}
		}

		// ------------- X EKSENINDEKI HARETEKRLERI KISITLAMAK
		// ICIN

		if (player.getHeight_h() < crMt.getHeight_h()) {
			if ((player.getY_h() > crMt.getY_h() && player.getY_h() < crMt.bottomPoint_h())
					|| (player.bottomPoint_h() < crMt.bottomPoint_h() && player.bottomPoint_h() > crMt.getY_h())) {
				// SAGA GITMESINI ENGELLEMEK ICIN
				if (player.rightPoint_h() == crMt.getX_h() && player.getX_h() < crMt.rightPoint_h()) {
					player.setmRight(false);
				}
				// SOLA GITMASINI ENGELLEMEK ICIN
				if (player.getX_h() == crMt.rightPoint_h() && player.rightPoint_h() > crMt.getX_h()) {
					player.setmLeft(false);
				}
			}
		} else {
			if (player.getY_h() < crMt.bottomPoint_h() && player.bottomPoint_h() > crMt.getY_h()) {
				// SAGA GITMESINI ENGELLEMEK ICIN
				if (player.rightPoint_h() == crMt.getX_h() && player.getX_h() < crMt.rightPoint_h()) {
					player.setmRight(false);
				}
				// SOLA GITMASINI ENGELLEMEK ICIN
				if (player.getX_h() == crMt.rightPoint_h() && player.rightPoint_h() > crMt.getX_h()) {
					player.setmLeft(false);
				}
			}
		}

	}

	public boolean aprcCollide(Content cnt1, Content crMt) {
		// ------------- Z EKSENI Y HAREKETE BAGLI
		if (cnt1.getWidth() < crMt.getWidth()) {
			if ((cnt1.getX() < crMt.rightPoint() && cnt1.getX() > crMt.getX())
					|| (cnt1.rightPoint() < crMt.rightPoint() && cnt1.rightPoint() > crMt.getX())) {
				// Z INDEXINI AYARLAMAK ICIN

				if (cnt1.bottomPoint() >= crMt.getCollideY() && cnt1.getCollideY() < crMt.bottomPoint()) {
					return true;

				} else if (cnt1.getCollideY() <= crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getCollideY()) {
					return true;
				}
			}
		} else {
			if (cnt1.getX() < crMt.rightPoint() && cnt1.rightPoint() > crMt.getX()) {
				// Z INDEXINI AYARLAMAK ICIN
				if (cnt1.getCollideY() <= crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getCollideY()) {
					return true;
				} else if (cnt1.bottomPoint() >= crMt.getCollideY() && cnt1.getCollideY() < crMt.bottomPoint()) {
					return true;
				}
			}
		}

		// ------------- Z EKSENI X HAREKETE BAGLI
		if (cnt1.getHeight() < crMt.getHeight()) {
			if ((cnt1.getCollideY() > crMt.getCollideY() && cnt1.getCollideY() < crMt.bottomPoint())
					|| (cnt1.bottomPoint() < crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getCollideY())) {
				// Z INDEXINI AYARLAMAK ICIN
				if (cnt1.rightPoint() >= crMt.getX() && cnt1.getX() < crMt.rightPoint()) {
					return true;
				}
				if (cnt1.getX() <= crMt.rightPoint() && cnt1.rightPoint() > crMt.getX()) {
					return true;
				}
			}
		} else {
			if (cnt1.getCollideY() < crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getCollideY()) {
				// Z INDEXINI AYARLAMAK ICIN
				if (cnt1.getX() <= crMt.rightPoint() && cnt1.rightPoint() > crMt.getX()) {
					return true;
				}
				if (cnt1.rightPoint() >= crMt.getX() && cnt1.getX() < crMt.rightPoint()) {
					return true;
				}
			}
		}
		return false;

	}

	public boolean zOrder(Content cnt1, Content crMt) {
		// ------------- Z EKSENI Y HAREKETE BAGLI
		if (cnt1.getWidth() < crMt.getWidth()) {
			if ((cnt1.getX() < crMt.rightPoint() && cnt1.getX() > crMt.getX())
					|| (cnt1.rightPoint() < crMt.rightPoint() && cnt1.rightPoint() > crMt.getX())) {
				// Z INDEXINI AYARLAMAK ICIN

				if (cnt1.bottomPoint() >= crMt.getY() && cnt1.getY() < crMt.bottomPoint()) {
					compareZ(cnt1, crMt);
					return true;

				} else if (cnt1.getY() <= crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getY()) {
					compareZ(cnt1, crMt);
					return true;
				}
			}
		} else {
			if (cnt1.getX() < crMt.rightPoint() && cnt1.rightPoint() > crMt.getX()) {
				// Z INDEXINI AYARLAMAK ICIN
				if (cnt1.getY() <= crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getY()) {
					compareZ(cnt1, crMt);
					return true;
				} else if (cnt1.bottomPoint() >= crMt.getY() && cnt1.getY() < crMt.bottomPoint()) {
					compareZ(cnt1, crMt);
					return true;
				}
			}
		}

		// ------------- Z EKSENI X HAREKETE BAGLI
		if (cnt1.getHeight() < crMt.getHeight()) {
			if ((cnt1.getY() > crMt.getY() && cnt1.getY() < crMt.bottomPoint())
					|| (cnt1.bottomPoint() < crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getY())) {
				// Z INDEXINI AYARLAMAK ICIN
				if (cnt1.rightPoint() >= crMt.getX() && cnt1.getX() < crMt.rightPoint()) {
					compareZ(cnt1, crMt);
					return true;
				}
				if (cnt1.getX() <= crMt.rightPoint() && cnt1.rightPoint() > crMt.getX()) {
					compareZ(cnt1, crMt);
					return true;
				}
			}
		} else {
			if (cnt1.getY() < crMt.bottomPoint() && cnt1.bottomPoint() > crMt.getY()) {
				// Z INDEXINI AYARLAMAK ICIN
				if (cnt1.getX() <= crMt.rightPoint() && cnt1.rightPoint() > crMt.getX()) {
					compareZ(cnt1, crMt);
					return true;
				}
				if (cnt1.rightPoint() >= crMt.getX() && cnt1.getX() < crMt.rightPoint()) {
					compareZ(cnt1, crMt);
					return true;
				}
			}
		}
		return false;

	}

	public void damgePlayer(int damage) {
		if (this.player.isAvailable()) {
			this.stats.updateHealth(this.player.getHealth());
		}
		this.player.getDamaged(damage);

	}
	
	public void healPlayer(int damage) {
		this.player.heal(damage);
		this.stats.updateHealth(this.player.getHealth());
	}

	public void compareZ(Content cnt1, Content cnt2) {
		if (cnt1.getY_h() >= cnt2.bottomPoint_h()) {
			if (cnt1.getZ(contentPane) > cnt2.getZ(contentPane)) {
				cnt1.setZ(cnt2.getZ(contentPane), contentPane);
			}
		} else {
			if (cnt1.getZ(contentPane) < cnt2.getZ(contentPane)) {
				cnt1.setZ(cnt2.getZ(contentPane), contentPane);
			}
		}

	}

	public void zOrder() {
		Component[] components = contentPane.getComponents();
		for (int counter = 0; counter < components.length; counter++) {
			for (int counter2 = 0; counter2 < components.length; counter2++) {
				Component mt1 = components[counter];
				Component mt2 = components[counter2];
				if (mt1.getY() + mt1.getHeight() < mt2.getY() + mt2.getHeight()) {
					components[counter] = mt2;
					components[counter2] = mt1;
				}
			}
		}
		for (int counter = components.length - 1; counter > -1; counter--) {
			contentPane.setComponentZOrder(components[counter], -counter + components.length - 1);
		}
		contentPane.setComponentZOrder(background, components.length - 1);
	}

	public void endGame() {
		showGameOver=false;
		gameOver = false;
		player.setAlive(false);
		jFrame.dispose();
	}

	public void addComp(Component comp) {
		this.contentPane.add(comp);
		this.contentPane.setComponentZOrder(comp, 0);
	}

	public Creature getPlayer() {
		return player;
	}

	public void setPlayer(Creature player) {
		this.player = player;
	}

	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(ArrayList<Creature> creatures) {
		this.creatures = creatures;
	}

	public ArrayList<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}




	public ArrayList<Material> getDangers() {
		return dangers;
	}

	public void setDangers(ArrayList<Material> dangers) {
		this.dangers = dangers;
	}

	public JFrame getjFrame() {
		return jFrame;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JLabel getBackground() {
		return background;
	}

	public void setBackground(JLabel background) {
		this.background = background;
	}

	public JLabel getFps() {
		return fps;
	}

	public void setFps(JLabel fps) {
		this.fps = fps;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

}