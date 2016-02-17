package game_engine;

import java.util.ArrayList;

//Uses Components from the component class to create objects used in the game

import java.util.List;

public class GameObject {
	List<Component> components = new ArrayList<Component>();
	
	private GameEngine gameEngine;
	private GameObject parent;
	private ArrayList<GameObject> children;
	private Transform transform;
	private String name;
	
	public GameObject (GameEngine game, String name, GameObject parent, int x, int y) {
		gameEngine = game;
		this.name = name;
		this.parent = parent;
		transform = new Transform(x, y);
	}
	
	public GameObject (GameEngine game, String name, int x, int y) {
		gameEngine = game;
		this.name = name;
		transform = new Transform(x, y);
	}
	
	public void tick (float delta) {
		for (Component c : components) {
			c.tick(delta);
		}
	}

	public void addComponent (Component c) {
		components.add(c);
		c.setOwner(this);
	}
	
	public void doEvent (Constants.Event event) {
		if (hasComponent(Constants.ComponentType.sound)) {
			Sound soundComponent = (Sound) getComponent(Constants.ComponentType.sound);
			Constants.theSoundEngine.playSound(soundComponent.getEventSound(event));
		}
	}
	
	public boolean hasComponent (Constants.ComponentType type) {
		for (Component comp : components) {
			if (comp.getType() == type) {
				return true;
			}
		}
		return false;
	}
	
	//Getters
	public String getName () {
		return name;
	}
	
	public Component getComponent (Constants.ComponentType type) {
		for (Component comp : components) {
			if (comp.getType() == type) {
				return comp;
			}
		}
		return null;
	}
	
	public Transform getTransform () {
		return transform;
	}
	
	public GameEngine getEngine () {
		return gameEngine;
	}
}
