package game_engine;

import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;

//Used for testing of components

public class TestGame {
	
	public static void main (String args[]) {
		GameEngine g = new GameEngine(600, 600);
		g.initialize();
		
		GameObject player = createPlayer("Player", 300, 520, 32, 32);
		player.addChild(createStaticObject("Child", -32, -32, 16, 16));
		
		GameObject player2 = createPlayer("Player2", 200, 520, 32, 32, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A);
		
		GameObject floor = createStaticObject("floor", 300, 560, 20, 600);
		GameObject roof = createStaticObject("roof", 300, 450, 20, 50);
		
		g.addObject(player);
		g.addObject(player2);
		g.addObject(floor);
		g.addObject(roof);
		g.gameLoop();
	}

	
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w, int up, int left, int down, int right) {
		GameObject player = new GameObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("test_image.gif", h, w));
		Physics physics2 = new Physics(1.0f);
		player.addComponent(physics2);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics2, up, left, down, right));
		player.addComponent(new Sound ("test_sound.wav", Constants.Event.collision));
		
		return player;
	}
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w) {
		GameObject player = new GameObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("test_image.gif", h, w));
		Physics physics2 = new Physics(1.0f);
		player.addComponent(physics2);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics2));
		player.addComponent(new Sound ("test_sound.wav", Constants.Event.collision));
		
		return player;
	}
	
	public static GameObject createStaticObject(String name, int startPosX, int startPosY, int h, int w) {
		GameObject object = new GameObject(name, startPosX, startPosY);
		
		object.addComponent(new Graphic(null, h, w));
		object.addComponent(new Collider(h, w));
		
		return object;
	}
}
