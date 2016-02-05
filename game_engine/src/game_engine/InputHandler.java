package game_engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//Handles inputs, keyboard mapping etc to allow the player to control the game

public class InputHandler implements KeyListener {

	private ArrayList<GameObjectController> controllers = new ArrayList<GameObjectController>();
	
	private InputHandler () {

	}
	
	public static InputHandler getInstance () {
		if (Constants.theInputHandler == null) {
			Constants.theInputHandler = new InputHandler();
		}
		return Constants.theInputHandler;
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
		for (GameObjectController controller : controllers) {
			controller.addInput(key);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Integer key = e.getKeyCode();
		for (GameObjectController controller : controllers) {
			controller.removeInput(key);
		}
	}	
}
