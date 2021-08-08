package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import engine.physicsEngine.objects.GameObject;
import engine.physicsEngine.objects.ObjectManager;
import engine.renderEngine.core.Drawer;
import engine.renderEngine.core.GameManager;
import engine.renderEngine.core.Window;
import engine.renderEngine.graphics.Image;
import engine.debug.*;
import engine.soundEngine.ResourceLoader;

public class tntSpawner extends GameObject {

	private Random random;
	private int randomX, randomY;

	public tntSpawner(int x, int y, int sx, int sy) {
		random = new Random();

		doDraw = false;
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
	}

	private int counter;
	private int counterLimit = 100;
	private static ArrayList<Block> tnts = new ArrayList<Block>();
	@Override
	public void update(Window win, GameManager gm) {
		if (counter >= counterLimit) {
			Block b = new Block(100, 100, 47, 50);
			tnts.add(b);
			ObjectManager.addObject(b);
			counter = 0;
			if(counterLimit > 20)
				counterLimit--;
			Debug.Log("Speed: " + counterLimit);
		}
		counter++;
		
	}
	
	public boolean checkDistance(Character character) {
		for (int i = 0; i < tnts.size(); i++) {
			Block b = tnts.get(i);
			if(b != null) {
				float distance = (float) Math.sqrt((character.getY() - b.getY()) * (character.getY() - b.getY()) + 
						(character.getX() - b.getX()) * (character.getX() - b.getX()));
				if(distance < 50) {
					character.kill();
					isDestroyed = true;
					return true;
				}
			}else {
				tnts.remove(i);
			}
		}
		return false;
	}
	
	public void clear() {
		tnts.clear();
	}

	@Override
	public void animate(Window win, Drawer d) {
		// TODO Auto-generated method stub

	}

}
