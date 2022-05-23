public class Projectile {

    private int type;
    private int x;
    private int y;
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
    private int speed;

    public Projectile(int type, int x, int y, int dir, int targetX, int targetY) {
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
        System.out.println(targetPos.getY() + " " + targetPos.getX());
        num = getNum();
        den = getDen();
        while(Math.abs(num/speed) > 2 || Math.abs(den/speed) > 2) {
            num/=2;
            den/=2;
        }
        System.out.println(num + " vs. " + den);
        move(); //move once to get clear of head
    }

    private double getNum() {
        System.out.println("D " + targetPos.getX() + " " + x);
        return (targetPos.getX() - height/2);
    }

    private double getDen() {
        System.out.println("D " + targetPos.getY() + " " + y);
        return (targetPos.getY() - width/2);
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

        //move with slope
        System.out.println("S: " + num/speed + " " + den/speed);
        x += ((num)/speed);
        y += ((den)/speed);

    }

    private void collide() {
        collided = true;
        //possibly do respective things here?
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getType() {return type;}
}