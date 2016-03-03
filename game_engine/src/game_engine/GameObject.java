package game_engine;

import java.util.concurrent.ConcurrentLinkedQueue;

public class GameObject {
	private ConcurrentLinkedQueue<Component> components = new ConcurrentLinkedQueue<Component>();
	private ConcurrentLinkedQueue<GameObject> children = new ConcurrentLinkedQueue<GameObject>();
	
	private GameObject parent = null;
	private Transform transform;
	private String name;
	private Constants.Direction facing = Constants.Direction.east;  
	private int width = 0, height = 0;
	
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
		if (c.getType() == Constants.ComponentType.graphic) {
			width = ((Graphic)c).getWidth();
			height = ((Graphic)c).getHeight();
		}
	}
	
	public void addAction (int actionKey, Action a) {
		if (hasComponent(Constants.ComponentType.controller)) {
			a.setOwner(this);
			GameObjectController control = (GameObjectController)getComponent(Constants.ComponentType.controller);
			control.addActionKey(actionKey, a);
		}
	}
	
	public void addChild (GameObject g) {
		children.add(g);
		g.setParent(this);
	}
	
	public void doEvent (Constants.Event event) {
		if (event == Constants.Event.collision) {
			if (hasComponent(Constants.ComponentType.sound)) {
				Sound soundComponent = (Sound) getComponent(Constants.ComponentType.sound);
				Constants.theSoundEngine.playSound(soundComponent.getEventSound(event));
			}
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
	
	public void handleCollisionExtras (GameObject other) {
		
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
	
	public ConcurrentLinkedQueue<GameObject> getChildren () {
		return children;
	}
	
	public GameObject getParent () {
		return parent;
	}
	
	public Constants.Direction getFacingDirection () {
		return facing;
	}
	
	public int getWidth () {
		return width;
	}
	public int getHeight () {
		return height;
	}

	//Setters
	public void setFacingDirection (Constants.Direction dir) {
		facing = dir;
	}
	
	public void setParent (GameObject parent) {
		this.parent = parent;
		for (Component c : components) {
			c.setHasParent(true);
		}
	}
}
