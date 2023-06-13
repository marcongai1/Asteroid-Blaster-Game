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

public class Game extends JComponent {
    int shipX, shipY, lives, asteroidsHit;
    Rectangle playerRectangle;
    ArrayList<Rectangle> enemyRectangles;
    ArrayList<Asteroid> asteroids;
    ArrayList<Projectile> projectiles;
    JFrame frame;
    Timer timer;
    boolean gameOver;
    boolean movingDown,movingLeft,movingRight,movingUp;
    int remainingTime;
    int timeMS;

    public Game(JFrame frame) {
        this.frame = frame;
        asteroids = new ArrayList<>();
        projectiles = new ArrayList<>();
        enemyRectangles = new ArrayList<>();
        shipX = 250;
        shipY = 500;
        lives = 3;
        playerRectangle = new Rectangle(shipX, shipY, 50, 50);

        frame.setFocusable(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // Movement
                if (keyCode == KeyEvent.VK_UP) {
                    movingUp = true;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    movingDown = true;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    movingLeft = true;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    movingRight = true;
                }

                // Shooting
                if (keyCode == KeyEvent.VK_SPACE) {
                    shoot();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // Movement
                if (keyCode == KeyEvent.VK_UP) {
                    movingUp = false;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    movingDown = false;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    movingLeft = false;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    movingRight = false;
                }
            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (gameOver) {
                    // game over
                } else {
                    timeMS++;
                    if(timeMS%1000==0){
                        remainingTime--;
                        if (remainingTime <= 0) {
                            remainingTime = 0;
                        }
                    }
                    updateScreen();
                    frame.repaint();
                }
            }
        });
        timer.start();
    }

    public void updateEnemyRectangles() {
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            Rectangle enemyRectangle = enemyRectangles.get(i);
            int newX = asteroid.getAsteroidX();
            int newY = asteroid.getAsteroidY();
            enemyRectangle.setLocation(newX, newY);
        }
    }

    private void shoot() {
        int shipMidpointX = shipX + 20;
        int shipMidpointY = shipY;
        Projectile projectile = new Projectile(shipMidpointX, shipMidpointY);
        projectiles.add(projectile);
    }

    private void checkForAsteroidCollisions() {
        for (int i = enemyRectangles.size() - 1; i >= 0; i--) {
            if (enemyRectangles.get(i).intersects(playerRectangle)) {
                enemyRectangles.remove(i);
                asteroids.remove(i);
                lives--;
            }
        }
    }

    private void generateNewAsteroid() {
        double rand = Math.random();
        if (rand < 0.01) { 
            Asteroid asteroid = new Asteroid(this);
            asteroids.add(asteroid);
            Rectangle enemyRectangle = new Rectangle(asteroid.getAsteroidX(), asteroid.getAsteroidY(), 50, 50);
            enemyRectangles.add(enemyRectangle);
        }
    }

    private void removeAsteroid(int index) {
        asteroids.get(index).setDestroyed(true);
        asteroids.remove(index);
        enemyRectangles.remove(index);
    }

    private void updateAsteroidLocation() {
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            asteroids.get(i).updateAsteroid();
        }
    }

    private void checkProjectileCollisions() {
        for (int i = 0; i < asteroids.size(); i++) {
            Rectangle enemyRectangle = enemyRectangles.get(i);

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
                else if (enemyRectangle.intersects(projectileX, projectileY, 10, 10)) {
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

    private void updateProjectiles() {
        ArrayList<Projectile> newProjectiles = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.updateProjectilePosition();
            newProjectiles.add(projectile);
        }
        projectiles = newProjectiles;
    }

    private void updateScreen() {
        //Ship movement
        if (movingUp && shipY > 0) {
            shipY -= 5;
        }
        if (movingDown && shipY < getHeight() - playerRectangle.height) {
            shipY += 5;
        }
        if (movingLeft && shipX > 0) {
            shipX -= 5;
        }
        if (movingRight && shipX < getWidth() - playerRectangle.width) {
            shipX += 5;
        }
        playerRectangle.setLocation(shipX, shipY);
        checkForAsteroidCollisions();
        updateAsteroidLocation();
        generateNewAsteroid();
        updateProjectiles();
        checkProjectileCollisions();
    }

    private void drawShip(Graphics graphics) {
        int shipWidth = 50;  
        int shipHeight = 50;  

        int[] xPoints = {shipX, shipX + shipWidth, shipX + shipWidth / 2};  
        int[] yPoints = {shipY + shipHeight, shipY + shipHeight, shipY};  

        if (lives == 3) {
            graphics.setColor(Color.BLUE);
        } else if (lives == 2) {
            graphics.setColor(Color.GREEN);
        } else if (lives == 1) {
            graphics.setColor(Color.RED);
        }
        else
            gameOver = true;

        graphics.fillPolygon(xPoints, yPoints, 3); 
    }


    private void drawAsteroids(Graphics graphics) {
        updateEnemyRectangles();
        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);
            if (!asteroid.isDestroyed()) {
                graphics.setColor(Color.WHITE);
                graphics.fillOval(asteroid.getAsteroidX(), asteroid.getAsteroidY(), 50, 50);
            }
        }
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
        if (lives == 0) {
            setEndScreenText(graphics, "ALL LIVES LOST, YOU LOSE!");
        } else if (asteroids.size() == 0) {
            setEndScreenText(graphics, "ALL ASTEROIDS DESTROYED, YOU WIN!");
        } else {
            setEndScreenText(graphics, "OUT OF TIME, YOU LOSE!");
        }
        
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial", Font.BOLD, 36));
        FontMetrics fm = graphics.getFontMetrics();
        int textWidth = fm.stringWidth("GAME OVER!");
        int x = (getWidth() - textWidth) / 2;
        int y = getHeight() / 2 + 50;
        graphics.drawString("GAME OVER!", x, y);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //Displays Time
        String timeString = "Time: " + remainingTime + "s";
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = graphics.getFontMetrics();
        int textWidth = fm.stringWidth(timeString);
        int x = getWidth() - textWidth - 10;
        int y = 20;
        graphics.drawString(timeString, x, y);
        
        //Displays Lives
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        graphics.drawString("Lives: " + lives, 10, 20);

        //Displays Score
        graphics.drawString("Score: " + asteroidsHit, 10, 40);
        
        drawAsteroids(graphics);
        drawProjectiles(graphics);
        drawShip(graphics);
        

        if (gameOver) {
            setGameOver(graphics);
        }
    }
}
