package game_engine;


public class ShotObject extends game_engine.GameObject {

	private Physics physicsComponent;
	private GameObject owner;
	private Timer lifeTimer = new Timer();
	private long life;
	
	public ShotObject(String name, GameObject parent, int x, int y, Constants.Direction face, long life) {
		super(name, x, y);
		this.life = life;
		owner = parent;
		setFacingDirection(face);
		physicsComponent = new ShotPhysics(0.5f);
		physicsComponent.setGravityException(true);
		physicsComponent.setJumpEnabled(false);
		addComponent(physicsComponent);
		addComponent(new Graphic(null, 8, 8));
	}

	@Override
	public void tick (float delta) {
		if (lifeTimer.getRunTime() >= life) {
			Constants.theGameEngine.removeObject(this);
		} else {
			super.tick(delta);
			if (physicsComponent == null) {
				physicsComponent = (Physics)getComponent(Constants.ComponentType.physics);
			}
			if (getFacingDirection() == Constants.Direction.north) {
				physicsComponent.moveUp();
			} else if (getFacingDirection() == Constants.Direction.east) {
				physicsComponent.moveRight();
			} else if (getFacingDirection() == Constants.Direction.west) {
				physicsComponent.moveLeft();
			}
		}
	}

	private class ShotPhysics extends Physics {

		public ShotPhysics(float mass) {
			super(mass);
			setMaxVelocity(500f);
			setAcceleration(2000f);
		}
		
	}
}
