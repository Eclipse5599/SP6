package game_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

public class Sprite extends JComponent {

	Graphic graphicComponent;
	Image sprite;
	
	public Sprite (Graphic graphicComponent) {
		super();
		this.graphicComponent = graphicComponent;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Transform temp = graphicComponent.getOwnerTransform();
		if (sprite != null) {
			g.drawImage(sprite, temp.getX(), temp.getY(), null);
		} else {
			g.setColor(Color.red);
			g.fillRect(temp.getX(), temp.getY(), 32, 32);
		}
	}

	
	
}
