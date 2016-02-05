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
	private final int frames = 60;
	private Timer deltaTimer = new Timer();
	
	public GameEngine() {
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	void initialize() {
		input = InputHandler.getInstance();
		loader = Loader.getInstance();
		physics = PhysicsEngine.getInstance();
		renderer = Renderer.getInstance();
		sounds = SoundEngine.getInstance();
		
		frame.add(renderer);
		frame.addKeyListener(input);
		frame.setFocusable(true);
	}
	
	void gameLoop() {
		frame.revalidate();
		frame.repaint();
		while (!exit) {
			long deltaMillis = deltaTimer.getDeltaMillis();
			float delta = deltaMillis/1000f;
			//System.out.println("Seconds: " + delta + ", Millis: " + deltaMillis);
			for(GameObject g : gameobjects) {
				g.tick(delta);
			}
			
			movementDetected = physics.doPhysics(delta);
			if (movementDetected) {
				movementDetected = false;
				frame.revalidate();
				frame.repaint();
			}
			
			try {
				Thread.sleep((long) (frames*(delta)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	void addObject(GameObject g) {
		gameobjects.add(g);
		if (g.hasComponent(Constants.ComponentType.graphic)) {
			renderer.addGraphicComponent((Graphic)g.getComponent(Constants.ComponentType.graphic));
		}
		if (g.hasComponent(Constants.ComponentType.controller)) {
			input.addController(((GameObjectController)g.getComponent(Constants.ComponentType.controller)));
		}
		if (g.hasComponent(Constants.ComponentType.physics)) {
			physics.addPhysicsComponent(((Physics)g.getComponent(Constants.ComponentType.physics)));
		} 
	}
	
	public Loader getLoader () {
		return loader;
	}
}
