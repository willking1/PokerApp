import java.awt.Graphics;

public class Tail {

    private Tail next;
    private int posX;
    private int posY;
    private int dir;
    private char[][] map;
    
    public Tail(Tail next, int dir, int posX, int posY) {
        this.next = next;
        this.dir = dir;
        this.posX = posX;
        this.posY = posY;
        System.out.println(toString());
    }

    public void drawMe(Graphics g) {} //should we draw here?

    public void move() {
        if(dir == -1) {
            posX = next.getX();
            posY = next.getY();
        } else {
            //move head according to dir
            if(dir == 1) {
                posX--;
            } else if(dir == 2) {
                posX++;
            } else if(dir == 3) {
                posY--;
            } else {
                posY++;
            }
        }
    }

    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getDir() {return dir;}
    public void setDir(int dir) {this.dir = dir;}

    public String toString() {
        return posX + " " + posY;
    }

}