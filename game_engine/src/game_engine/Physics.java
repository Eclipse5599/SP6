package game_engine;

//Holds data related to physics 

public class Physics extends Component {
	private float mass;
	private float xVelocity = 0, yVelocity = 0;
	private float maxVelocity, minVelocity;
	private float acceleration = 10f;
	private static float absoluteMaxVelocity = 100;
	private boolean moveUp = false, moveRight = false, moveDown = false, moveLeft = false; 
	
	
	//TODO: Hastigheter åt två motsatta håll tar ej ut varandra.
	//TODO: Accelerationer upp/vänster är långsammare än accelerationen åt höger/ner.
	public Physics (float mass) {
		compType = Constants.ComponentType.physics;
		this.mass = mass;
		maxVelocity = absoluteMaxVelocity/mass;
		minVelocity = -maxVelocity; 
		
		acceleration  = maxVelocity;
		
		if (maxVelocity < 1) {
			maxVelocity = 1; 
			minVelocity = -maxVelocity;
		} else if (maxVelocity > absoluteMaxVelocity) {
			maxVelocity = absoluteMaxVelocity;
			minVelocity = -maxVelocity;
		}
	}
	
	@Override
	public void tick (float delta) {
		//Vertical
		if (moveUp && moveDown) {
			moveUp = false;
			moveDown = false;
		}
		if (moveUp) {
			yVelocity -= acceleration*delta;
		}
		if (moveDown) {
			yVelocity += acceleration*delta;
		}
		
		//Horizontal
		if (moveRight && moveLeft) {
			moveRight = false;
			moveLeft = false;
		}
		if (moveRight) {
			xVelocity += acceleration*delta;
		}
		if (moveLeft) {
			xVelocity -= acceleration*delta;
		}
		
		//Lock velocity
		if (yVelocity > maxVelocity) {
			yVelocity = maxVelocity;
		} else if (yVelocity < minVelocity) {
			yVelocity = minVelocity;
		}
		if (xVelocity > maxVelocity) {
			xVelocity = maxVelocity;
		} else if (xVelocity < minVelocity) {
			xVelocity = minVelocity;
		}
		
		//Slow down
		if (!moveUp && !moveDown) {
			if ((yVelocity < 0.001 && yVelocity > 0) || (yVelocity > -0.001 && yVelocity < 0)){
				yVelocity = 0;
			} else {
				if (yVelocity < 0) {
					yVelocity += acceleration*delta;
				} else if (yVelocity > 0){
					yVelocity -= acceleration*delta;
				}
			}
		}
		if (!moveRight && !moveLeft) {
			if ((xVelocity < 0.001 && xVelocity > 0) || (xVelocity > -0.001 && xVelocity < 0)){
				xVelocity = 0;
			} else {
				if (xVelocity < 0) {
					xVelocity += acceleration*delta;
				} else if (xVelocity > 0){
					xVelocity -= acceleration*delta;
				}
			}
		}
		
		moveUp = false;
		moveRight = false;
		moveDown = false;
		moveLeft = false;
	}
	
	public void moveUp () {
		moveUp = true;
	}
	
	public void moveRight () {
		moveRight = true;
	}
	
	public void moveDown () {
		moveDown = true;
	}
	public void moveLeft () {
		moveLeft = true;
	}	
	
	public float getXVelocity () {
		return xVelocity;
	}
	
	public float getYVelocity () {
		return yVelocity;
	}
}

//Old code

//@Override
//public void tick (float delta) {
//	//Slow down
//	if (yVelocity != 0) {
//		yVelocity /= 2;
//	}
//	if ((yVelocity < 0.001 && yVelocity > 0) || (yVelocity > -0.001 && yVelocity < 0)){
//		yVelocity = 0;
//	}
//	
//	if (xVelocity != 0) {
//		xVelocity /= 2;
//	}
//	if ((xVelocity < 0.001 && xVelocity > 0) || (xVelocity > -0.001 && xVelocity < 0)){
//		xVelocity = 0;
//	}
//}
//
//public void moveUp (float delta) {
//	yVelocity -= acceleration;
//	if (yVelocity < minVelocity){
//		yVelocity = minVelocity;
//	}
//}
//
//public void moveRight (float delta) {
//	xVelocity += acceleration;
//	if (xVelocity < maxVelocity){
//		xVelocity = maxVelocity;
//	}
//}
//
//public void moveDown (float delta) {
//	yVelocity += acceleration;
//	if (yVelocity < maxVelocity){
//		yVelocity = maxVelocity;
//	}
//}
//public void moveLeft (float delta) {
//	xVelocity -= acceleration;
//	if (xVelocity < minVelocity){
//		xVelocity = minVelocity;
//	}
//}
