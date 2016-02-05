package game_engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

//Renders graphical components to the screen
//For later stages: decide what components to render to avoid rendering assets that can't be seen

import javax.swing.JPanel;
//import java.awt.event.*;

public class Renderer extends JPanel {
	
	private static final long serialVersionUID = -7904606405657475749L;
	private ArrayList<Graphic> graphicComponents = new ArrayList<Graphic>();
	
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
				g.drawImage(sprite, (int)temp.getX(), (int)temp.getY(), null);
			} else {
				g.setColor(Color.red);
				g.fillRect((int)temp.getX(), (int)temp.getY(), 32, 32);
			}
		}
	}
}
