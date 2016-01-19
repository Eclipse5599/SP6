package game_engine;

import java.util.List;

public class GameObject {
	List<Component> components;
	
	boolean isStatic = false;
	int coordinates[][];
	
	public void tick() {
		for (Component c : components) {
			c.tick();
		}
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}
}
