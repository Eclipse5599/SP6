package game_engine;

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
		
		g.addObject(testObject);
		g.gameLoop();
	}

}
