package game_engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//Handles inputs, keyboard mapping etc to allow the player to control the game

public class InputHandler implements KeyListener {

	private ArrayList<GameObjectController> controllers = new ArrayList<GameObjectController>();
	public ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
	
	public void checkInputToControllers () {
		if (!pressedKeys.isEmpty()) {
			for (GameObjectController controller : controllers) {
				controller.input(null);
			}
		}
	}
	
	public void addController (GameObjectController newController) {
		controllers.add(newController);
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Integer key = e.getKeyCode();
		System.out.println(key);
		if (!pressedKeys.contains(key)) {
			pressedKeys.add(key);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Integer key = e.getKeyCode();
		if (pressedKeys.contains(key)) {
			pressedKeys.remove(key);
		}
	}	
}
