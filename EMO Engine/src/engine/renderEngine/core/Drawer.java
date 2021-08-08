/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.renderEngine.core;

import engine.renderEngine.graphics.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author emsahin
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

    public void drawImage(Image img, int x, int y, int sx, int sy, Boolean direction) {
        //g.drawImage(img.getRawImage(), x, y, null);
        g.drawImage(img.getRawImage(), x+(direction?0:1)*sx, y, (direction?1:-1)*sx,sy,null);
    }

    public void drawString(String str, int x, int y) {
        g.drawString(str, x, y);
    }

    public void drawRect(int x, int y, int w, int h) {
        g.drawRect(x, y, w, h);
    }
    
    public void fillRect(int x, int y, int w, int h) {
        g.fillRect(x, y, w, h);
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
