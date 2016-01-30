package game_engine;
import java.awt.event.KeyEvent;
//Used for testing of components

public class TestGame {
	
	public static void main (String args[]) {
		GameEngine g = new GameEngine();
		g.initialize();
		
		GameObject testObject = new GameObject("Player" ,20, 20, true, true); //Create a GameObject at coordinate (0, 0), is static and is collideable.
		
		testObject.addComponent(new Graphic(null));
		Physics physics = new Physics(1.0);
		testObject.addComponent(physics);
		testObject.addComponent(new GameObjectController(physics));
		
		
		GameObject testObject2 = new GameObject("Player2" ,80, 80, true, true); //Create a GameObject at coordinate (0, 0), is static and is collideable.
		
		testObject2.addComponent(new Graphic(null));
		Physics physics2 = new Physics(1.0);
		testObject2.addComponent(physics2);
		testObject2.addComponent(new GameObjectController(physics2, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
		
		g.addObject(testObject);
		g.addObject(testObject2);
		g.gameLoop();
	}

}
