
package engine.renderEngine.graphics;

import java.awt.image.BufferedImage;

import engine.debug.Debug;

/**
 *
 * @author emsahin
 */
public class SpriteSheet {
    private BufferedImage img;
    private int sx, sy;
    
    public SpriteSheet(Image img, int sx, int sy){
        this.img = img.getRawImage();
        this.sx = sx;
        this.sy = sy;
    }
    
    public BufferedImage getSpriteImage(int x, int y){
        try {
            return img.getSubimage(x*sx, y*sy, sx, sy);
        } catch (Exception e) {
            Debug.LogError(e.getMessage());
        }
        return null;
    }
    
    public Image getSprite(int arrX, int arrY){
         return new Image(getSpriteImage(arrX, arrY));
    }
}
