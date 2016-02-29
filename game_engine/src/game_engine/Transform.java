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
		if (owner.hasParent()) {
			return owner.getParent().getTransform().x + x;
		} 
		return x;
	}
	
	public float getY() {
		if (owner.hasParent()) {
			return owner.getParent().getTransform().y + y;
		} 
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
	
//	public void moveWithParent (float x, float y) {
//		this.x = this.x + x;
//		this.y = this.y + y; 
//	}
//	
//	public void updateChildrenPos (float x, float y) {
//		if (owner.hasChildren()) {
//			for (GameObject child : owner.getChildren()) {
//				child.getTransform().moveWithParent(x, y);
//			}
//		}
//	}

	public void setScale(float scale) {
		this.scale = scale;
	}	
}
