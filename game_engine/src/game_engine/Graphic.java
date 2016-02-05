package game_engine;

import java.awt.image.BufferedImage;

//Contains graphical data based on information in the component class

public class Graphic extends Component {
	private BufferedImage sprite;
	
	public Graphic (String imagePath) {
		compType = Constants.ComponentType.graphic;
		if (imagePath != null) {
			sprite = Constants.theLoader.loadImage(imagePath);
		}
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
}
