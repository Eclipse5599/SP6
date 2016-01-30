package game_engine;

//Holds data related to physics 

public class Physics extends Component {
	private double mass;
	private double xVelocity = 0;
	private double yVelocity = 0;
	private double maxVelocity;
	private double minVelocity;
	private double acceleration = 1;
	private static double absoluteMaxVelocity = 10;
	
	public Physics (double mass) {
		compType = Constants.ComponentType.physics;
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
	public void tick (double delta) {
		//Slow down
		if (yVelocity != 0) {
			yVelocity /= 2;
		}
		if (yVelocity < 0.001 && yVelocity > 0){
			yVelocity = 0;
		}
		if (yVelocity > -0.001 && yVelocity < 0){
			yVelocity = 0;
		}
		
		if (xVelocity != 0) {
			xVelocity /= 2;
		}
		if (xVelocity < 0.001 && xVelocity > 0){
			xVelocity = 0;
		}
		if (xVelocity > -0.001 && xVelocity < 0){
			xVelocity = 0;
		}
	}
	
	public void moveUp (double delta) {
		yVelocity -= acceleration;
		if (yVelocity < minVelocity){
			yVelocity = minVelocity;
		}
	}
	
	public void moveRight (double delta) {
		xVelocity += acceleration;
		if (xVelocity < maxVelocity){
			xVelocity = maxVelocity;
		}
	}
	
	public void moveDown (double delta) {
		yVelocity += acceleration;
		if (yVelocity < maxVelocity){
			yVelocity = maxVelocity;
		}
	}
	public void moveLeft (double delta) {
		xVelocity -= acceleration;
		if (xVelocity < minVelocity){
			xVelocity = minVelocity;
		}
	}
	
	public double getXVelocity () {
		return xVelocity;
	}
	
	public double getYVelocity () {
		return yVelocity;
	}
}
