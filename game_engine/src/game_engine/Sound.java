package game_engine;

import java.util.HashMap;

import javax.sound.sampled.Clip;

import game_engine.Constants.Event;

//Sound related data, such as what file to play and at what volume
//Possibly playback speed etc

public class Sound extends Component {
	double volume = 1;
	HashMap<Event, Clip> eventClips;
	
	public Clip getEventSound(Event collision) {
		return null;
	}
}
