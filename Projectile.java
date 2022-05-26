public class Projectile {

    private int type;
    private double x;
    private double y;
    private int dir;
    private int range;
    private int dist;
    private int width;
    private int height;
    private Position targetPos;
    private boolean collided;
    private boolean posX, posY;
    private double num;
    private double den;
    private int slope;
    private double speed;

    public Projectile(int type, double x, double y, int dir, int targetX, int targetY) {
        this.x = x;
        this.y = y;
        height = 845;
        width = 1000;
        this.type = type;
        this.dir = dir;
        dist = 0;
        if(type == 2) {
            range = 15;
            speed = 50;
        } else {
            range = -1;
            speed = 25;
        }
        collided = false;
        targetPos = new Position(targetX, targetY);
        num = getNum();
        den = getDen();
        while(Math.abs(num/speed) > 1.3 || Math.abs(den/speed) > 1.3) {
            num/=1.1;
            den/=1.1;
        }
        System.out.println(num + " vs. " + den);
        move(); //move twice to get clear of head
    }

    private double getNum() {
        return ((double)targetPos.getX() - (double)height/2);
    }

    private double getDen() {
        return ((double)targetPos.getY() - (double)width/2);
    }

    public void move() {
        if(dist > 10) return;
        if(range != -1) {
            if(dist == range) {
                collide();
                return;
            }
        }

        //move with slope
        x += ((num)/speed);
        y += ((den)/speed);

    }

    private void collide() {
        collided = true;
        //possibly do respective things here?
    }

    public int getX() {return (int)(x+0.5);}
    public int getY() {return (int)(y+0.5);}
    public int getType() {return type;}
}