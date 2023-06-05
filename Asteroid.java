import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
    ArrayList<Rectangle> enemyRectangles;
    ArrayList<Asteroid> asteroids;
    ArrayList<Projectile> projectiles;
    JFrame frame;
    Timer timer;
    boolean gameOver;
    public Game(JFrame frame){
        this.frame = frame;
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();
        enemyRectangles = new ArrayList<>();
        shipX = 250;// finish statement
        shipY = 500;// finish statement
        playerRectangle = new Rectangle(shipX, shipY, 50, 50);// finish Statement

        frame.setFocusable(true);    
        frame.addKeyListener(new KeyAdapter(){
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
                if(gameOver){
                    //implement
                }
                else{
                    updateScreen();
                    frame.repaint();
                }
            }
        });
        timer.start();
    }
    public void updateEnemyRectanges(){
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            Rectangle enemyRectangle = enemyRectangles.get(i);
            int newX = asteroid.getAsteroidX(); 
            int newY = asteroid.getAsteroidY();  
            enemyRectangle.setLocation(newX, newY);
        }
    }
    private void handleKeyPress(KeyEvent event){
        //up
        if(event.getKeyCode()==38){  
            if(shipY<50){}
            else{
                shipY--;
                playerRectangle.setLocation(shipX, shipY);
            }
        }
        //right
        if(event.getKeyCode()==39){ 
            if(shipX>350){}
            else{
                shipX++; 
                playerRectangle.setLocation(shipX, shipY);
            } 
        }
        //down
        if(event.getKeyCode()==40){   
            if(shipY>550){}
            else{
                shipY++;
                playerRectangle.setLocation(shipX, shipY);
            }
        }
        //left
        if(event.getKeyCode()==37){  
            if(shipX<50){}
            else{
                shipX--; 
                playerRectangle.setLocation(shipX, shipY);
            }
        }
        //space bar shooting
        if(event.getKeyCode()==32){
            shoot();
        }
    }
    private void shoot(){
        int shipMidpointX = shipX + 25;
        int shipMidpointY = shipY + 25;    
        Projectile projectile = new Projectile(shipMidpointX, shipMidpointY);
        projectiles.add(projectile);    
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
