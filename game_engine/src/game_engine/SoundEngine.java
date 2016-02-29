package game_engine;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

//Loads and plays sounds

public class SoundEngine {
	
	Timer timer = new Timer();

	private SoundEngine () {
		
	}
	
	public static SoundEngine getInstance () {
		if (Constants.theSoundEngine == null) {
			Constants.theSoundEngine = new SoundEngine();
		}
		return Constants.theSoundEngine;
	}
	
	public void addBackgroundSound (String path) {
		final File backgroundSound = Constants.theLoader.loadSound(path);
		if (backgroundSound !=  null) {
			Thread temp = new Thread(new Runnable () {
				public void run() {
					try {
						Clip sound = AudioSystem.getClip();
						AudioInputStream ais = AudioSystem.getAudioInputStream(backgroundSound);
						sound.open(ais);
						
						sound.loop(Clip.LOOP_CONTINUOUSLY);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			temp.start();
		}
	}

	public void playSound(final File eventSound) {
		if (eventSound != null && timer.getRunTime() > 200) {
			timer.reset();
			Thread temp = new Thread(new Runnable () {
				public void run() {
					try {
						Clip sound = AudioSystem.getClip();
						AudioInputStream ais = AudioSystem.getAudioInputStream(eventSound);
						sound.open(ais);
						
						sound.start();
						while (sound.getMicrosecondLength() > sound.getMicrosecondPosition()) {
						}
						sound.stop();
						sound.flush();
						sound.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			temp.start();
		}
	}
}
