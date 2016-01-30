package game_engine;

import java.awt.Image;

//Contains graphical data based on information in the component class

import javax.swing.*;
//import java.awt.event.*;

public class Graphic extends Component {
	
	//private Sprite sprite;
	private Image sprite;
	
	public Graphic (String imagePath) {
		compType = Constants.ComponentType.graphic;
		//spirte = Loader.loadImage(imagePath);
	}
	
	public void tick (double delta) {
		
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	//public Sprite getSprite() {
	//	return sprite;
	//}
}
