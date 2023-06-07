import javax.swing.JFrame;
public class Main {
    public static void main(String[]args){
        JFrame frame = new JFrame("Asteroid Shooter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Game(frame));
        frame.setVisible(true);
        }
    
}
