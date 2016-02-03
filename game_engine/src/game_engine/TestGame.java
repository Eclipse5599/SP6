package game_engine;

import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;

//Used for testing of components

public class TestGame {
	
	public static void main (String args[]) {
		GameEngine g = new GameEngine();
		g.initialize();
		
		GameObject testObject = new GameObject(g, "Player" ,20, 20, true, true); //Create a GameObject at coordinate (0, 0), is static and is collideable.
		
		testObject.addComponent(new Graphic(null));
		Physics physics = new Physics(1.0f);
		testObject.addComponent(physics);
		testObject.addComponent(new GameObjectController(physics));
		
		
		GameObject testObject2 = new GameObject(g, "Player2" ,520, 520, true, true); //Create a GameObject at coordinate (0, 0), is static and is collideable.
		
		testObject2.addComponent(new Graphic("test_image.gif"));
		Physics physics2 = new Physics(1.0f);
		testObject2.addComponent(physics2);
		testObject2.addComponent(new GameObjectController(physics2, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
		
//		Clip clip = Loader.loadSound("test_sound.wav");
//		clip.start();
		g.addObject(testObject);
		g.addObject(testObject2);
		g.gameLoop();
	}

}
