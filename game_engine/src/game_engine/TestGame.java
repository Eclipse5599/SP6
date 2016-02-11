package game_engine;

import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;

//Used for testing of components

public class TestGame {
	
	public static void main (String args[]) {
		GameEngine g = new GameEngine(600, 600);
		g.initialize();
		
		GameObject testObject = new GameObject(g, "Player" ,20, 20); //Create a GameObject at coordinate (0, 0), is static and is collideable.
		
		testObject.addComponent(new Graphic("test_image.gif", 32, 32));
		Physics physics = new Physics(1.0f);
		testObject.addComponent(physics);
		testObject.addComponent(new Collider(32, 32));
		testObject.addComponent(new GameObjectController(physics));
		
		
		GameObject testObject2 = new GameObject(g, "Player2" ,520, 500); //Create a GameObject at coordinate (0, 0), is static and is collideable.
		
		testObject2.addComponent(new Graphic("test_image.gif", 32, 32));
		Physics physics2 = new Physics(1.0f);
		testObject2.addComponent(physics2);
		testObject2.addComponent(new Collider(32, 32));
		testObject2.addComponent(new GameObjectController(physics2, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
		
		GameObject floor = new GameObject(g, "floor", 300, 545);
		floor.addComponent(new Graphic("kjh.jpg", 20, 600));
		floor.addComponent(new Collider(20, 600));
		
//		Clip clip = Loader.loadSound("test_sound.wav");
//		clip.start();
		g.addObject(testObject);
		g.addObject(testObject2);
		g.addObject(floor);
		g.gameLoop();
	}

}
