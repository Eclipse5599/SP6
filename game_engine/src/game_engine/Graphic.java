package game_engine;

//Contains graphical data based on information in the component class

import javax.swing.*;
//import java.awt.event.*;

public class Graphic extends Component {
	
	private Sprite sprite;
	
	public Graphic (GameObject owner) {
		compType = Constants.ComponentType.graphic;
		sprite = new Sprite(this);
	}
	
	public void tick () {
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
