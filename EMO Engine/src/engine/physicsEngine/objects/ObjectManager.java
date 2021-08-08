package engine.physicsEngine.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import engine.renderEngine.core.Drawer;
import engine.renderEngine.core.GameManager;
import engine.renderEngine.core.Window;

public class ObjectManager {
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public static void addObject(GameObject go) {
		objects.add(go);
	}
	
	public static void removeObject(GameObject go) {
		objects.remove(go);
	}
	
	public static void clearObjects() {
		objects.clear();
	}
	
	public static void update(Window win, GameManager gm) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject go = objects.get(i);
			if(go.isDestroyed) {
				objects.remove(go);
			}else {
				go.update(win, gm);
			}
		}
	}
	
	public static void render(Window win, Drawer d) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject go = objects.get(i);
			go.render(win, d);
		}
	}
}
