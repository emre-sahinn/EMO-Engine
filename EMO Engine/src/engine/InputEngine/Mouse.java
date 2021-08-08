package engine.InputEngine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import engine.debug.Debug;

public class Mouse implements MouseListener, MouseMotionListener {

    private static ArrayList<Integer> PressedButtons = new ArrayList<Integer>();
    private static ArrayList<Integer> DownButtons = new ArrayList<Integer>();
    private static int x, y;

    @Override
    public void mousePressed(MouseEvent e) {
        int key = e.getButton();
        if (DownButtons.indexOf(key) == -1) {
            PressedButtons.add(key);
            DownButtons.add(key);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int key = e.getButton();
        if (PressedButtons.indexOf(key) != -1) {
            PressedButtons.remove(PressedButtons.indexOf(key));
        }

        if (DownButtons.indexOf(key) != -1) {
            DownButtons.remove(DownButtons.indexOf(key));
        }
    }

    public boolean isButtonPressed(int key) {
        if (PressedButtons.indexOf(key) != -1) {
            PressedButtons.remove(PressedButtons.indexOf(key));
            return true;
        }
        return false;
    }

    public boolean isButtonDown(int key) {
        return DownButtons.indexOf(key) != -1;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
