package game_engine;

import java.util.ArrayList;

//Handles calculations related to physics in the game, such as gravity and force

public class PhysicsEngine {
	private ArrayList<Physics> physicsComponents = new ArrayList<Physics>();
	private ArrayList<Collider> colliderComponents = new ArrayList<Collider>();
	
	private static enum GravityType{world, point, none};
	private GravityType gravType = GravityType.world;
	private float gravitationalForce = 50f; 
	
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
			movedObject = true;
//			if (p.getXVelocity() != 0 || p.getYVelocity() != 0) {
				Transform trans = p.getOwnerTransform(); 
				
				float xMovement = p.getXVelocity()*delta;
				float yMovement = p.getYVelocity()*delta;
				
				if (gravType == GravityType.world) {
					yMovement += p.getMass()*gravitationalForce*delta;
				}
				
				GameObject tmp = p.owner;
				if (tmp.hasComponent(Constants.ComponentType.collider)) {
					Collider col = (Collider)tmp.getComponent(Constants.ComponentType.collider);
					boolean xCollided = false, yCollided = false;
					for (Collider c : colliderComponents) {
						if (col.xCollide(xMovement, c)) {
							xCollided = true;
						}
						if (col.yCollide(yMovement, c)) {
							yCollided = true;
						}
					}
					if (!xCollided) {
						trans.setX(trans.getX() + xMovement);
					}
					if (!yCollided) {
						trans.setY(trans.getY() + yMovement);
					}
				}
			}
//		}
		return movedObject;
	}
	
	public void addPhysicsComponent (Physics p) {
		physicsComponents.add(p);
	}
	
	public void addColliderComponent (Collider c) {
		colliderComponents.add(c);
	}
}
