package game_engine;

import java.util.concurrent.ConcurrentLinkedQueue;

//Holds data related to physics 

public class Physics extends Component {

	private Vector2 velocity = new Vector2(0, 0), acc = new Vector2(0, 0);
	private float mass;
	private boolean grounded = false, gravityException = false; 
	
	private Physics parentPhysics;
	
	private ConcurrentLinkedQueue<Vector2> forces = new ConcurrentLinkedQueue<Vector2>();
	
	
	public Physics (float mass) {
		compType = Constants.ComponentType.physics;
		this.mass = mass;
	}
	
	@Override
	public void tick (float delta) {
		Vector2 sumForces = new Vector2(0, 0);
		for (Vector2 aForce : forces) {
			sumForces.add(aForce);
		}
		sumForces.multiply(25);
		
		acc = sumForces;
		acc.divide(mass);
		
		velocity.add(acc);
		velocity.divide(2.0f);
		
		forces.clear();
	}	
	
	public void addForce (Vector2 force) {
		if (!forces.contains(force)) {
			forces.add(force);
		}
	}
	public void removeForce(Vector2 force) {
		if (forces.contains(force)) {
			forces.remove(force);
		}
	}
	
	public float getMass () {
		return mass;
	}
	
	public Physics getParentPhysics () {
		return parentPhysics;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public boolean gravityException() {
		return gravityException;
	}
	
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	public void setGravityException(boolean state) {
		gravityException = state;
	}
	
	public boolean isGrounded() {
		return grounded;
	}
	
	@Override
	public void setHasParent (boolean state) {
		hasParent = state;
		if (hasParent) {
			GameObject parent = owner.getParent();
			if (parent.hasComponent(compType)) {
				parentPhysics = (Physics)parent.getComponent(compType);
			}
		}
	}
}