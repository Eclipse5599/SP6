package game_engine;


public class ShotObject extends game_engine.GameObject {

	private Physics physicsComponent;
	private GameObject owner;
	private Timer lifeTimer = new Timer();
	private long life;
	
	public ShotObject(String name, GameObject parent, int x, int y, int w, int h, Constants.Direction face, long life) {
		super(name, x, y);
		this.life = life;
		owner = parent;
		setFacingDirection(face);
		physicsComponent = new Physics(0.5f);
		physicsComponent.setGravityException(true);
		Collider col = new Collider(w, h);
		col.setIsTrigger(true);
		
		addComponent(physicsComponent);
		addComponent(col);
		addComponent(new Graphic("shot.png", w, h));
	}

	@Override
	public void tick (float delta) {
		if (lifeTimer.getRunTime() >= life) {
			Constants.theGameEngine.removeObject(this);
		} else {
			super.tick(delta);
			if (getFacingDirection() == Constants.Direction.north) {
				physicsComponent.addForce(new Vector2(0, -100));
			} else if (getFacingDirection() == Constants.Direction.east) {
				physicsComponent.addForce(new Vector2(100, 0));
			} else if (getFacingDirection() == Constants.Direction.west) {
				physicsComponent.addForce(new Vector2(-100, 0));
			}
		}
	}
	
	@Override
	public void handleCollisionExtras (GameObject other) {
		if (other != owner) {
			other.doEvent(Constants.Event.hit);
			if (other.hasComponent(Constants.ComponentType.physics)) {
				Vector2 sentForce = new Vector2(physicsComponent.getVelocity());
				sentForce.multiply(physicsComponent.getMass());
				((Physics)other.getComponent(Constants.ComponentType.physics)).addForce(sentForce);
			}
			Constants.theGameEngine.removeObject(this);
		}
	}
}
