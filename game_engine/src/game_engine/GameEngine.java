package game_engine;

//The awesomesauce game engine making the game a game omg lol!

import javax.swing.*;

import java.util.ArrayList;
//import java.awt.event.*;
import java.util.List;

public class GameEngine {
	private InputHandler input;
	private Loader loader;
	private PhysicsEngine physics;
	private Renderer renderer;
	private SoundEngine sounds;
	
	private JFrame frame = new JFrame();
	private List<GameObject> gameobjects = new ArrayList<GameObject>();
	
	private boolean exit = false;
	private boolean movementDetected = true;
	
	public GameEngine() {
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	void initialize() {
		input = new InputHandler();
		loader = new Loader();
		physics = new PhysicsEngine();
		renderer = new Renderer();
		sounds = new SoundEngine();
		
		frame.add(renderer);
		frame.addKeyListener(input);
		frame.setFocusable(true);
	}
	
	void gameLoop() {
		while (!exit) {
			input.checkInputToControllers();
			for(GameObject g : gameobjects) {
				g.tick();
			}
			if (movementDetected) {
				movementDetected = false;
				frame.revalidate();
				frame.repaint();
			}
		}
	}
	
	void addObject(GameObject g) {
		gameobjects.add(g);
		if (g.hasComponent(Constants.ComponentType.graphic)) {
			renderer.addGraphicComponent((Graphic)g.getComponent(Constants.ComponentType.graphic));
		}
	}
}
