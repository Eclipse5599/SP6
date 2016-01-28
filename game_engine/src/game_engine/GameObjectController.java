package game_engine;

public class GameObjectController extends Component {
	private int up, right, down, left; //keycodes
	
	public GameObjectController () {
		compType = Constants.ComponentType.controller;
	}

	public void input (int pressedKeys[]) {
		
	}
}
