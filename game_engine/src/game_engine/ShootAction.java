package game_engine;

public class ShootAction extends Action {
	int shotWidth = 8, shotHeight = 8;
	public ShootAction () {
		super();
	}
	public ShootAction (long frequencyMillis) {
		super(frequencyMillis);
	}
	
	@Override
	public void doAction () {
		if (readyToUse()) {
			Transform ownerTransform = owner.getTransform();
			int x = (int)ownerTransform.getX(), y = (int)ownerTransform.getY();
			Constants.Direction face = owner.getFacingDirection();
			if (face == Constants.Direction.north) {
				y -= owner.getHeight()-shotHeight;
			} else if (face == Constants.Direction.east) {
				x += owner.getWidth()-shotWidth;
			} else if (face == Constants.Direction.west) {
				x -= owner.getWidth()-shotWidth;
			}
			GameObject newO = new ShotObject("Shot", owner, x, y, shotWidth, shotHeight, face, 1000);
			Constants.theGameEngine.addObject(newO);
		}
	}
}