import java.io.*;

public class Game {

    public CAL<Tail> snake;
    private char[][] map;
    private String splitChar, splitLine;
    private int dir;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
    }

    public Game(int size) {
        map = new char[size][size];
        splitChar = " ";
        splitLine = "|";
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

    //Slightly buzzin at the moment
    public void addSnake(int x, int y) {
        snake = new CAL<Tail>();
        snake.add(new Tail(null, 2, x, y));
        map[x][y] = '0';
        for(int i = x-1; i > x-5; i--) {
            snake.add(new Tail(snake.get(snake.size()-1), -1, i, y));
            map[i][y] = '0';
        }
    }

    //Also slightly buzzin at the moment
    public void move() {
        //remove old tail from map so it isn't displayed.
        int tailX = snake.get(snake.size()-1).getX();
        int tailY = snake.get(snake.size()-1).getY();
        map[tailX][tailY] = '+';
        System.out.println("Tail: " + tailX + " " + tailY);
        //update tail positions
        for(int i = 0; i < snake.size(); i++) {
            snake.get(i).move();
        }
        //Add new head position to map so it gets displayed
        int headX = snake.get(0).getX();
        int headY = snake.get(0).getX();
        System.out.println("Head: " + headX + " " + headY);
        map[headX][headY] = '0';
    }

    /*
    dir 1 = left
    dir 2 = right
    dir 3 = up
    dir 4 = down
    */
    public void left() {
        if(snake.get(0).getDir()!=2) snake.get(0).setDir(1);
    }

    public void right() {
        if(snake.get(0).getDir()!=1) snake.get(0).setDir(2);
    }

    public void down() {
        if(snake.get(0).getDir()!=3) snake.get(0).setDir(4);
    }

    public void up() {
        if(snake.get(0).getDir()!=4) snake.get(0).setDir(3);
    }
}