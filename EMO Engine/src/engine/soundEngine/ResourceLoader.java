/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.soundEngine;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import engine.debug.Debug;

/**
 *
 * @author emsahin
 */
public class ResourceLoader {
    public static BufferedImage loadImage(String path){
        try{
            File file = new File(path);
            BufferedImage img = ImageIO.read(file);
            return img;
        }catch(Exception e){
            Debug.LogError(e.getMessage());
        }
        return null;
    }
}
