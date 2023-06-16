import javax.swing.JComponent;

public class PowerUp {
    int x, y;
    int yVel = (int)(Math.random()*10)+1;
    int xVel = (int)(Math.random()*10)+1;
    boolean collide;
    JComponent component;
    public PowerUp(JComponent component){
        this.component = component;
        y = 0;
        x = (int)(Math.random()*360)+20;
    }
    public int getPowerUpY(){
        return y;
    }
    public int getPowerUpX(){
        return x;
    }
    public void updatePowerUpPosition(){
        y += yVel;
        x += xVel;
    }
    public boolean isCollide(){
        return collide;
    }
    public void setCollide(boolean b){
        collide = true;
    }
    public void updatePowerUps(){
        y += yVel;
        x += xVel;
        if (y >= 600) {
            // Remove the power-up
        }
        else if (x <= 0 || x >= 380){
            if(x <= 0)
                x = 0;
            else
                x = 380;
            xVel = -xVel;
        }

        
    }
}
