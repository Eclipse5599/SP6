package game_engine;

import java.util.ArrayList;

//Handles calculations related to physics in the game, such as gravity and force

public class PhysicsEngine {
	private ArrayList<Physics> physicsComponents = new ArrayList<Physics>();
	
	public PhysicsEngine () {
		
	}
	
	public boolean doPhysics (float delta) {
		boolean movedObject = false;
		for (Physics p : physicsComponents) {
			if (p.getXVelocity() != 0 || p.getYVelocity() != 0) {
				movedObject = true;
				Transform trans = p.getOwnerTransform(); 
				float xVel = p.getXVelocity()*delta;
				float yVel = p.getYVelocity()*delta;
				System.out.println("XVel: " + xVel + ", YVel: " + yVel);
				trans.setX(trans.getX() + xVel);
				trans.setY(trans.getY() + yVel);
			}
		}
		return movedObject;
	}
	
	public void addPhysicsComponent (Physics p) {
		physicsComponents.add(p);
	}
}
