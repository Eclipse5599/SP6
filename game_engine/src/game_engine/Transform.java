package game_engine;

public class Transform extends Component {

	private float x, y;
	private float scale;
	
	public Transform (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Transform (int x, int y, float scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public float getScale() {
		return scale;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}	
}
