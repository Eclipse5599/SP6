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
	
	private ArrayList<Graphic> graphicComponents = new ArrayList<Graphic>();
	
	public Renderer() {
		
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
				g.drawImage(sprite, temp.getX(), temp.getY(), null);
			} else {
				g.setColor(Color.red);
				g.fillRect(temp.getX(), temp.getY(), 32, 32);
			}
		}
	}
}
