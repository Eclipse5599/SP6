package game_engine;

import java.util.List;

public class GameObject {
	List<Component> components;
	
	public boolean isStatic;
	public boolean collideable;
	public int xCoord;
	public int yCoord;
	
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
	
	public void addComponent(Component c) {
		components.add(c);
	}
}
