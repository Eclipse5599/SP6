package game_engine;

public class Transform extends Component {

	private int x, y;
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

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public float getScale() {
		return scale;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}	
}
