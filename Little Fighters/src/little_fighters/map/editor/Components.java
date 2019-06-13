package little_fighters.map.editor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import little_fighters.content.materials.Material;

public class Components {

	private JLabel aprc = new JLabel();
	private int starginX = 0;
	private int mouseX = 0;
	private int currentXPadding = 0;
	private int starginY = 0;
	private int mouseY = 0;
	private int currentYPadding = 0;

	private Components that = this;
	MapEditor mapEditor;
	private boolean admin = false;
	private Material metarial;

	public Components(Components components) {
		this.metarial = Material.copyMaterial(components.getMetarial());
		this.aprc = new JLabel();
		aprc.setBounds(0, 0, components.getAprc().getWidth(), components.getAprc().getHeight());
		aprc.setIcon(components.getAprc().getIcon());
		this.admin = components.isAdmin();
		this.mapEditor = components.getMapEditor();
		addMovable();
	}

	public Components(Material metarial, boolean admin, MapEditor mapEditor) {
		this.metarial = metarial;
		this.aprc = new JLabel();
		aprc.setBounds(metarial.getAprc().getX(), metarial.getAprc().getY(), metarial.getAprc().getWidth(), metarial.getAprc().getHeight());
		aprc.setIcon(metarial.getAprc().getIcon());
		this.admin = admin;
		this.mapEditor = mapEditor;
		addMovable();
	}

	public void addMovable() {
		aprc.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (admin) {
				} else {
					if (mapEditor.isDelete()) {
						mapEditor.removeMetarialFromMap(that);
					} else {
						mouseX = e.getX();
						mouseY = e.getY();
						currentXPadding = mouseX - starginX + currentXPadding;
						currentYPadding = mouseY - starginY + currentYPadding;
						aprc.setBounds(currentXPadding, currentYPadding, aprc.getWidth(), aprc.getHeight());
						metarial.getAprc().setBounds(currentXPadding, currentYPadding, aprc.getWidth(),
								aprc.getHeight());
					}

				}
			}
		});

		aprc.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				mapEditor.zOrder();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				starginX = e.getX();
				starginY = e.getY();
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (admin) {
					Components newComp = new Components(that);
					newComp.setAdmin(false);
					mapEditor.addMetarialToMap(newComp);
					admin = true;
				} else {
					mouseX = e.getX();
					mouseY = e.getY();
				}
			}
		});
	}

	public MapEditor getMapEditor() {
		return mapEditor;
	}

	public void setMapEditor(MapEditor mapEditor) {
		this.mapEditor = mapEditor;
	}

	public Material getMetarial() {
		return metarial;
	}

	public void setMetarial(Material metarial) {
		this.metarial = metarial;
	}

	public int getStarginX() {
		return starginX;
	}

	public void setStarginX(int starginX) {
		this.starginX = starginX;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getCurrentXPadding() {
		return currentXPadding;
	}

	public void setCurrentXPadding(int currentXPadding) {
		this.currentXPadding = currentXPadding;
	}

	public int getStarginY() {
		return starginY;
	}

	public void setStarginY(int starginY) {
		this.starginY = starginY;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public int getCurrentYPadding() {
		return currentYPadding;
	}

	public void setCurrentYPadding(int currentYPadding) {
		this.currentYPadding = currentYPadding;
	}

	public MapEditor getPanel() {
		return mapEditor;
	}

	public void setPanel(MapEditor mapEditor) {
		this.mapEditor = mapEditor;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public JLabel getAprc() {
		return aprc;
	}

	public void setAprc(JLabel aprc) {
		this.aprc = aprc;
	}

}
