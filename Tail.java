import java.awt.Graphics;

public class Tail {

    private Tail next;
    private int posX;
    private int posY;
    private int dir;
    
    public Tail(Tail next, int dir) {
        this.next = next;
        this.dir = dir;
    }

    public void drawMe(Graphics g) {}

    public void move(int dir) {
        if(dir == -1) {
            posX = next.getX();
            posY = next.getY();
        } else {
            //move head according to dir
        }
    }

    public int getX() {return posX;}
    public int getY() {return posY;}
    public void setDir(int dir) {this.dir = dir;}

}
