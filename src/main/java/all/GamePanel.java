package all;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // allocate a thread to run 
    Thread gameThread;

    // the fps at which the game is rendered
    final static int FPS = 60;

    Board board;

    // constructor
    public GamePanel() {
        //panel settings
        //this.setSize(Frame.WIDTH, Frame.HEIGHT);
        this.setBackground(Color.orange);
        this.setLocation(0, 0);
        // this.addKeyListener(keyHandler);
        //this.setLayout(null);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        
        // start the thread
        gameThread = new Thread(this);
        gameThread.start();

        // initialize the board
        board = new Board();
    }

    @Override
    public void run() {

        // calculations are in nanoseconds nextDrawTime is the next frame
        double drawInterval = 1e9 / FPS; 
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                // the target time minus current time
                double remainingTime = nextDrawTime - System.nanoTime();
                
                // avoiding negatives and convert from nano to milisecods
                remainingTime = Math.max(remainingTime, 0) / 1e6;

                // wait until next draw
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        board.draw(g);

        g.dispose();
    }
}
