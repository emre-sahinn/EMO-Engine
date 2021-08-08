/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.renderEngine.graphics;

import java.awt.image.BufferedImage;

import engine.soundEngine.ResourceLoader;

/**
 *
 * @author emsahin
 */
public class Image {
    private BufferedImage img;
    public Image(String path){
        img = ResourceLoader.loadImage(path);
    }
    
    public Image(BufferedImage img){
        this.img = img;
    }
    
    public BufferedImage getRawImage(){
        return img;
    }
    
    public int getWidth(){
        return img.getWidth(null);
    }
    
    public int getHeight(){
        return img.getHeight(null);
    }
}
