package game_engine;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class FaceChild extends GameObject {
	private int width = 0, height = 0, parentWidth = 0, parentHeight = 0;
	private GameObject parent;
	
	public FaceChild(String name, int key,GameObject parent, int x, int y, int w, int h, int pw, int ph) {
		super(name, parent, x, y);
		this.parent = parent;
		width = w;
		height = h;
		parentWidth = pw; 
		parentHeight = ph;
		addComponent(new GunGraphic("gun.png", w, h));
		addComponent(new GameObjectController(null, KeyEvent.VK_UNDEFINED, KeyEvent.VK_UNDEFINED, KeyEvent.VK_UNDEFINED, KeyEvent.VK_UNDEFINED));
		addAction(key, new ShootAction(20));
	}
	
	@Override
	public void tick (float delta) {
		super.tick(delta);
		if (parent.getFacingDirection() == Constants.Direction.north) {
			getTransform().setX(0);
			getTransform().setY(-height/2-parentHeight/2);
		} else if (parent.getFacingDirection() == Constants.Direction.east) {
			getTransform().setX(width/2+parentWidth/2);
			getTransform().setY(6);
		} else if (parent.getFacingDirection() == Constants.Direction.west) {
			getTransform().setX(-width/2-parentWidth/2);
			getTransform().setY(6);
		}
		setFacingDirection(parent.getFacingDirection());
	}

	public class GunGraphic extends Graphic {
		BufferedImage sprite[] = new BufferedImage[3];
		public GunGraphic(String imagePath, int height, int width) {
			super(imagePath, height, width);
			sprite[0] = Constants.theLoader.loadImage("north_"+imagePath);
			sprite[1] = Constants.theLoader.loadImage("east_"+imagePath);
			sprite[2] = Constants.theLoader.loadImage("west_"+imagePath);
		}

		public BufferedImage getSprite() {
			if (parent.getFacingDirection() == Constants.Direction.north) {
				return sprite[0];
			} else if (parent.getFacingDirection() == Constants.Direction.east) {
				return sprite[1];
			} else if (parent.getFacingDirection() == Constants.Direction.west) {
				return sprite[2];
			} 
			return sprite[0];
		}
	}
}
