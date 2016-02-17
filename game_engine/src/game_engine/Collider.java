package game_engine;

public class Collider extends Component {
	
	private Physics physics;
	private double radius;
	private int height, width;
	public ColliderType colType;
	public static enum ColliderType{circle, rectangle};
	
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
		if (other != this) {
			if (xIntersect(xMov, other)) {
				owner.doEvent(Constants.Event.collision);
				return true;
			}
			
		}
		return false;
	}
	public boolean yCollide (float yMov, Collider other) {
		if (other != this) {
			boolean wasGrounded = physics.isGrounded();
			if (yIntersect(yMov, other)) {
				if (!wasGrounded && yMov > 0) {
					owner.doEvent(Constants.Event.collision);
				} else if (yMov < 0) {
					owner.doEvent(Constants.Event.collision);
				}
				return true;
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
					return true;					
				}
			} else if (ly <= oly && ly >= ouy) { //Collided with feet
				if ((rx <= orx && rx >= olx) || (lx <= orx && lx >= olx)) {
					if (physics != null) {
						physics.setGrounded(true);
					}
					return true;					
				}
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
			
			if (uy <= oly && uy >= ouy){ //Collided with head
				if ((rx <= orx && rx >= olx) || (lx <= orx && lx >= olx)) {
					return true;					
				}
			} else if (ly <= oly && ly >= ouy) { //Collided with feet
				if ((rx <= orx && rx >= olx) || (lx <= orx && lx >= olx)) {
					if (physics != null) {
						physics.setGrounded(true);
					}
					return true;					
				}
			}
		}
		return false;
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
	
	@Override
	public void setOwner(GameObject owner) {
		this.owner = owner;
		if (owner.hasComponent(Constants.ComponentType.physics)) {
			physics = (Physics)owner.getComponent(Constants.ComponentType.physics);
		}
	}
}
