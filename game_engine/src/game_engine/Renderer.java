package game_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JPanel;

public class Renderer extends JPanel {
	
	private static final long serialVersionUID = -7904606405657475749L;
	private ConcurrentLinkedQueue<Graphic> graphicComponents = new ConcurrentLinkedQueue<Graphic>();
	
	private Renderer() {
		
	}

	public static Renderer getInstance () {
		if (Constants.theRenderer == null) {
			Constants.theRenderer = new Renderer();
		}
		return Constants.theRenderer;
	}
	
	
	public void addGraphicComponent(Graphic g) {
		graphicComponents.add(g);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Graphic graphic : graphicComponents) {
			Image sprite = graphic.getSprite();
			Transform temp = graphic.getOwnerTransform();
			if (sprite != null) {
				//g.drawImage(sprite, (int)temp.getX() - graphic.getWidth()/2, (int)temp.getY() - graphic.getHeight()/2, (int)temp.getX() + graphic.getWidth()/2, (int)temp.getY() + graphic.getHeight()/2, 0, 0, graphic.getWidth(), graphic.getHeight(), null);
				g.drawImage(sprite, (int)temp.getX() - graphic.getWidth()/2, (int)temp.getY() - graphic.getHeight()/2, null);
			} else {
				g.setColor(Color.red);
				g.fillRect((int)temp.getX() - graphic.getWidth()/2, (int)temp.getY() - graphic.getHeight()/2, graphic.getWidth(), graphic.getHeight());
			}
		}
	}

	public void removeGraphicComponent(Graphic component) {
		graphicComponents.remove(component);
	}
}
