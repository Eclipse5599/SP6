package game_engine;

//Uses Components from the component class to create objects used in the game

import java.util.List;

public class GameObject {
	List<Component> components;
	
	private boolean isStatic;
	private boolean collideable;
	private int xCoord;
	private int yCoord;
	
	public GameObject(int x, int y, boolean isstatic, boolean iscollideable) {
		xCoord = x;
		yCoord = y;
		isStatic = isstatic;
		collideable = iscollideable;
	}
	
	public void tick() {
		for (Component c : components) {
			c.tick();
		}
	}
	
	public boolean getIsStatic() {
		return isStatic;
	}
	
	public void setIsStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public boolean getCollideable() {
		return collideable;
	}
	
	public void setCollideable(boolean collideable) {
		this.collideable = collideable;
	}
	
	//Eventually a seperate class for Coords
	public int getXCoord() {
		return xCoord;
	}
	
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}
}
