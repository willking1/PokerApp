import java.io.*;

public class Game {

    private int snakeNum;
    private int[] snake;
    private int[] tail;
    private char[][] map;
    private String splitChar, splitLine;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
        snake = new int[3];
        tail = new int[2];
        snakeNum = 0;
    }

    public void set(String comp) {
        int x = 0;
        int y = 0;

        for(int i=0; i<comp.length(); i++) {
            char c = comp.charAt(i);
            if(c+"" == splitChar) continue;
            if(c+"" == splitLine) {
                y++;
                x = 0;
            } else {
                map[x][y] = c;
            }
        }
    }

    public String compress() {
        String s = "";
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                s += map[i][j] + splitChar;
            }
            s += splitLine;
        }
        return s.substring(0, s.length()-2);
    }

    public void print() {
        for(int i=0; i<map.length; i++) {
            System.out.println();
            for(int j=0; j<map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }

    public char[][] getArr() {
        return map;
    }
    
    public int[] getNext(int x, int y) {
        if(map[x][y]==map[x+1][y]) return new int[]{x+1, y};
        else if(map[x][y]==map[x-1][y]) return new int[]{x-1, y};
        else if(map[x][y]==map[x][y+1]) return new int[]{x, y+1};
        else return new int[]{x, y-1};
    }

    public void move() {
        int dir = snake[2];
        if(dir == 1) snake[0]--;
        else if(dir == 2) snake[0]++;
        else if(dir == 3) snake[1]--;
        else snake[1]++;
        map[snake[0]][snake[1]] = '0';
        int[] nextTail = getNext(tail[0], tail[1]);
        map[tail[0]][tail[1]] = '+';
        tail = nextTail;
    }

    public void left() {
        int dir = snake[2];
        if(dir == 3 || dir == 4) snake[2] = 1;
    }

    public void right() {
        int dir = snake[2];
        if(dir == 3 || dir == 4) snake[2] = 2;
    }

    public void down() {
        int dir = snake[2];
        if(dir == 1 || dir == 2) snake[2] = 4;
    }

    public void up() {
        int dir = snake[2];
        if(dir == 1 || dir == 2) snake[2] = 3;
    }

    public void addSnake() {
        snake = new int[]{4, 7, 2};
        tail = new int[]{4, 2};
        map[snake[0]][snake[1]] = Character.forDigit(snakeNum, 10);
        System.out.println((char)(snakeNum));
        for(int i = snake[1]-1; i >= snake[1]-5; i--) {
            map[snake[0]][i] = Character.forDigit(snakeNum, 10);
        }
        snakeNum++;
    }
}