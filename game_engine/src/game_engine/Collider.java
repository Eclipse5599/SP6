package game_engine;

public class Collider extends Component {
	
	public static enum ColliderType{circle, rectangle};
	private Physics physics;
	private double radius;
	private int height, width;
	public ColliderType colType;
	private boolean northCollided = false, eastCollided = false, southCollided = false, westCollided = false, parentHasCollider = true, isTrigger = false;
	private Collider parentCollider = null;
	
	public Collider (Graphic g) {
		compType = Constants.ComponentType.collider;
		colType = ColliderType.rectangle;
		height = g.getHeight();
		width = g.getWidth();
	}
	
	public Collider (double radius) {
		compType = Constants.ComponentType.collider;
		colType = ColliderType.circle;
		this.radius = radius;
	}
	
	public Collider (int height, int width) {
		compType = Constants.ComponentType.collider;
		colType = ColliderType.rectangle;
		this.height = height;
		this.width = width;
	}
	
	public boolean xCollide (float xMov, Collider other) {
		if (other != this && (!other.owner.hasParent() || other.owner.getParent() != this.owner) && !other.getIsTrigger()) {
			if (xIntersect(xMov, other)) {
				owner.doEvent(Constants.Event.collision);
				owner.handleCollisionExtras(other.owner);
				return true;
			}
		}
		return false;
	}
	public boolean yCollide (float yMov, Collider other) {
		if (other != this && (!other.owner.hasParent() || other.owner.getParent() != this.owner) && !other.getIsTrigger()) {
			boolean wasGrounded = physics.isGrounded();
			if (yIntersect(yMov, other)) {
				if ((!wasGrounded) && yMov > 0) {
					owner.doEvent(Constants.Event.collision);
				} else if (yMov < 0) {
					owner.doEvent(Constants.Event.collision);
				}
				owner.handleCollisionExtras(other.owner);
				return true;
			}
		}
		return false;
	}
	
	private boolean xIntersect (float xMov, Collider other) {
		if (colType == ColliderType.circle && other.getColType() == ColliderType.circle) {
			if (other != this) {
				Transform otr = other.getTransform();
				float xDist = Math.abs(getTransform().getX() + xMov - otr.getX());
				float yDist = Math.abs(getTransform().getY() - otr.getY());
				
				double dist = Math.sqrt(xDist * xDist + yDist * yDist);
				System.out.println("Distance: " + dist);
				if ((radius + other.getRadius()) > dist) {
					return true;
				}
			}
			return false;
		} else if (colType == ColliderType.rectangle && other.getColType() == ColliderType.rectangle) {
			//uy = upperY, ly = lowerY, lx = leftX, rx = rightX
			Transform t = getTransform();
			float uy = t.getY() - height/2, ly = t.getY() + height/2;
			float lx = t.getX() - width/2 + xMov, rx = t.getX() + width/2 + xMov;
			
			Transform ot = other.getTransform();
			float ouy = ot.getY() - other.getHeight()/2, oly = ot.getY() + other.getHeight()/2;
			float olx = ot.getX() - other.getWidth()/2, orx = ot.getX() + other.getWidth()/2;
			
			if (rx <= orx && rx >= olx) { //collided with right side
				if ((uy <= oly && uy >= ouy) || (ly <= oly && ly >= ouy)) {
					if (!isTrigger) {
						other.setWestCollided(true);
						setEastCollided(true);
					}
					return true;
				}else if ((ouy <= ly && ouy >= uy) || (oly <= ly && oly >= uy)) {
					if (!isTrigger) {
						other.setWestCollided(true);
						setEastCollided(true);
					}
					return true;
				}
			} else if (lx <= orx && lx >= olx) {//collided with left side
				if ((uy <= oly && uy >= ouy) || (ly <= oly && ly >= ouy)) {
					if (!isTrigger) {
						other.setEastCollided(true);
						setWestCollided(true);
					}
					return true;
				}else if ((ouy <= ly && ouy >= uy) || (oly <= ly && oly >= uy)) {
					if (!isTrigger) {
						other.setEastCollided(true);
						setWestCollided(true);
					}
					return true;
				}
			}
		}
		return false;
	}
	private boolean yIntersect (float yMov, Collider other) {
		if (physics != null && yMov < 0) {
			physics.setGrounded(false);
		}
		
		if (colType == ColliderType.circle && other.getColType() == ColliderType.circle) {	
			Transform otr = other.getTransform();
			float xDist = Math.abs(getTransform().getX() - otr.getX());
			float yDist = Math.abs(getTransform().getY() + yMov - otr.getY());
			
			double dist = Math.sqrt(xDist * xDist + yDist * yDist);
			System.out.println("Distance: " + dist);
			if ((radius + other.getRadius()) > dist) {
				return true;
			}
			return false;
		} else if (colType == ColliderType.rectangle && other.getColType() == ColliderType.rectangle) {
			//uy = upperY, ly = lowerY, lx = leftX, rx = rightX
			Transform t = getTransform();
			float uy = t.getY() - height/2 + yMov, ly = t.getY() + height/2 + yMov;
			float lx = t.getX() - width/2, rx = t.getX() + width/2;
			
			Transform ot = other.getTransform();
			float ouy = ot.getY() - other.getHeight()/2, oly = ot.getY() + other.getHeight()/2;
			float olx = ot.getX() - other.getWidth()/2, orx = ot.getX() + other.getWidth()/2;
			
			if (uy <= oly && uy >= ouy){ //Collided with head
				if ((rx <= orx && rx >= olx) || (lx <= orx && lx >= olx)) {
					if (!isTrigger) {
						other.setSouthCollided(true);
						setNorthCollided(true);
					}
					return true;					
				} else if ((orx <= rx && orx >= lx) || (olx <= rx && olx >= lx)) {
					if (!isTrigger) {
						other.setSouthCollided(true);
						setNorthCollided(true);
					}
					return true;					
				}
			} else if (ly <= oly && ly >= ouy) { //Collided with feet
				if ((rx <= orx && rx >= olx) || (lx <= orx && lx >= olx)) {
					if (!isTrigger) {
						other.setNorthCollided(true);
						setSouthCollided(true);
					}
					return true;					
				}else if ((orx <= rx && orx >= lx) || (olx <= rx && olx >= lx)) {
					if (!isTrigger) {
						other.setNorthCollided(true);
						setSouthCollided(true);
					}
					return true;					
				}
			}
		}
		return false;
	}
	
