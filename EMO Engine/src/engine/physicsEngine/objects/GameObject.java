package engine.physicsEngine.objects;

import engine.renderEngine.core.Drawer;
import engine.renderEngine.core.GameManager;
import engine.renderEngine.core.Window;
import engine.renderEngine.graphics.Image;
import engine.renderEngine.graphics.SpriteSheet;

public abstract class GameObject {

	protected int x, y, sx, sy;
	protected boolean doDraw=true, didDraw, hasImage, isDestroyed, direction = true;
	protected Image image;
	//spritesheet
	protected int spriteCount;
	protected SpriteSheet spritesheet;
	protected String spriteSheetPath;
	
	
	public void render(Window win, Drawer d) {
		if (doDraw) {
			if (hasImage) {
				d.drawImage(image, x, y, sx,sy, direction);
			}else {
				spritesheet = new SpriteSheet(new Image(spriteSheetPath), sx, sy) ;
				animate(win, d);
				//d.drawImage(spritesheet.getSprite(0, 0), x, y);
			}
			didDraw = true;
		}
	}
	
	public abstract void update(Window win, GameManager gm);
	
	public abstract void animate(Window win, Drawer d);
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getSX() {return sx;}
	public int getSY() {return sy;}
	public boolean getDidDraw() {return didDraw;}
}
