package game_engine;

import game_engine.Constants.GravityType;

import java.util.concurrent.ConcurrentLinkedQueue;

//Handles calculations related to physics in the game, such as gravity and force

public class PhysicsEngine {
	private ConcurrentLinkedQueue<Physics> physicsComponents = new ConcurrentLinkedQueue<Physics>();
	private ConcurrentLinkedQueue<Collider> colliderComponents = new ConcurrentLinkedQueue<Collider>();
	
	private GravityType gravType = GravityType.world;
	private Vector2 gravitationalForce = new Vector2(0, 100f); 
	
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
			
			if (gravType == GravityType.world && !p.gravityException()) {
				p.addForce(gravitationalForce);
			}
			Vector2 movement = new Vector2(p.getVelocity());
			movement.multiply(delta);
			
			GameObject tmp = p.owner;
			if (tmp.hasComponent(Constants.ComponentType.collider)) {
				Collider col = (Collider)tmp.getComponent(Constants.ComponentType.collider);
				
				for (Collider c : colliderComponents) {
					col.xCollide(movement.getX(), c);
					col.yCollide(movement.getY(), c);
				}
			}
		}
		for (Physics p : physicsComponents) {
			Transform trans = p.getOwnerTransform(); 
			
			if (gravType == GravityType.world && !p.gravityException()) {
				p.addForce(gravitationalForce);
			}
			Vector2 movement = new Vector2(p.getVelocity());
			movement.multiply(delta);
			
			GameObject tmp = p.owner;
			if (tmp.hasComponent(Constants.ComponentType.collider)) {
				Collider col = (Collider)tmp.getComponent(Constants.ComponentType.collider);
				if (col.getSouthCollided() && gravType == GravityType.world) {
					p.setGrounded(true);
				} else if (gravType != GravityType.world){
					p.setGrounded(true);
				}
				if (!col.getEastCollided() && movement.getX() > 0) {
					trans.setX(trans.getX() + movement.getX());
				} else if (!col.getWestCollided() && movement.getX() < 0) {
					trans.setX(trans.getX() + movement.getX());
				}
				if (!col.getNorthCollided() && movement.getY() < 0) {
					trans.setY(trans.getY() + movement.getY());
				} else if (!col.getSouthCollided() && movement.getY() > 0) {
					trans.setY(trans.getY() + movement.getY());
				}
				col.resetCollided();
			} else {
				trans.setX(trans.getX() + movement.getX());
				trans.setY(trans.getY() + movement.getY());
			}
		}
//		}
		return movedObject;
	}
	
	public void addPhysicsComponent (Physics p) {
		physicsComponents.add(p);
//		if (gravType != GravityType.world) {
//			p.setJumpEnabled(false);
//		}
	}
	
	public void addColliderComponent (Collider c) {
		colliderComponents.add(c);
	}

	public GravityType getGravityType () {
		return gravType;
	}
	
	public void setGravType(GravityType gravType) {
		this.gravType = gravType;
//		if (gravType != GravityType.world) {
//			for (Physics p : physicsComponents) {
//				p.setJumpEnabled(false);
//			}
//		}
	}

	public void removePhysicsComponent(Physics physics) {
		physicsComponents.remove(physics);
	}

	public void removeColliderComponent(Collider collider) {
		colliderComponents.remove(collider);
	}
}
