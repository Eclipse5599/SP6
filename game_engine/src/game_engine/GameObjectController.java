package game_engine;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class GameObjectController extends Component {
	
	private int up = KeyEvent.VK_UP, right = KeyEvent.VK_RIGHT, down = KeyEvent.VK_DOWN, left = KeyEvent.VK_LEFT; //keycodes
	private ArrayList<Integer> input = new ArrayList<Integer>();
	private HashMap<Integer, Action> actionKeys = new HashMap<Integer, Action>(); 
	private Physics physicsComponent;

	private long jumpDuration = 800;
	private Timer jumpTimer = new Timer(jumpDuration); 
	private boolean jumpEnabled = true;
	
	private Vector2 upForce = new Vector2(0, -200), rightForce = new Vector2(100, 0), downForce = new Vector2(0, 100), leftForce = new Vector2(-100, 0);
	
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
		if (physicsComponent != null) {
			if (input.contains(up)) {
				if (physicsComponent.isGrounded() && jumpEnabled || !jumpEnabled) {
					jumpTimer.reset();
				}
				if (!isJumping()) {
					physicsComponent.removeForce(upForce);
				} else {
					physicsComponent.addForce(upForce);					
				}
				owner.setFacingDirection(Constants.Direction.north);
			} else {
				physicsComponent.removeForce(upForce);
			}
			if (input.contains(right)) {
				physicsComponent.addForce(rightForce);
				owner.setFacingDirection(Constants.Direction.east);
			}  else {
				physicsComponent.removeForce(rightForce);
			}
			if (input.contains(down)) {
				physicsComponent.addForce(downForce);
			}  else {
				physicsComponent.removeForce(downForce);
			}
			if (input.contains(left)) {
				owner.setFacingDirection(Constants.Direction.west);
				physicsComponent.addForce(leftForce);
			} else {
				physicsComponent.removeForce(leftForce);
			}
		}
		for (Integer aActionKey : actionKeys.keySet()) {
			if (input.contains(aActionKey)) {
				actionKeys.get(aActionKey).doAction();
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

	public void addActionKey(int actionKey, Action a) {
		if (!actionKeys.containsKey(actionKey) && !actionKeys.containsValue(a)) {
			actionKeys.put(actionKey, a);
		}
	}
	public boolean isJumping () {
		if (jumpTimer.getRunTime() < jumpDuration) {
			return true;
		}
		return false;
	}

	public boolean getJumpEnabled () {
		return jumpEnabled;
	}
	
	public void setJumpEnabled (boolean state) {
		jumpEnabled = state;
	}
}
