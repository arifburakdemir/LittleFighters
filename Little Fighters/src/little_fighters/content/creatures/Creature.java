package little_fighters.content.creatures;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import little_fighters.content.Content;

public abstract class Creature extends Content {

	protected JLabel nickName_l = new JLabel();
	protected JLabel HealthBar = new JLabel();
	protected JLabel animation = new JLabel();

	protected boolean alive = true;
	
	protected int health = 100;
	protected int maxHealth = 100;
	protected int maxSpeed = 0;
	protected int crSpeed = 0;

	protected int animCount = 0;

	protected boolean right = false;
	protected boolean left = false;
	protected boolean up = false;
	protected boolean down = false;

	protected boolean mRight = true;
	protected boolean mLeft = true;
	protected boolean mUp = true;
	protected boolean mDown = true;

	protected boolean available = true;
	protected ImageIcon icon = new ImageIcon();

	Thread damageAnim = new Thread(new Runnable() {

		@Override
		public void run() {
			while (alive) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				while (!available) {
					aprc.setVisible(false);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					aprc.setVisible(true);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	});

	public Creature() {
		super();
		setThrough(false);
		nickName_l.setHorizontalAlignment(SwingConstants.CENTER);
		nickName_l.setVerticalAlignment(SwingConstants.CENTER);
		aprc.add(nickName_l);
		aprc.add(HealthBar);
	}

	public void getDamaged(int damage) {

		if (available) {

			this.health -= damage;
			if(this.health <= 0){
				alive = false;
			}

			available = false;
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					available = true;
				}
			}, 1000);
		}

	}
	public void heal(int heal) {
		if(this.health + heal < maxHealth){
			this.health += heal;
		}else{
			this.health = maxHealth;
		}
	}


	public void moveAnimation() {
		if (this.right) {
			if (animation.getX() != -96) {
				animation.setBounds(animation.getX() - 32, -96, 128, 192);
			} else {
				animation.setBounds(0, -96, 128, 192);
			}

		} else if (this.left) {
			if (animation.getX() != -96) {
				animation.setBounds(animation.getX() - 32, -48, 128, 192);
			} else {
				animation.setBounds(0, -48, 128, 192);
			}

		} else if (this.down) {
			if (animation.getX() != -96) {
				animation.setBounds(animation.getX() - 32, 0, 128, 192);
			} else {
				animation.setBounds(0, 0, 128, 192);
			}

		} else if (this.up) {
			if (animation.getX() != -96) {
				animation.setBounds(animation.getX() - 32, -144, 128, 192);
			} else {
				animation.setBounds(0, -144, 128, 192);
			}

		} else {
			animation.setBounds(0, 0, 128, 192);
		}
	}

	@Override
	public int getCollideY() {
		return super.getCollideY() + 10;
	}
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public Thread getDamageAnim() {
		return damageAnim;
	}

	public void setDamageAnim(Thread damageAnim) {
		this.damageAnim = damageAnim;
	}

	public JLabel getNickName_l() {
		return nickName_l;
	}

	public JLabel getAnimation() {
		return animation;
	}

	public void setAnimation(JLabel animation) {
		this.animation = animation;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getAnimCount() {
		return animCount;
	}

	public void setAnimCount(int animCount) {
		this.animCount = animCount;
	}

	public void setNickName_l(JLabel nickName_l) {
		this.nickName_l = nickName_l;
	}

	public String getNickName() {
		return this.nickName_l.getText();
	}

	public void setNickName(String nickName) {
		this.nickName_l.setText(nickName);
	}

	public boolean ismRight() {
		return mRight;
	}

	public void setmRight(boolean mRight) {
		this.mRight = mRight;
	}

	public boolean ismLeft() {
		return mLeft;
	}

	public void setmLeft(boolean mLeft) {
		this.mLeft = mLeft;
	}

	public boolean ismUp() {
		return mUp;
	}

	public void setmUp(boolean mUp) {
		this.mUp = mUp;
	}

	public boolean ismDown() {
		return mDown;
	}

	public void setmDown(boolean mDown) {
		this.mDown = mDown;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public int getCrSpeed() {
		return crSpeed;
	}

	public void setCrSpeed(int crSpeed) {
		this.crSpeed = crSpeed;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public JLabel getHealthBar() {
		return HealthBar;
	}

	public void setHealthBar(JLabel healthBar) {
		HealthBar = healthBar;
	}



}
