import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Game{
    int shipX,shipY;
    Rectangle playerRectangle;
    ArrayList projectiles,enemyRectangles,asteroids;
    JFrame frame;
    Timer timer;
    public Game(JFrame frame){
        this.frame = frame;
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();
        enemyRectangles = new ArrayList<>();
        shipX = 0;// finish statement
        shipY = 0;// finish statement
        playerRectangle = new Rectangle(shipX, shipY, 10, 10);// finish Statement

        frame.setFocusable(true);    
        frame.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }
        });

        timer = new Timer(10, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                //to be implemented
            }
        });
        timer.start();
    }
    public void updateEnemyRectanges(){

    }
    private void handleKeyPress(KeyEvent event){

    }
    private void shoot(){

    }
    private void checkForAsteroidCollisions(){

    }
    private void generateNewAsteroid(){

    }
    private void removeAsteroid(int index){

    }
    private void updateAsteroidLocation(){

    }
    private void checkProjectileCollisions(){

    }
    private void updateProjectiles(){

    }
    private void updateScreen(){

    }
    private void drawShip(Graphics graphics){

    }
    private void drawAsteroids(Graphics graphics){

    }
    private void drawProjectiles(Graphics graphics) {

    }
    private void setEndScreenText(Graphics graphics, String str){

    }
    private void setGameOver(Graphics graphics){

    }
    protected void paintComponent(Graphics graphics){
        
    } // from JComponent

}
