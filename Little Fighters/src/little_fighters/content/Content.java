package little_fighters.content;



import javax.swing.JLabel;
import javax.swing.JPanel;

import little_fighters.LittleFighters;

public abstract class Content extends LittleFighters{

	
	protected JLabel aprc = new JLabel();
	protected JLabel hold = new JLabel();
	
	private boolean through = false;
	
	public Content(){
		aprc.add(getHold());
	}
	
	public void setHold(JLabel hold) {
		this.hold = hold;
	}

	public JLabel getHold() {
		return hold;
	}

	public int getX_h() {
		return getAprc().getX()+hold.getX();
	}

	public int getY_h() {
		return getY()+hold.getY();
	}

	public int rightPoint_h() {
		return getAprc().getX() + getHold().getX() + getHold().getWidth();
	}

	public int bottomPoint_h(){
		return getAprc().getY() + getHold().getY() +  getHold().getHeight();
	}
	public int getCollideY(){
		return getY();
	}
	public int getHeight_h() {
		return hold.getHeight();
	}
	public int getWidth_h() {
		return hold.getWidth();
	}
	public JLabel getAprc() {
		return aprc;
	}

	public int getX() {
		return aprc.getX();
	}

	public void setAprc(JLabel aprc) {
		this.aprc = aprc;
	}

	public int getY() {
		return aprc.getY();
	}

	public int rightPoint() {
		return getX()+getWidth();
	}

	public int bottomPoint(){
		return getY()+getHeight();
	}

	public int getHeight() {
		return aprc.getHeight();
	}
	public int getWidth() {
		return aprc.getWidth();
	}

	public void setX(int x) {
		aprc.setBounds(x, getY(), getWidth(), getHeight());
	}

	public void setY(int y) {
		aprc.setBounds(getX(), y, getWidth(), getHeight()); 
	}

	public void setHeight(int height) {
		aprc.setBounds(getX(), getY(), getWidth(), height);
	}
	public void setWidth(int width) {
		aprc.setBounds(getX(), getY(), width, getHeight());
	}
	public int getZ(JPanel contentPane) {
		return contentPane.getComponentZOrder(getAprc());
	}
	public void setZ(int z,JPanel contentPane) {
		contentPane.setComponentZOrder(getAprc(), z);
	}
	public boolean isThrough() {
		return through;
	}
	public void setThrough(boolean through) {
		this.through = through;
	}
	
	

}
