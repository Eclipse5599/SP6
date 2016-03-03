package game_engine;

import game_engine.Constants.Event;

import java.net.URL;
import java.util.HashMap;

//Sound related data, such as what file to play and at what volume
//Possibly playback speed etc

public class Sound extends Component {
	double volume = 1;
	HashMap<Event, URL> eventClips = new HashMap<Event, URL>();
	
	public Sound (String path, Event event) {
		compType = Constants.ComponentType.sound;
		URL temp = Constants.theLoader.loadSound(path);
		if (temp != null) {
			eventClips.put(event, temp);
		}
	}
	
	public URL getEventSound(Event event) {
		return eventClips.get(event);
	}
}
