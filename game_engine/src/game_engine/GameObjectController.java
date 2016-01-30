package game_engine;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameObjectController extends Component {
	
	private int up = KeyEvent.VK_UP, right = KeyEvent.VK_RIGHT, down = KeyEvent.VK_DOWN, left = KeyEvent.VK_LEFT; //keycodes
	private ArrayList<Integer> input = new ArrayList<Integer>();
	private Physics physicsComponent;
	
	public GameObjectController (Physics physicsComponent) {
		this.physicsComponent = physicsComponent;
		compType = Constants.ComponentType.controller;
	}
	
	public void tick (double delta) {
		if (!input.isEmpty()) {
			if (input.contains(up)) {
				physicsComponent.moveUp(delta);
			} 
			if (input.contains(right)) {
				physicsComponent.moveRight(delta);
			} 
			if (input.contains(down)) {
				physicsComponent.moveDown(delta);
			} 
			if (input.contains(left)) {
				physicsComponent.moveLeft(delta);
			}
		}
	}
	
	public void addInput (Integer pressedKey) {
		input.add(pressedKey);
	}
	
	public void removeInput (Integer pressedKey) {
		input.remove(pressedKey);
	}
}
