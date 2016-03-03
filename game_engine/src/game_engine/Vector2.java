package game_engine;

public class Vector2 {
	private float x, y;
	
	public Vector2 (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 other) {
		x = other.getX();
		y = other.getY();
	}

	public void add(Vector2 other) {
		x += other.getX();
		y += other.getY();
	}
	
	public void divide(float d) {
		x /= d;
		y /= d;
	}
	
	public void multiply(float m) {
		x *= m;
		y *= m;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}
