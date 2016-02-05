package game_engine;

import java.util.ArrayList;

//Handles calculations related to physics in the game, such as gravity and force

public class PhysicsEngine {
	private ArrayList<Physics> physicsComponents = new ArrayList<Physics>();
	
	private PhysicsEngine () {
		
	}
	
	public static PhysicsEngine getInstance () {
		if (Constants.thePhysicsEngine == null) {
			Constants.thePhysicsEngine = new PhysicsEngine();
		}
		return Constants.thePhysicsEngine;
	}
	
	public boolean doPhysics (float delta) {
		boolean movedObject = false;
		for (Physics p : physicsComponents) {
			if (p.getXVelocity() != 0 || p.getYVelocity() != 0) {
				movedObject = true;
				Transform trans = p.getOwnerTransform(); 
				
				float xMovement = p.getXVelocity()*delta;
				float yMovement = p.getYVelocity()*delta;
				System.out.println("XMovement: " + xMovement + ", YMovement: " + yMovement);
				trans.setX(trans.getX() + xMovement);
				trans.setY(trans.getY() + yMovement);
			}
		}
		return movedObject;
	}
	
	public void addPhysicsComponent (Physics p) {
		physicsComponents.add(p);
	}
}
