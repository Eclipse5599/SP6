package game_engine;

//Loads and plays sounds

public class SoundEngine {

	private SoundEngine () {
		
	}
	
	public static SoundEngine getInstance () {
		if (Constants.theSoundEngine == null) {
			Constants.theSoundEngine = new SoundEngine();
		}
		return Constants.theSoundEngine;
	}
	
}
