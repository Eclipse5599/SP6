package game_engine;

import java.util.List;

public class GameEngine {
	public InputHandler input;
	public Loader loader;
	public PhysicsEngine physics;
	public Renderer renderer;
	public SoundEngine sounds;
	
	private List<GameObject> gameobjects;
	
	void initialize() {
		input = new InputHandler();
		loader = new Loader();
		physics = new PhysicsEngine();
		renderer = new Renderer();
		sounds = new SoundEngine();
	}
	
	void gameLoop() {
		for(GameObject g : gameobjects) {
			g.tick();
		}
	}
	
	void addObject(GameObject g) {
		gameobjects.add(g);
	}
}
