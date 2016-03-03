package game_engine;

public class Transform extends Component {

	private Vector2 pos;
	private float scale;
	
	public Transform (int x, int y) {
		pos = new Vector2(x, y);
	}
	
	public Transform (int x, int y, float scale) {
		pos = new Vector2(x, y);
		this.scale = scale;
	}

	public float getX() {
		if (owner.hasParent()) {
			return owner.getParent().getTransform().getX() + pos.getX();
		} 
		return pos.getX();
	}
	
	public float getY() {
		if (owner.hasParent()) {
			return owner.getParent().getTransform().getY() + pos.getY();
		} 
		return pos.getY();
	}

	public float getScale() {
		return scale;
	}
	
	public void setX(float x) {
		pos.setX(x);
	}

	public void setY(float y) {
		pos.setY(y);
	}

	public void setScale(float scale) {
		this.scale = scale;
	}	
}
