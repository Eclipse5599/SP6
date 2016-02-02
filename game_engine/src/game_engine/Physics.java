package game_engine;

//Holds data related to physics 

public class Physics extends Component {
	private float mass;
	private float xVelocity = 0;
	private float yVelocity = 0;
	private float maxVelocity;
	private float minVelocity;
	private float acceleration = 50f;
	private static float absoluteMaxVelocity = 100;
	
	
	//TODO: Hastigheter åt två motsatta håll tar ej ut varandra.
	//TODO: Accelerationer upp/vänster är långsammare än accelerationen åt höger/ner.
	public Physics (float mass) {
		compType = Constants.ComponentType.physics;
		this.mass = mass;
		maxVelocity = absoluteMaxVelocity/mass;
		minVelocity = -maxVelocity; 
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
		//Slow down
		if (yVelocity != 0) {
			yVelocity /= 2;
		}
		if ((yVelocity < 0.001 && yVelocity > 0) || (yVelocity > -0.001 && yVelocity < 0)){
			yVelocity = 0;
		}
		
		if (xVelocity != 0) {
			xVelocity /= 2;
		}
		if ((xVelocity < 0.001 && xVelocity > 0) || (xVelocity > -0.001 && xVelocity < 0)){
			xVelocity = 0;
		}
	}
	
	public void moveUp (float delta) {
		yVelocity -= acceleration;
		if (yVelocity < minVelocity){
			yVelocity = minVelocity;
		}
	}
	
	public void moveRight (float delta) {
		xVelocity += acceleration;
		if (xVelocity < maxVelocity){
			xVelocity = maxVelocity;
		}
	}
	
	public void moveDown (float delta) {
		yVelocity += acceleration;
		if (yVelocity < maxVelocity){
			yVelocity = maxVelocity;
		}
	}
	public void moveLeft (float delta) {
		xVelocity -= acceleration;
		if (xVelocity < minVelocity){
			xVelocity = minVelocity;
		}
	}
	
	public float getXVelocity () {
		return xVelocity;
	}
	
	public float getYVelocity () {
		return yVelocity;
	}
}
