package game_engine;

import javax.sound.sampled.Clip;

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

	public void playSound(Clip eventSound) {
		
	}
	
}
