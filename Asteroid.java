import javax.swing.JComponent;
public class Asteroid {
    int asteroidX = (int)(Math.random()*500)+50;
    int asteroidY;
    boolean isDestroyed;
    JComponent component;
    public Asteroid(JComponent component){
        this.component = component;
        asteroidX = (int)(Math.random()*500)+50;
        asteroidY = 0;
    }
    public int getAsteroidY(){
        return asteroidY;
    }
    public int getAsteroidX(){
        return asteroidX;
    }
    public boolean isDestroyed(){
        return isDestroyed;
    }
    public void setDestroyed(boolean isDestroyed){
        isDestroyed = true;
    }
    public void updateAsteroid(){
        if(isDestroyed){
            //remove asteroid
        }
        else{
            if(asteroidY > 600){
                asteroidY = 0;
                asteroidX = (int)(Math.random()*400);
            }
            else{
                asteroidY+=(int)(Math.random()*9)+2;
            }
        }
    }
}
