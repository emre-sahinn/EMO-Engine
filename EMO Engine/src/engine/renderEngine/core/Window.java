package engine.renderEngine.core;

import engine.InputEngine.Input;
import engine.InputEngine.Mouse;
import engine.debug.Debug;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window extends Canvas {

    private static final long serialVersionUID = 1L;

    private static JFrame FRAME;
    private static int WIDTH, HEIGHT, BUFFER_SIZE;
    private static String TITLE;
    
    private Drawer drawer;
    private Input input = new Input();
    private Mouse mouse = new Mouse();

    int frames,ticks,time;
    public int lastFrames, lastTicks;
    private static boolean isRunning;
    private Thread loop;
    private GameManager gm;
    private final double UPDATE_SPEED = 60;

    public Window(String title, int width, int height, int bufferSize, GameManager gm) {
        this.gm = gm;
        Window.TITLE = title;
        Window.WIDTH = width;
        Window.HEIGHT = height;
        Window.BUFFER_SIZE = bufferSize;

        FRAME = new JFrame(TITLE);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setSize(size);
        setFocusable(true);
        FRAME.setSize(size);
        FRAME.add(this);
        FRAME.pack();
        FRAME.setResizable(false);
        FRAME.setLocationRelativeTo(null);
    }

    public void show() {
        this.createBufferStrategy(BUFFER_SIZE);
        isRunning = true;
        FRAME.setVisible(true);
        
        drawer = new Drawer(this);
        startInputListeners();
        gameLoop();
    }
    
    private void startInputListeners(){
        this.addKeyListener(input);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    //displays next buffer
    public void update() {
        if (!isRunning()) {
            Debug.LogError("Hata: Pencere olu�turulmad�.");
        }
        this.getBufferStrategy().show();
    }

    public void clear(Color ClearColor) {
        if (!isRunning()) {
            Debug.LogError("Hata: Pencere olu�turulmadan temizleme yap�lamaz.");
        }
        BufferStrategy st = this.getBufferStrategy();
        Graphics g = st.getDrawGraphics();
        g.setColor(ClearColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void close() {
        FRAME.dispose();
        System.exit(0);

    }

    public boolean isRunning() {
        return isRunning;
    }

    private void gameLoop() {
        loop = new Thread() {
            public void run() {
                double lastTime = System.nanoTime();
                double delta = 0;
                
                final double ns = 1e9 / UPDATE_SPEED;
                
                double start = System.currentTimeMillis();
                
                int next = 1;
                while (isRunning()) {   
                    double nowTime = System.nanoTime();
                    double now = (System.currentTimeMillis()-start)/1000;   
                    delta += (nowTime - lastTime) / ns;
                    lastTime = nowTime;
                    
                    while (delta >= 1) {
                        gm.update();
                        delta--;
                    }   
                    gm.render();
                    
                    if (now>=next) {
                        next++;
                        time++;
                        lastFrames=frames;
                        lastTicks = ticks;
                        //Debug.Log("FPS: " + lastFrames + " UPS: " + lastTicks);
                        frames=0;
                        ticks=0;
                    }
                }
            }
        };
        loop.start();
    }
    
    Drawer getDrawer(){
        return drawer;
    }
    
    public Input getInput(){
        return input;
    }
    
    public Mouse getMouse(){
        return mouse;
    }

}
