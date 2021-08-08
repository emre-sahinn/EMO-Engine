package engine.renderEngine.core;

public abstract class GameLevel {

	protected int levelID;
	
	public abstract void init(Window win, GameManager mg);
	
	public abstract void render(Window win, Drawer d, GameManager mg);
	
	public abstract void update(Window win, GameManager mg);
	
	public int getLevel() {
		return levelID;
	}
}
