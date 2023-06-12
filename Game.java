import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
        shipX = 250;
        shipY = 500;
        lives = 3;
        playerRectangle = new Rectangle(shipX, shipY, 50, 50);

        frame.setFocusable(true);    
        frame.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {}

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
            public void keyReleased(KeyEvent e) {}
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
    public void updateEnemyRectangles(){
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
        for (int i = 0; i < asteroids.size(); i++) {
            Rectangle enermyRectangle = enemyRectangles.get(i);
    
            for (int j = 0; j < projectiles.size(); j++) {
                Projectile projectile = projectiles.get(j);
                int projectileX = projectile.getX();
                int projectileY = projectile.getY();
    
                // Check if projectile moves off the screen
                if (projectileY < 0) {
                    projectiles.remove(j);
                    j--;
                }
                // Check if projectile intersects with asteroid
                else if (enermyRectangle.intersects(projectileX,projectileY,10,10)) {
                    projectiles.remove(j);
                    removeAsteroid(i);
                    asteroidsHit++;
                    j--;
                    i--;
                    frame.repaint();
                    break;
                }
            }
        }
    }
    private void updateProjectiles(){
        ArrayList<Projectile> newProjectiles = new ArrayList<>();
        for(Projectile projectile : projectiles){
            projectile.updateProjectilePosition();
            newProjectiles.add(projectile);
        }
        projectiles = newProjectiles;
    }
    private void updateScreen(){
        checkForAsteroidCollisions();
        updateAsteroidLocation();
        generateNewAsteroid();
        updateProjectiles();
        checkProjectileCollisions();
    }
    private void drawShip(Graphics graphics) {
        int shipX = 100;  // X-coordinate of the ship's position
        int shipY = 100;  // Y-coordinate of the ship's position
        int shipWidth = 50;  // Width of the ship
        int shipHeight = 50;  // Height of the ship
    
        graphics.setColor(Color.BLUE);  
        graphics.fillRect(shipX, shipY, shipWidth, shipHeight); 
    }

    private void drawAsteroids(Graphics graphics) {
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            if (!asteroid.isDestroyed()) {
                graphics.setColor(Color.WHITE);
                graphics.fillOval(asteroid.getAsteroidX(), asteroid.getAsteroidY(), 50, 50);
            }
        }
        updateEnemyRectangles();
    }
    private void drawProjectiles(Graphics graphics) {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(projectile.getX(), projectile.getY(), 10, 10);
        }
    }
    
    private void setEndScreenText(Graphics graphics, String str) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = graphics.getFontMetrics();
        int textWidth = fm.stringWidth(str);
        int x = (getWidth() - textWidth) / 2;
        int y = getHeight() / 2;
        graphics.drawString(str, x, y);
    }
    
    private void setGameOver(Graphics graphics) {
        setEndScreenText(graphics, "Game Over");
    }
    
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawAsteroids(graphics);
        drawProjectiles(graphics);
        drawShip(graphics);
    
        if (gameOver) {
            setGameOver(graphics);
        }
    }
    

}
