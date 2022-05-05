import java.awt.Graphics;

public class Tail {

    private Tail next;
    private int posX;
    private int posY;
    private int dir;
    private char[][] map;
    
    public Tail(Tail next, int dir, int posX, int posY, char[][] map) {
        this.map = map;
        this.next = next;
        this.dir = dir;
        this.posX = posX;
        this.posY = posY;
    }

    public void drawMe(Graphics g) {} //should we draw here?

    public int move() {
        if(dir == -1) {
            posX = next.getX();
            posY = next.getY();
        } else {
            //move head according to dir
            if(dir == 1) {
                if(posX-1<0) return -1;
                posX--;
            } else if(dir == 2) {
                if(posX+1>map.length) return -1;
                posX++;
            } else if(dir == 3) {
                if(posY-1<0) return -1;
                posY--;
            } else {
                if(posY+1>map.length) return -1;
                posY++;
            }
        }
        return 0;
    }

    public Tail next() {return next;}
    public int getX() {return posX;}
    public int getY() {return posY;}
    public int getDir() {return dir;}
    public void setDir(int dir) {this.dir = dir;}

}