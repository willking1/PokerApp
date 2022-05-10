public class Projectile {

    private int type;
    private int x;
    private int y;
    private int dir;
    private int range;
    private int dist;
    private boolean collided;

    public Projectile(int type, int x, int y, int dir) {
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

        move(); //move once to get clear of head
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

        dist++;
    }

    private void collide() {
        collided = true;
        //possibly do respective things here?
    }

    public int getX() {return x;}
    public int getY() {return y;}
}