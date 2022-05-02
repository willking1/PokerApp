import java.io.*;

public class Game {

    private int snakeNum;
    private char[][] map;
    private String splitChar, splitLine;
    private int dir;
    private CAL<CAL<Tail>> snakes;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
        snakeNum = 0;
        snakes = new CAL<CAL<Tail>>();
    }

    public Game(int size) {
        map = new char[size][size];
        splitChar = " ";
        splitLine = "|";
        snakeNum = 0;
        snakes = new CAL<CAL<Tail>>();
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

    public void addSnake() {
        snakes.add(new CAL<Tail>());
    }

    public void move() {
       
    }

    public void left() {
 
    }

    public void right() {
        
    }

    public void down() {
    
    }

    public void up() {
  
    }

    public char[][] getArr() {
        return map;
    }
}