/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import engine.InputEngine.Input;
import engine.InputEngine.Mouse;
import engine.physicsEngine.objects.ObjectManager;
import engine.renderEngine.core.Drawer;
import engine.renderEngine.core.GameLevel;
import engine.renderEngine.core.GameManager;
import engine.renderEngine.core.Window;
import engine.renderEngine.core.helpers.*;
import engine.renderEngine.graphics.Image;
import engine.renderEngine.graphics.SpriteSheet;
import engine.debug.*;
import engine.soundEngine.ResourceLoader;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author emsahin, 85530
 */
public class Game extends GameLevel {
    //SpriteSheet sp;
	Image grass;
	Character character;
	tntSpawner spawner;
    private String coords = "";
    int skor = 0;
    int y = 200, x = 100;
    private int cx, cy;
    public int tntCount ;
    
    public Game(int levelID) {
        this.levelID = levelID;
    }

    @Override
    public void init(Window win, GameManager mg) {
    	Random r = new Random();

    	spawner = new tntSpawner(-50,-50,0,0);
    	ObjectManager.addObject(spawner);
    	
    	character = new Character(r.nextInt(win.getWidth())/2 + 100, r.nextInt(win.getWidth())/2 , 57,80);
		ObjectManager.addObject(character);
		grass = new Image(ResourceLoader.loadImage("res/grass.png"));
    }

    @Override
    public void render(Window win, Drawer d, GameManager gm) {
        d.setColor(Color.black);
        d.drawImage(grass, new Vector2D(-120, 0), 640, 640, true);
        d.drawImage(grass, new Vector2D(200, 0), 640, 640, true);
        
        d.drawString(coords, new Vector2D(5, 20));
        d.drawString(win.lastFrames+" FPS", new Vector2D(5-cx, win.getHeight()-5-cy));
        
        if(gameEnd) {
        	ObjectManager.clearObjects();
        	d.fillRect(new Vector2D(0, 0), new Rectangle(win.getWidth(), win.getWidth()));
        	
        	d.setColor(Color.white);
        	d.drawString("GEBERDÝN..", new Vector2D(win.getWidth()/2-50, win.getHeight()/2-100));
        	d.drawString("SKORUN: "+skor, new Vector2D(win.getWidth()/2-50, win.getHeight()/2+100));
        	d.drawString("Yeniden oynamak için R'ye bas", new Vector2D(win.getWidth()/2-100, win.getHeight()/2+200));
        }
        
        d.setCameraPos(cx, cy);
    }
    
    int timer = 0;
    boolean gameEnd;
    @Override
    public void update(Window win, GameManager gm) {
        Input input = win.getInput();
        
        // Mouse m = win.getMouse();
        // not required
        
        if(input.isKeyPressed(KeyEvent.VK_R)){
            skor = 0;
            spawner.clear();
            ObjectManager.clearObjects();
            gameEnd = false;
            gm.enterLevel(0, true);
        }
        
        if(gameEnd)
        	return;
        gameEnd = spawner.checkDistance(character);
        
        
        timer++;
        if(timer >= 10) {
        	timer = 0;
        	coords = "Skor: " + skor++;
        }
        
        
        if(input.isKeyDown(KeyEvent.VK_K)){
            cx += 1;
        }else if(input.isKeyDown(KeyEvent.VK_H)){
            cx -= 1;
        }
        if(input.isKeyDown(KeyEvent.VK_J)){
            cy += 1;
        }else if(input.isKeyDown(KeyEvent.VK_U)){
            cy -= 1;
        }
        
        
    }

}
