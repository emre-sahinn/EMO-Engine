/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.renderEngine.core;

import engine.renderEngine.core.helpers.*;
import engine.renderEngine.graphics.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author emsahin, 85530
 */
public class Drawer {

    private Window win;
    private BufferStrategy st;
    private Graphics g;

    private int cameraX, cameraY;

    public Drawer(Window win) {
        this.win = win;
        this.st = win.getBufferStrategy();
        this.g = st.getDrawGraphics();
    }

    public void drawImage(Image img, Vector2D pos, int width, int height, Boolean direction) {
        //g.drawImage(img.getRawImage(), x, y, null);
        g.drawImage(img.getRawImage(), pos.x+(direction?0:1)*width, pos.y, (direction?1:-1)*width, height,null);
    }

    public void drawString(String str, Vector2D pos) {
        g.drawString(str, pos.x, pos.y);
    }

    public void drawRect(Vector2D pos, Rectangle rect) {
        g.drawRect(pos.x, pos.y, rect.width, rect.height);
    }

    public void fillRect(Vector2D pos, Rectangle rect) {
        g.fillRect(pos.x, pos.y, rect.width, rect.height);
    }
    
    public void drawOval(Vector2D pos, Oval ov) {
    	g.drawOval(pos.x, pos.y, ov.width, ov.height);
    }
    
    public void fillOval(Vector2D pos, Oval ov) {
    	g.fillOval(pos.x, pos.y, ov.width, ov.height);
    }
    
    public void setColor(Color c) {
        g.setColor(c);
    }

    public void setCameraPos(int cx, int cy) {
        g.translate(-cameraX, -cameraY);
        cameraX = cx;
        cameraY = cy;
        g.translate(cameraX, cameraY);
    }

    public void moveCamera(int mx, int my) {
        cameraX += mx;
        cameraY += my;
    }
}
