package engine.renderEngine.core;

import java.awt.Color;
import java.util.ArrayList;

import engine.physicsEngine.objects.ObjectManager;

public class GameManager {

    private ArrayList<GameLevel> levels = new ArrayList<GameLevel>();
    private GameLevel currentLevel;
    private Window win;

    public void addLevel(GameLevel level) {
        levels.add(level);
    }

    public void flushLevels() {
        levels.clear();
    }

    public void enterLevel(int levelID, boolean doInit) {
        currentLevel = levels.get(levelID);
        if (doInit) {
            init();
        }
    }

    public Window createWindow(String title, int width, int height, int bufferSize) {
        win = new Window(title, width, height, bufferSize,this);
        return win;
    }

    void init() {
        if (isLevelOpen()) {
            currentLevel.init(win, this);
        }
    }

    void render() {
        if (isLevelOpen()) {
            win.clear(Color.black);
            
            currentLevel.render(win, win.getDrawer(), this);
            ObjectManager.render(win, win.getDrawer());
            
            win.update();
            win.frames++;
        }
    }

    void update() {
        if (isLevelOpen()) {
            currentLevel.update(win, this);
            ObjectManager.update(win, this);
            win.ticks++;
        }
    }

    private boolean isLevelOpen() {
        if (currentLevel == null) {
            return false;
        }
        return true;
    }
}
