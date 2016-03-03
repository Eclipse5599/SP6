package game_engine;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.SourceDataLine;

//Loads and plays sounds

public class SoundEngine {
	private int frequency = 400;
	private ConcurrentHashMap<URL, Timer> soundTimers = new ConcurrentHashMap<URL, Timer>();
	private boolean exit = false;
	
	private SoundEngine () {
		
	}
	
	public static SoundEngine getInstance () {
		if (Constants.theSoundEngine == null) {
			Constants.theSoundEngine = new SoundEngine();
		}
		return Constants.theSoundEngine;
	}
	
	public void addBackgroundSound (String path) {
		final URL backgroundSound = Constants.theLoader.loadSound(path);
		if (backgroundSound !=  null) {
				Thread temp = new Thread(new Runnable () {
					public void run() {
						while (!exit) {
							try {
								final AudioInputStream in = getAudioInputStream(backgroundSound);
								final AudioFormat outFormat = getOutFormat (in.getFormat());
								final Info info = new Info(SourceDataLine.class, outFormat);
								
								final SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
								
								if (line != null) {
									line.open(outFormat);
									line.start();
									stream(getAudioInputStream(outFormat, in), line);
									line.drain();
									line.stop();
									
								}
							} catch (Exception e) {
								e.printStackTrace();
								break;
							}
						}
					}
				});
				temp.start();
		}
	}

	public void playSound(final URL url) {
		if (url != null && !soundTimers.containsKey(url)) {
			soundTimers.put(url, new Timer(frequency));
		}
		if (soundTimers.containsKey(url) && soundTimers.get(url).getRunTime() > frequency) {
			soundTimers.get(url).reset();
			if (url != null) {
				Thread temp = new Thread(new Runnable () {
					public void run() {
						try {
							final AudioInputStream in = getAudioInputStream(url);
							final AudioFormat outFormat = getOutFormat (in.getFormat());
							final Info info = new Info(SourceDataLine.class, outFormat);
							
							final SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
							
							if (line != null) {
								line.open(outFormat);
								line.start();
								stream(getAudioInputStream(outFormat, in), line);
								line.drain();
								line.stop();
								
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				temp.start();
			}
		}
	}
	
	public void exit () {
		exit = true;
	}
	
	//https://odoepner.wordpress.com/2013/07/19/play-mp3-or-ogg-using-javax-sound-sampled-mp3spi-vorbisspi/
	private AudioFormat getOutFormat (AudioFormat inFormat) {
		final int ch = inFormat.getChannels();
		
		final float rate = inFormat.getSampleRate();
		return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, rate, 16, ch, ch*2, rate, false);
	}
	private void stream (AudioInputStream in, SourceDataLine line) throws IOException {
		final byte[] buffer = new byte[4096];
		for (int n = 0 ; n != -1 ; n = in.read(buffer, 0, buffer.length)) {
			if (exit) {
				break;
			}
			line.write(buffer, 0, n);
		}
	}
}
