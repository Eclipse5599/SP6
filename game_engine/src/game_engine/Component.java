package game_engine;

//Holds components used to create game objects

public abstract class Component {

	protected GameObject owner;
	protected Constants.ComponentType compType;
	
	public void tick(double delta) {
		
	}
	
	public Constants.ComponentType getType() {
		return compType;
	}
	
	public Transform getOwnerTransform() { 
		return owner.getTransform();
	}
	
	public void setOwner(GameObject owner) {
		this.owner = owner;
	}
}
