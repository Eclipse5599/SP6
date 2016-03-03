package game_engine;

public class PlayerObject extends GameObject {

	private int health = 500;
	public PlayerObject(String name, int x, int y) {
		super(name, x, y);
	}
	
	@Override
	public void doEvent(Constants.Event event) {
		if (event == Constants.Event.hit) {
			health--;
			if (health <= 0) {
				Constants.theGameEngine.removeObject(this);
			}
		}
	}
}
