package game;

import java.awt.event.KeyEvent;
import java.util.Random;

import engine.InputEngine.Input;
import engine.physicsEngine.objects.GameObject;
import engine.renderEngine.core.Drawer;
import engine.renderEngine.core.GameManager;
import engine.renderEngine.core.Window;
import engine.renderEngine.core.helpers.*;
import engine.debug.*;

public class Character extends GameObject{
	private int speed = 3;
	private Random random;
	private boolean isMoving, dir=true;
	
	public Character(int x, int y, int sx, int sy) {
		random = new Random();
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		
		spriteCount = 0;
		spriteSheetPath = "res/karakter.png";
		hasImage = false; //enable spritesheet mode
	}
	
	public void kill() {
		isDestroyed = true;
	}
	
	@Override
	public void update(Window win, GameManager gm) {
		Input input = win.getInput();
		isMoving = false;
		
		if(input.isKeyDown(KeyEvent.VK_W)){
            if(y > 0) {
            	y -= speed;
            }
            isMoving = true;
        }else if(input.isKeyDown(KeyEvent.VK_S)){
        	if(y < win.getHeight()-sy) {
            	y += speed;
            }
            isMoving = true;
        }
        if(input.isKeyDown(KeyEvent.VK_A)){
        	if(x > 0) {
            	x -= speed;
            }
            isMoving = true;
            dir=false;
        }else if(input.isKeyDown(KeyEvent.VK_D)){
        	if(x < win.getWidth()-sx) {
            	x += speed;
            }
            isMoving = true;
            dir=true;
        }
	}

	private int timer;
	private int timerLimit;
	private int timerIdleLimit = 512;
	private int timerRunLimit = 256;
	@Override
	public void animate(Window win, Drawer d) {
		d.drawImage(spritesheet.getSprite(spriteCount, 0), new Vector2D(x, y), sx, sy, dir);
		timer+=1;
		
		//idle dan run a hizli gecmek icin
		if(isMoving && timerLimit == timerIdleLimit) {
			timerLimit = timerRunLimit;
		}
		
		if(timer >= timerLimit) {
			spriteCount += 1;
			if(!isMoving) {
				timerLimit = timerIdleLimit;
				spriteCount %= 2;
			}else {
				timerLimit = timerRunLimit;
				spriteCount %= 2;
				spriteCount +=2;
			}
			timer = 0;
		}
		
	}

}
