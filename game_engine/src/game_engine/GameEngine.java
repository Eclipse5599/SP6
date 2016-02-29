package game_engine;

//The awesomesauce game engine making the game a game omg lol!

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
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
	
	public GameEngine(int w, int h) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(w, h);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);		
		frame.setVisible(true);
	}
	
	public void initialize() {
		input = InputHandler.getInstance();
		loader = Loader.getInstance();
		physics = PhysicsEngine.getInstance();
		renderer = Renderer.getInstance();
		sounds = SoundEngine.getInstance();
		
		frame.add(renderer);
		frame.addKeyListener(input);
		frame.setFocusable(true);
	}
	
	public void gameLoop() {
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
	
	public void addObject(GameObject g) {
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
		if (g.hasComponent(Constants.ComponentType.collider)) {
			physics.addColliderComponent(((Collider)g.getComponent(Constants.ComponentType.collider)));
		} 
		if (g.hasChildren()) {
			for (GameObject child : g.getChildren()) {
				addObject(child);
			}
		}
	}
	
	public void setGravityType (Constants.GravityType gravType) {
		physics.setGravType(gravType);
	}
}
