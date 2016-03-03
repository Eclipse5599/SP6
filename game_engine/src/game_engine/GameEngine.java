package game_engine;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameEngine {	
	private JFrame frame = new JFrame();
	private ConcurrentLinkedQueue<GameObject> gameobjects = new ConcurrentLinkedQueue<GameObject>();
	
	private boolean exit = false;
	private boolean movementDetected = true;
	private final int FPS = 60;
	private final long OPTIMAL_TIME = 1000000/FPS;
	private Timer deltaTimer = new Timer();
	private long lastLoopTime = 0;
	private int fps = 0, lastFpsTime = 0;
	
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
			long now = System.currentTimeMillis();
			long updateLength = deltaTimer.getRunTime();
			deltaTimer.reset();
			lastLoopTime = now;
			float delta = updateLength/(float)OPTIMAL_TIME;
			for(GameObject g : gameobjects) {
				g.tick(delta);
			}
			
			movementDetected = Constants.thePhysicsEngine.doPhysics(delta);
			if (movementDetected) {
				movementDetected = false;
				frame.revalidate();
				frame.repaint();
			}
			
			lastFpsTime += updateLength;
			fps++;
			
			if (lastFpsTime >= 1000000) {
				lastFpsTime = 0;
				fps = 0;
			}
			
			try {
				Thread.sleep((lastLoopTime - System.currentTimeMillis() + OPTIMAL_TIME)/1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		frame.remove(Constants.theRenderer);
		JLabel endMessage = new JLabel("Game Ended", JLabel.CENTER);
		endMessage.setVerticalAlignment(JLabel.CENTER);
		endMessage.setFont(new Font(endMessage.getFont().getName(), Font.PLAIN, 50));
		frame.add(endMessage);
		frame.revalidate();
		frame.repaint();
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
			if (g.getName() == "Player" || g.getName() == "Player2") {
				exit();
			}
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
	
	public void addBackgroundSound(String path) {
		Constants.theSoundEngine.addBackgroundSound(path);
	}
	
	public void setGravityType (Constants.GravityType gravType) {
		Constants.thePhysicsEngine.setGravType(gravType);
	}
	
	public void exit () {
		Constants.theSoundEngine.exit();
		exit = true;
	}
}
