package game_engine;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameObjectController extends Component {
	
	private int up = KeyEvent.VK_UP, right = KeyEvent.VK_RIGHT, down = KeyEvent.VK_DOWN, left = KeyEvent.VK_LEFT; //keycodes
	private ArrayList<Integer> input = new ArrayList<Integer>();
	private Physics physicsComponent;
	
	public GameObjectController (Physics physicsComponent) {
		compType = Constants.ComponentType.controller;
		this.physicsComponent = physicsComponent;
	}
	
	public GameObjectController (Physics physicsComponent, int keyCodeUp, int keyCodeRight, int keyCodeDown, int keyCodeLeft) {
		compType = Constants.ComponentType.controller;
		this.physicsComponent = physicsComponent;
		up = keyCodeUp;
		right = keyCodeRight;
		down = keyCodeDown;
		left = keyCodeLeft;
	}
	
	public void tick (float delta) {
		if (!input.isEmpty()) {
			if (input.contains(up)) {
				physicsComponent.moveUp();
			} 
			if (input.contains(right)) {
				physicsComponent.moveRight();
			} 
			if (input.contains(down)) {
				physicsComponent.moveDown();
			} 
			if (input.contains(left)) {
				physicsComponent.moveLeft();
			}
		}
	}
	
	public void addInput (Integer pressedKey) {
		if (!input.contains(pressedKey)) {
			input.add(pressedKey);
		}
	}
	
	public void removeInput (Integer pressedKey) {
		if (input.contains(pressedKey)) {
			input.remove(pressedKey);
		}
	}
}
