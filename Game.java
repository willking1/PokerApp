import java.io.*;

public class Game {

    private int snakeNum;
    private char[][] map;
    private String splitChar, splitLine;
    private int dir;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
        snakeNum = 0;
    }

    public Game(int size) {
        map = new char[size][size];
        splitChar = " ";
        splitLine = "|";
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
       
    }

    public void left() {
 
    }

    public void right() {
        
    }

    public void down() {
    
    }

    public void up() {
  
    }
}