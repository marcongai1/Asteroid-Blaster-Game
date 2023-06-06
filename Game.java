import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Game extends JComponent{
    int shipX,shipY,lives,asteroidsHit;
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
        lives = 3;
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
                 //up
                if(e.getKeyCode()==38){  
                    if(shipY<50){}
                    else{
                        shipY--;
                        playerRectangle.setLocation(shipX, shipY);
                    }
                }
                //right
                if(e.getKeyCode()==39){ 
                    if(shipX>350){}
                    else{
                        shipX++; 
                        playerRectangle.setLocation(shipX, shipY);
                    } 
                }
                //down
                if(e.getKeyCode()==40){   
                    if(shipY>550){}
                    else{
                        shipY++;
                        playerRectangle.setLocation(shipX, shipY);
                    }
                }
                //left
                if(e.getKeyCode()==37){  
                    if(shipX<50){}
                    else{
                        shipX--; 
                        playerRectangle.setLocation(shipX, shipY);
                    }
                }
                //space bar shooting
                if(e.getKeyCode()==32){
                    shoot();
                }
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
    private void shoot(){
        int shipMidpointX = shipX + 25;
        int shipMidpointY = shipY + 25;    
        Projectile projectile = new Projectile(shipMidpointX, shipMidpointY);
        projectiles.add(projectile);    
    }
    private void checkForAsteroidCollisions(){
        for(int i = enemyRectangles.size()-1; i >= 0; i--)
        if(enemyRectangles.get(i).intersects(playerRectangle)){
            enemyRectangles.remove(i);
            asteroids.remove(i);
            lives--;
        }
    }
    private void generateNewAsteroid(){
        if(Math.random()*5 == 3){
            Asteroid asteroid = new Asteroid(this);
            asteroids.add(asteroid);
            Rectangle enemyRectangles = new Rectangle(asteroid.getAsteroidX(),asteroid.getAsteroidY(),50,50);
            enemyRectangles.add(enemyRectangles);
        }
    }
    private void removeAsteroid(int index){
        asteroids.get(index).setDestroyed(true);
        asteroids.remove(index);
        enemyRectangles.remove(index);
    }
    private void updateAsteroidLocation(){
        for(int i = asteroids.size()-1; i >= 0; i--){
            asteroids.get(i).updateAsteroid();
        }
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
