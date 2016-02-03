package game_engine;

public class Collider extends Component {
	
	private int height, width;
	private Transform transform;
	
	public Collider (int height, int width) {
		compType = Constants.ComponentType.collider;
		this.height = height;
		this.width = width;
	}
	
	public boolean collide (Collider other) {
		return false;
	}
	
}
