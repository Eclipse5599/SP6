package game_engine;

import game_engine.Constants.GravityType;

import java.util.ArrayList;

//Handles calculations related to physics in the game, such as gravity and force

public class PhysicsEngine {
	private ArrayList<Physics> physicsComponents = new ArrayList<Physics>();
	private ArrayList<Collider> colliderComponents = new ArrayList<Collider>();
	
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
			float xMovement = p.getXVelocity()*delta;
			float yMovement = p.getYVelocity()*delta;
			
			if (gravType == GravityType.world) {
				yMovement += p.getMass()*gravitationalForce*delta;
			}
			
			GameObject tmp = p.owner;
			if (tmp.hasComponent(Constants.ComponentType.collider)) {
				Collider col = (Collider)tmp.getComponent(Constants.ComponentType.collider);
				
				for (Collider c : colliderComponents) {
					col.xCollide(xMovement, c);
					col.yCollide(yMovement, c);
				}
			}
		}
		for (Physics p : physicsComponents) {
			Transform trans = p.getOwnerTransform(); 
			
			float xMovement = p.getXVelocity()*delta;
			float yMovement = p.getYVelocity()*delta;
			
			if (gravType == GravityType.world) {
				yMovement += p.getMass()*gravitationalForce*delta;
			}
			
			GameObject tmp = p.owner;
			if (tmp.hasComponent(Constants.ComponentType.collider)) {
				Collider col = (Collider)tmp.getComponent(Constants.ComponentType.collider);
				if (col.getSouthCollided() && gravType == GravityType.world) {
					p.setGrounded(true);
				} else if (gravType != GravityType.world){
					p.setGrounded(true);
				}
				if (!col.getEastCollided() && xMovement > 0) {
					trans.setX(trans.getX() + xMovement);
				} else if (!col.getWestCollided() && xMovement < 0) {
					trans.setX(trans.getX() + xMovement);
				}
				if (!col.getNorthCollided() && yMovement < 0) {
					trans.setY(trans.getY() + yMovement);
				} else if (!col.getSouthCollided() && yMovement > 0) {
					trans.setY(trans.getY() + yMovement);
				}
				col.resetCollided();
			}
		}
//		}
		return movedObject;
	}
	
	public void addPhysicsComponent (Physics p) {
		physicsComponents.add(p);
		if (gravType != GravityType.world) {
			p.setJumpEnabled(false);
		}
	}
	
	public void addColliderComponent (Collider c) {
		colliderComponents.add(c);
	}

	public GravityType getGravityType () {
		return gravType;
	}
	
	public void setGravType(GravityType gravType) {
		this.gravType = gravType;
		if (gravType != GravityType.world) {
			for (Physics p : physicsComponents) {
				p.setJumpEnabled(false);
			}
		}
	}
}
