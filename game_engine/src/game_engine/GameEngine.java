package game_engine;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GameEngine {	
	private JFrame frame = new JFrame();
	private ConcurrentLinkedQueue<GameObject> gameobjects = new ConcurrentLinkedQueue<GameObject>();
	
	private boolean exit = false;
	private boolean movementDetected = true;
	private final int frames = 60;
	private Timer deltaTimer = new Timer();
	
	public GameEngine(int w, int h) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(new Dimension(w, h));
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);		
		frame.setVisible(true);
		Constants.theGameEngine = this;
	}
	
	public void initialize() {
		InputHandler.getInstance();
		Loader.getInstance();
		PhysicsEngine.getInstance();
		Renderer.getInstance();
		SoundEngine.getInstance();
		
		Constants.theRenderer.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.add(Constants.theRenderer);
		frame.addKeyListener(Constants.theInputHandler);
		frame.setFocusable(true);
		frame.pack();
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
			
			movementDetected = Constants.thePhysicsEngine.doPhysics(delta);
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
			Constants.theRenderer.addGraphicComponent((Graphic)g.getComponent(Constants.ComponentType.graphic));
		}
		if (g.hasComponent(Constants.ComponentType.controller)) {
			Constants.theInputHandler.addController(((GameObjectController)g.getComponent(Constants.ComponentType.controller)));
		}
		if (g.hasComponent(Constants.ComponentType.physics)) {
			Constants.thePhysicsEngine.addPhysicsComponent(((Physics)g.getComponent(Constants.ComponentType.physics)));
		} 
		if (g.hasComponent(Constants.ComponentType.collider)) {
			Constants.thePhysicsEngine.addColliderComponent(((Collider)g.getComponent(Constants.ComponentType.collider)));
		} 
		if (g.hasChildren()) {
			for (GameObject child : g.getChildren()) {
				addObject(child);
			}
		}
	}
	
	public void removeObject(GameObject g) {
		if(gameobjects.contains(g)) {
			if (g.hasChildren()) {
				for (GameObject child : g.getChildren()) {
					removeObject(child);
				}
			}
			if (g.hasComponent(Constants.ComponentType.graphic)) {
				Constants.theRenderer.removeGraphicComponent((Graphic)g.getComponent(Constants.ComponentType.graphic));
			}
			if (g.hasComponent(Constants.ComponentType.controller)) {
				Constants.theInputHandler.removeController(((GameObjectController)g.getComponent(Constants.ComponentType.controller)));
			}
			if (g.hasComponent(Constants.ComponentType.physics)) {
				Constants.thePhysicsEngine.removePhysicsComponent(((Physics)g.getComponent(Constants.ComponentType.physics)));
			} 
			if (g.hasComponent(Constants.ComponentType.collider)) {
				Constants.thePhysicsEngine.removeColliderComponent(((Collider)g.getComponent(Constants.ComponentType.collider)));
			} 
		}
	}
	
	public void setGravityType (Constants.GravityType gravType) {
		Constants.thePhysicsEngine.setGravType(gravType);
	}
}