	public void resetCollided () {
		setNorthCollided(false);
		setEastCollided(false);
		setSouthCollided(false);
		setWestCollided(false);
	}
	
	public void updateParentCollider () {
		if (hasParent && parentHasCollider) {
			parentCollider.setNorthCollided(northCollided);
			parentCollider.setEastCollided(eastCollided);
			parentCollider.setSouthCollided(southCollided);
			parentCollider.setWestCollided(westCollided);
		}
	}
	
	public final Transform getTransform () {
		return getOwnerTransform();
	}
	
	public double getRadius () {
		return radius;
	}
	
	public ColliderType getColType () {
		return colType;
	}
	
	public int getHeight () {
		return height;
	}
	
	public int getWidth () {
		return width;
	}
	
	public boolean getNorthCollided () {
		return northCollided;
	}
	public boolean getEastCollided () {
		return eastCollided;
	}
	public boolean getSouthCollided () {
		return southCollided;
	}
	public boolean getWestCollided () {
		return westCollided;
	}
	
	public boolean getIsTrigger () {
		return isTrigger;
	}
	
	@Override
	public void setOwner (GameObject owner) {
		this.owner = owner;
		if (owner.hasComponent(Constants.ComponentType.physics)) {
			physics = (Physics)owner.getComponent(Constants.ComponentType.physics);
		}
	}
	
	public void setNorthCollided (boolean status) {
		northCollided = status;
		updateParentCollider();
	}
	public void setEastCollided (boolean status) {
		eastCollided = status;
		updateParentCollider();
	}
	public void setSouthCollided (boolean status) {
		southCollided = status;
		updateParentCollider();
	}
	public void setWestCollided (boolean status) {
		westCollided = status;
		updateParentCollider();
	}
	
	public void setIsTrigger (boolean state) {
		isTrigger = state;
	}
	
	@Override
	public void setHasParent (boolean state) {
		hasParent = state;
		if (hasParent) {
			GameObject parent = owner.getParent();
			if (parent.hasComponent(compType)) {
				parentCollider = (Collider)parent.getComponent(compType);
			} else {
				parentHasCollider = false;
			}
		}
	}
}
