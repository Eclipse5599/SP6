import game_engine.*;
import java.awt.event.KeyEvent;

//Used for testing of components

public class TestGame {
	
	public static void main (String args[]) {
		GameEngine g = new GameEngine(600, 600);
		g.initialize();
		g.setGravityType(Constants.GravityType.none);
		
		GameObject player = createPlayer("Player", 300, 520, 32, 32);
		player.addChild(createChildObject("LeftChild", -32, 16, 32, 16));
		player.addChild(createChildObject("RightChild", 32, 16, 32, 16));
		player.addAction(KeyEvent.VK_SPACE, new ShootAction());
		
		GameObject player2 = createPlayer("Player2", 200, 520, 32, 32, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A);
		
		g.addObject(player);
		g.addObject(player2);
		g.gameLoop();
	}

	
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w, int up, int left, int down, int right) {
		GameObject player = new GameObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("test_image.gif", h, w));
		Physics physics = new Physics(1.0f);
		player.addComponent(physics);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics, up, left, down, right));
		player.addComponent(new Sound ("test_sound.wav", Constants.Event.collision));
		
		return player;
	}
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w) {
		GameObject player = new GameObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("test_image.gif", h, w));
		Physics physics = new Physics(1.0f);
		player.addComponent(physics);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics));
		player.addComponent(new Sound ("test_sound.wav", Constants.Event.collision));
		
		return player;
	}
	
	public static GameObject createStaticObject(String name, int startPosX, int startPosY, int h, int w) {
		GameObject object = new GameObject(name, startPosX, startPosY);
		
		object.addComponent(new Graphic(null, h, w));
		object.addComponent(new Collider(h, w));
		
		return object;
	}
	
	public static GameObject createChildObject(String name, int startPosX, int startPosY, int h, int w) {
		GameObject object = new GameObject(name, startPosX, startPosY);
		
		object.addComponent(new Graphic(null, h, w));
//		Physics physics = new Physics(1.0f);
//		object.addComponent(physics);
//		object.addComponent(new Collider(h, w));
		
		return object;
	}
	
	public static class ShootAction extends Action {
		@Override
		public void doAction () {
			
		}
	}
}
