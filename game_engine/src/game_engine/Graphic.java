package game_engine;

import java.awt.image.BufferedImage;

//Contains graphical data based on information in the component class

public class Graphic extends Component {
	private BufferedImage sprite;
	private int height, width;
	
	public Graphic (String imagePath, int height, int width) {
		compType = Constants.ComponentType.graphic;
		this.height = height;
		this.width = width;
		
		if (imagePath != null) {
			sprite = Constants.theLoader.loadImage(imagePath);
		}
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	public int getHeight () {
		return height;
	}
	
	public int getWidth () {
		return width;
	}
}
