package game;

import game_engine.*;

import java.awt.event.KeyEvent;

//Used for testing of components

public class TestGame {
	
	public static void main (String args[]) {
		GameEngine g = new GameEngine(600, 600);
		g.initialize();
		g.addBackgroundSound("background.wav");
//		g.setGravityType(Constants.GravityType.none);
		
		GameObject player = createPlayer("Player", 300, 216, 32, 32);//GameObject player = createPlayer("Player", 300, 563, 32, 32);
		player.addChild(new FaceChild("Gun", KeyEvent.VK_SHIFT, player, 0, 0, 16, 16, 32, 32));
		
		GameObject player2 = createPlayer("Player2", 64, 216, 32, 32, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A);
		player2.addChild(new FaceChild("Gun", KeyEvent.VK_E, player2, 0, 0, 16, 16, 32, 32));
		
		GameObject floor = createStaticObject("Floor", 300, 590, 20, 600);
		GameObject rightWall = createStaticObject("RightWall", 616, 300, 600, 32);
		GameObject leftWall = createStaticObject("LeftWall", -16, 300, 600, 32);
		
		GameObject platform1 = createStaticObject("Platform", 100, 532, 16, 200);
		GameObject platform2 = createStaticObject("Platform", 500, 532, 16, 200);
		
		GameObject platform3 = createStaticObject("Platform", 300, 474, 16, 480);
		
		GameObject platform4 = createStaticObject("Platform", 32, 416, 16, 64);
		GameObject platform5 = createStaticObject("Platform", 96, 358, 16, 64);
		GameObject platform6 = createStaticObject("Platform", 160, 300, 16, 64);
		
		GameObject platform7 = createStaticObject("Platform", 568, 416, 16, 64);
		GameObject platform8 = createStaticObject("Platform", 504, 358, 16, 64);
		GameObject platform9 = createStaticObject("Platform", 440, 300, 16, 64);
		
		GameObject platform10 = createStaticObject("Platform", 300, 242, 16, 216);
		GameObject platform11 = createStaticObject("Platform", 64, 242, 16, 128);
		GameObject platform12 = createStaticObject("Platform", 536, 242, 16, 128);
		
		GameObject platform13 = createStaticObject("Platform", 300, 490, 58, 16);
		GameObject platform14 = createStaticObject("Platform", 300, 458, 58, 16);
		
		g.addObject(player);
		g.addObject(player2);
		g.addObject(floor);
		g.addObject(rightWall);
		g.addObject(leftWall);
		
		g.addObject(platform1);
		g.addObject(platform2);
		g.addObject(platform3);
		g.addObject(platform4);
		g.addObject(platform5);
		g.addObject(platform6);
		g.addObject(platform7);
		g.addObject(platform8);
		g.addObject(platform9);
		g.addObject(platform10);
		g.addObject(platform11);
		g.addObject(platform12);
		g.addObject(platform13);
		g.addObject(platform14);
		
		g.gameLoop();
	}

	
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w, int up, int left, int down, int right) {
		GameObject player = new PlayerObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("player.png", h, w));
		Physics physics = new Physics(1.0f);
		player.addComponent(physics);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics, up, left, down, right));
		player.addComponent(new Sound ("bump.wav", Constants.Event.collision));
		
		return player;
	}
	
	public static GameObject createPlayer (String name, int startPosX, int startPosY, int h, int w) {
		GameObject player = new PlayerObject(name ,startPosX, startPosY);
		player.addComponent(new Graphic("player.png", h, w));
		Physics physics = new Physics(1.0f);
		player.addComponent(physics);
		player.addComponent(new Collider(h, w));
		player.addComponent(new GameObjectController(physics));
		player.addComponent(new Sound ("bump.wav", Constants.Event.collision));
		
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
}
