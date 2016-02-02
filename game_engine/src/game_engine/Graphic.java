package game_engine;

import java.awt.image.BufferedImage;

//Contains graphical data based on information in the component class

public class Graphic extends Component {
	
	//private Sprite sprite;
	private BufferedImage sprite;
	
	public Graphic (String imagePath) {
		compType = Constants.ComponentType.graphic;
		if (imagePath != null) {
			sprite = Loader.loadImage(imagePath);
		}
	}
	
	public void tick (float delta) {
		
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	//public Sprite getSprite() {
	//	return sprite;
	//}
}
