package game_engine;

//Holds components used to create game objects

public abstract class Component {

	protected GameObject owner;
	protected Constants.ComponentType compType;
	protected boolean hasParent;
	
	public void tick(float delta) {
		
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
	
	public void setHasParent (boolean state) {
		hasParent = state;
	}
}
