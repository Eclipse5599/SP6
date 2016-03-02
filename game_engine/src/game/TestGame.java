package game;

import game_engine.*;

import java.awt.event.KeyEvent;

//Used for testing of components

public class TestGame {
	
	public static void main (String args[]) {
		GameEngine g = new GameEngine(600, 600);
		g.initialize();
//		g.setGravityType(Constants.GravityType.none);
		
		GameObject player = createPlayer("Player", 300, 520, 32, 32);
		player.addAction(KeyEvent.VK_SPACE, new ShootAction());
		
		GameObject player2 = createPlayer("Player2", 200, 520, 32, 32, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A);
		
		GameObject floor = createStaticObject("Floor", 300, 590, 20, 600);
		GameObject rightWall = createStaticObject("RightWall", 600, 300, 600, 2);
		GameObject leftWall = createStaticObject("LeftWall", 0, 300, 600, 2);
		
		g.addObject(player);
		g.addObject(player2);
		g.addObject(floor);
		g.addObject(rightWall);
		g.addObject(leftWall);
		g.gameLoop();
	}

	
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w, int up, int left, int down, int right) {
		GameObject player = new GameObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("test_image.gif", h, w));
		Physics physics = new Physics(1.0f);
		player.addComponent(physics);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics, up, left, down, right));
		player.addComponent(new Sound ("test_sound.wav", Constants.Event.collision));
		
		return player;
	}
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w) {
		GameObject player = new GameObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("test_image.gif", h, w));
		Physics physics = new Physics(1.0f);
		player.addComponent(physics);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics));
		player.addComponent(new Sound ("test_sound.wav", Constants.Event.collision));
		
		return player;
	}
	
	public static GameObject createStaticObject(String name, int startPosX, int startPosY, int h, int w) {
		GameObject object = new GameObject(name, startPosX, startPosY);
		
		object.addComponent(new Graphic(null, h, w));
		object.addComponent(new Collider(h, w));
		
		return object;
	}
	
	public static GameObject createChildObject(String name, int startPosX, int startPosY, int h, int w) {
		GameObject object = new GameObject(name, startPosX, startPosY);
		
		object.addComponent(new Graphic(null, h, w));
		
		return object;
	}
	
	public static class ShootAction extends game_engine.Action {
		public ShootAction () {
			super();
		}
		public ShootAction (long frequencyMillis) {
			super(frequencyMillis);
		}
		
		@Override
		public void doAction () {
			if (readyToUse()) {
				GameObject owner = getOwner();
				Transform ownerTransform = owner.getTransform();
				System.out.println("SHOOTING at direction " + owner.getFacingDirection());
				GameObject newO = new ShotObject("Shot", owner,(int)ownerTransform.getX(), (int)ownerTransform.getY(), owner.getFacingDirection(), 1000);
				Constants.theGameEngine.addObject(newO);
			}
		}
	}
}
