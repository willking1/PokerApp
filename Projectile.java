public class Projectile {

    private int type;
    private int x;
    private int y;
    private int dir;
    private int range;
    private int dist;
    private int width;
    private int height;
    private double angle;
    private Position targetPos;
    private boolean collided;
    private boolean posX, posY;

    public Projectile(int type, int x, int y, int dir, int targetX, int targetY) {
        height = 845;
        width = 1000;
        this.type = type;
        this.x = x;
        this.y = y;
        this.dir = dir;
        dist = 0;
        if(type == 2) {
            range = 15;
        } else {
            range = -1;
        }
        collided = false;
        targetPos = new Position(targetX, targetY);
        setAngle();
        System.out.println(angle);
        System.out.println("Pos x: " + posX);
        System.out.println("Pos y: " + posY);
        move(); //move once to get clear of head
    }

    public void setAngle() { //should be called when starting shoot, possibly recalculate later
        posX = false;
        posY = false;
        System.out.println("x: " + width/2 + " " + targetPos.getX());
        System.out.println("y: " + height/2 + " " + targetPos.getY());
        //targetpos stores the screen position, not the position in the map so I need to use the snake screen position which is just the middle of the screen to calculate the angle
        int xDiff = targetPos.getX() - width/2;
        int yDiff = height/2 - targetPos.getY();
        System.out.println("x: " + xDiff);
        System.out.println("y: " + yDiff);
        // System.out.println(yDiff + " " + xDiff);
        if(xDiff > 0) posX = true;
        if(yDiff > 0) posY = true;
        //conv xdiff/ydiff to int
        double tempX = xDiff + 0.0;
        double tempY = yDiff + 0.0;
        angle = Math.atan(tempX/tempY); //how to account for q4?
        System.out.println(angle);
    }

    public void move() {
        if(dist > 10) return;
        if(range != -1) {
            if(dist == range) {
                collide();
                return;
            }
        }

        //basic directional movement
        // if(dir == 1) x--;
        // if(dir == 2) x++;
        // if(dir == 3) y--;
        // if(dir == 4) y++;

        //trig calculations
        if(posY) {
            x -= Math.abs((int) (Math.cos(angle) + 0.5));
        } else {
            System.out.println(Math.abs((int) (Math.cos(angle) + 0.5)));
            x += Math.abs((int) (Math.cos(angle) + 0.5));
        }
        if(posX) {
            if((int) (Math.cos(angle) + 0.5) == 0) y++;
            y += Math.abs((int) (Math.cos(angle) + 0.5));
        } else {
            if((int) (Math.cos(angle) + 0.5) == 0) y--;
            y -= Math.abs((int) (Math.cos(angle) + 0.5));
        }
        dist++;
    }

    private void collide() {
        collided = true;
        //possibly do respective things here?
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getType() {return type;}
}