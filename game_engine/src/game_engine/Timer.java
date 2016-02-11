package game_engine;

public class Timer {

	private long startTime;
	private long pauseTime;
	private long checkTime;
	
	private boolean paused = false;
	
	public Timer () {
		startTime = System.currentTimeMillis();
		checkTime = startTime;
	}
	
	public Timer (long millisToStartWith) {
		startTime = System.currentTimeMillis() - millisToStartWith;
		checkTime = startTime;
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
	
	public long getDeltaMillis () {
		long delta = ((System.currentTimeMillis() - checkTime));
		checkTime = System.currentTimeMillis();
		return delta;
	}
	
	public float getDeltaSeconds () {
		float delta = (getDeltaMillis() / 1000f);
		return delta;
	}
	
	public float getRunTime () {
		long runTime = ((System.currentTimeMillis() - startTime));
		return runTime;
	}
}
