package game_engine;

import java.util.ArrayList;

//Handles calculations related to physics in the game, such as gravity and force

public class PhysicsEngine {
	private ArrayList<Physics> physicsComponents = new ArrayList<Physics>();
	
	public PhysicsEngine () {
		
	}
	
	public boolean doPhysics (double delta) {
		boolean movedObject = false;
		for (Physics p : physicsComponents) {
			if (p.getXVelocity() != 0 || p.getYVelocity() != 0) {
				movedObject = true;
				Transform trans = p.getOwnerTransform(); 
				double xVel = p.getXVelocity();
				double yVel = p.getYVelocity();
				System.out.println("XVel: " + xVel + ", YVel: " + yVel);
				trans.setX((int)(trans.getX() + (xVel)));
				trans.setY((int)(trans.getY() + (yVel)));
			}
		}
		return movedObject;
	}
	
	public void addPhysicsComponent (Physics p) {
		System.out.println("Adding physicComponent");
		physicsComponents.add(p);
	}
}
