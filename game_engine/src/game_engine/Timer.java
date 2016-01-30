package game_engine;

public class Timer {

	private long startTime;
	private long pauseTime;
	
	private boolean paused = false;
	
	public Timer () {
		startTime = System.currentTimeMillis();
	}
	
	public void reset () {
		startTime = System.currentTimeMillis();
	}
	
	public void pause() {
		if (!paused) {
			pauseTime = System.currentTimeMillis();
			paused = true;
		}
	}
	
	public void unPause () {
		if (paused) {
			startTime += pauseTime;
			pauseTime = 0;
			paused = false;
		}
	}
	
	public double getDeltaMillis () {
		double delta = ((System.currentTimeMillis() - startTime));
		return delta;
	}
	
	public double getDeltaSeconds () {
		double delta = ((System.currentTimeMillis() - startTime) / 1000);
		return delta;
	}
}
