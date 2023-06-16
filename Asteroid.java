import javax.swing.JComponent;
public class Asteroid {
    int asteroidX;
    int asteroidY;
    boolean isDestroyed;
    JComponent component;
    public Asteroid(JComponent component){
        this.component = component;
        asteroidY = 0;
        asteroidX = (int)(Math.random()*300) + 25;
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
                asteroidX = (int)(Math.random()*320) + 40;
            }
            else{
                asteroidY+=(int)(Math.random()*8);
            }
        }
    }
}
