public class Projectile {

    private int type;
    private int x;
    private int y;
    private int dir;
    private int range;
    private int dist;
    private double angle;
    private Position targetPos;
    private boolean collided;
    private boolean posX, posY;

    public Projectile(int type, int x, int y, int dir, int targetX, int targetY) {
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

        move(); //move once to get clear of head
    }

    public void setAngle() { //should be called when starting shoot, possibly recalculate later
        posX = false;
        posY = false;
        int xDiff = targetPos.getX() - x;
        int yDiff = targetPos.getY() - y;
        System.out.println(yDiff + " " + xDiff);
        if(xDiff > 0) posX = true;
        if(yDiff > 0) posY = true;
        //conv xdiff/ydiff to int
        double tempX = xDiff + 0.0;
        double tempY = yDiff + 0.0;
        angle = Math.atan(tempX/tempY); //how to account for q4?
        System.out.println(angle);
    }

    public void move() {

        if(range != -1) {
            if(dist == range) {
                collide();
                return;
            }
        }

        //basic directional movement
        if(dir == 1) x--;
        if(dir == 2) x++;
        if(dir == 3) y--;
        if(dir == 4) y++;

        //trig calculations
        // if(posX) {
        //     x += Math.abs((int) (Math.cos(angle) + 0.5));
        // } else {
        //     x -= Math.abs((int) (Math.cos(angle) + 0.5));
        // }
        // if(posY) {
        //     y += Math.abs((int) (Math.cos(angle) + 0.5));
        // } else {
        //     y -= Math.abs((int) (Math.cos(angle) + 0.5));
        // }
        

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