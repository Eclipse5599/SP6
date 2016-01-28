package game_engine;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameObjectController extends Component {
	
	private int up = KeyEvent.VK_UP, right = KeyEvent.VK_RIGHT, down = KeyEvent.VK_DOWN, left = KeyEvent.VK_LEFT; //keycodes
	private ArrayList<Integer> input;
	
	
	public GameObjectController () {
		compType = Constants.ComponentType.controller;
	}

	public void tick () {
		if (input != null) {
			for (int keyInput : input) {
				if (keyInput == up) {
					System.out.println("UP");
				} else if (keyInput == right) {
					System.out.println("RIGHT");
				} else if (keyInput == down) {
					System.out.println("DOWN");
				} else if (keyInput == left) {
					System.out.println("LEFT");
				}
			}
			input = null;
		}
	}
	
	public void input (ArrayList<Integer> pressedKeys) {
		input = pressedKeys;
	}
}
