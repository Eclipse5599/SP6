package game_engine;

//The awesomesauce game engine making the game a game omg lol!

import javax.swing.*;
//import java.awt.event.*;
import java.util.List;

public class GameEngine {
	private InputHandler input;
	private Loader loader;
	private PhysicsEngine physics;
	private Renderer renderer;
	private SoundEngine sounds;
	
	private JFrame frame = new JFrame();
	
	
	private List<GameObject> gameobjects;
	
	public GameEngine() {
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	void initialize() {
		input = new InputHandler();
		loader = new Loader();
		physics = new PhysicsEngine();
		renderer = new Renderer(frame);
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
