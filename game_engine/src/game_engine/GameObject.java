package game_engine;

import java.util.ArrayList;

//Uses Components from the component class to create objects used in the game

import java.util.List;

public class GameObject {
	List<Component> components = new ArrayList<Component>();
	
	private GameObject parent;
	private boolean isStatic;
	private boolean collideable;
	private Transform transform;
	
	public GameObject(GameObject parent, int x, int y, boolean isstatic, boolean iscollideable) {
		this.parent = parent;
		transform = new Transform(x, y);
		isStatic = isstatic;
		collideable = iscollideable;
	}
	
	public GameObject(int x, int y, boolean isstatic, boolean iscollideable) {
		transform = new Transform(x, y);
		isStatic = isstatic;
		collideable = iscollideable;
	}
	
	public void tick() {
		for (Component c : components) {
			c.tick();
		}
	}

	public void addComponent(Component c) {
		components.add(c);
		c.setOwner(this);
	}
	
	public boolean hasComponent(Constants.ComponentType type) {
		for (Component comp : components) {
			if (comp.getType() == type) {
				return true;
			}
		}
		return false;
	}
	
	//Getters
	public boolean getIsStatic() {
		return isStatic;
	}
	
	public boolean getCollideable() {
		return collideable;
	}
	
	public Component getComponent(Constants.ComponentType type) {
		for (Component comp : components) {
			if (comp.getType() == type) {
				return comp;
			}
		}
		return null;
	}
	
	public Transform getTransform() {
		return transform;
	}
	
	//Setters
	public void setIsStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public void setCollideable(boolean collideable) {
		this.collideable = collideable;
	}
}
