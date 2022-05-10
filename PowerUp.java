public class PowerUp {
    private char color;
    private int type;
    private int snakePos;
    private int posX;
    private int posY;
    public PowerUp(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        type = (int)(Math.random()*3);
        color = type == 0 ? 'B' : type == 1 ? 'R' : 'P';
    }
    public PowerUp(int posX, int posY, int snakePos) {
        this.snakePos = snakePos;
        this.posX = posX;
        this.posY = posY;
        type = (int)(Math.random()*3);
        color = type == 0 ? 'B' : type == 1 ? 'R' : 'P';
    }
    //snakePos is the index within a snake of the powerup block
    public int getSnakePos() {
        return snakePos;
    }
    public void setSnakePos(int snakePos) {
        this.snakePos = snakePos;
    }
    public char getColor() {
        return color;
    }
    public int getType() {
        return type;
    }
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
}