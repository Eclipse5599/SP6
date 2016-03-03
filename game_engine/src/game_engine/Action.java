package game_engine;

public abstract class Action {
	private Timer frequency;
	private long frequencyMillis;
	private static final int STANDARD_FREQUENCY = 200;
	protected GameObject owner;
	
	public Action () {
		frequency = new Timer(STANDARD_FREQUENCY);
		this.frequencyMillis = STANDARD_FREQUENCY;
	}
	
	public Action (long frequencyMillis) {
		frequency = new Timer(frequencyMillis);
		this.frequencyMillis = frequencyMillis;
	}
	
	public boolean readyToUse () {
		if (frequency.getRunTime() >= frequencyMillis) {
			frequency.reset();
			return true;
		}
		return false;
	}
	
	public void doAction() {
		if (readyToUse()) {
			
		}
	}
	
	public GameObject getOwner () {
		return owner;
	}
	
	public void setOwner (GameObject owner) {
		this.owner = owner;
	}
}
