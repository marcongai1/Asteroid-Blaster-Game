public class Projectile {
    int xPosition, yPosition;
    final static int SPEED = 6;
    public Projectile(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    public int getX(){
        return xPosition;
    }
    public int getY(){
        return yPosition;
    }
    public void updateProjectilePosition(){
        yPosition -= SPEED;
    }
}
