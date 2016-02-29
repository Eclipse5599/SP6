package game_engine;

import java.util.ArrayList;

//Uses Components from the component class to create objects used in the game

import java.util.List;

public class GameObject {
	List<Component> components = new ArrayList<Component>();
	
	private GameObject parent = null;
	private ArrayList<GameObject> children = new ArrayList<GameObject>();
	private Transform transform;
	private String name;
	
	public GameObject (String name, GameObject parent, int x, int y) {
		this.name = name;
		this.parent = parent;
		transform = new Transform(x, y);
		transform.setOwner(this);
	}
	
	public GameObject (String name, int x, int y) {
		this.name = name;
		transform = new Transform(x, y);
		transform.setOwner(this);
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
	
	public void addAction (int actionKey, Action a) {
		if (hasComponent(Constants.ComponentType.controller)) {
			GameObjectController control = (GameObjectController)getComponent(Constants.ComponentType.controller);
			control.addActionKey(actionKey, a);
		}
	}
	
	public void addChild (GameObject g) {
		children.add(g);
		g.setParent(this);
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
	
	public boolean hasChildren () {
		return !children.isEmpty();
	}
	
	public boolean hasParent () {
		if (parent != null) {
			return true;
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
	
	public List<GameObject> getChildren () {
		return children;
	}
	
	public GameObject getParent () {
		return parent;
	}
	
	public void setParent (GameObject parent) {
		this.parent = parent;
		for (Component c : components) {
			c.setHasParent(true);
		}
	}
}
