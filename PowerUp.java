public class PowerUp {
    private char color;
    private int type;
    private int posX;
    private int posY;
    public PowerUp(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        type = (int)(Math.random()*3);
        color = type == 0 ? 'B' : type == 1 ? 'R' : 'P';
    }
    public char getColor() {
        return color;
    }
    public int getX() {
        return posX;
    }
    public int getY() {
        return posY;
    }
}