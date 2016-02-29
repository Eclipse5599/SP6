package game_engine;

//Holds constants for the game

//Hold references to all sub-systems and game engine!

public final class Constants {
	public static enum ComponentType{graphic, physics, sound, controller, collider};
	public static enum GravityType{world, point, none};
	public static enum Event{collision};
	
	public static GameEngine theGameEngine = null;
	public static InputHandler theInputHandler = null;
	public static Loader theLoader = null;
	public static PhysicsEngine thePhysicsEngine = null;
	public static Renderer theRenderer = null;
	public static SoundEngine theSoundEngine = null;
}
