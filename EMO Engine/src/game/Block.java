package game;

import java.awt.Color;
import java.util.Random;

import engine.physicsEngine.objects.GameObject;
import engine.physicsEngine.objects.ObjectManager;
import engine.renderEngine.core.Drawer;
import engine.renderEngine.core.GameManager;
import engine.renderEngine.core.Window;
import engine.renderEngine.graphics.Image;
import engine.soundEngine.ResourceLoader;

public class Block extends GameObject{

	private Random random;
	private int randomX, randomY;
	
	public Block(int x, int y, int sx, int sy) {
		random = new Random();
		
		image = new Image(ResourceLoader.loadImage("res/tnt.png"));
		hasImage = true;
		this.sx = sx;
		this.sy = sy;
		
		randomX = random.nextBoolean()?1:-1;
		randomY = random.nextBoolean()?1:-1;
		
	 if(randomX == 1 && randomY == 1) {
		 this.x = random.nextInt(700);
		 this.y = 0;
		 randomX = 0;
		 randomY = 1;
	 }else if(randomX == 1 && randomY == -1) {
		 this.x = random.nextInt(700);
		 this.y = 500;
		 randomX = 0;
		 randomY = -1;
	 }else if(randomX == -1 && randomY == 1) {
		 this.x = 0;
		 this.y = random.nextInt(500);
		 randomX = 1;
		 randomY = 0;
	 }else if(randomX == -1 && randomY == -1) {
		 this.x = 700;
		 this.y = random.nextInt(500);
		 randomX = -1;
		 randomY = 0;
	 }
	}
	
	@Override
	public void update(Window win, GameManager gm) {
		if(x < 0 || y < 0 || x > win.getWidth() || y > win.getHeight()) {
			//out of border
			isDestroyed = true;
		}else {
			x += randomX;
			y += randomY;
		}
	}

	@Override
	public void animate(Window win, Drawer d) {
		// TODO Auto-generated method stub
		
	}

}
