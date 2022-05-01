import java.io.*;

public class Game {

    private int snakeNum;
    private int[][] snakes;
    private int[][] tails;
    private char[][] map;
    private String splitChar, splitLine;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
        snakes = new int[4][3];
        tails = new int[4][2];
        for(int i = 0; i < snakes.length; i++) {
            snakes[i] = null;
        }
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
       for(int i = 0; i < snakes.length; i++) {
           if(snakes[i] != null) {
               int dir = snakes[i][2];
               if(dir == 1) snakes[i][0]--;
               else if(dir == 2) snakes[i][0]++;
               else if(dir == 3) snakes[i][1]--;
               else snakes[i][1]++;
               map[snakes[i][0]][snakes[i][1]] = Character.forDigit(i, 10);
               int[] nextTail = getNext(tails[i][0], tails[i][1]);
               map[tails[i][0]][tails[i][1]] = '+';
               tails[i] = nextTail;
           }
       }
    }

    public void addSnake() {
        snakes[snakeNum] = new int[]{4, 7, 2};
        tails[snakeNum] = new int[]{4, 2};
        map[snakes[snakeNum][0]][snakes[snakeNum][1]] = Character.forDigit(snakeNum, 10);
        System.out.println((char)(snakeNum));
        for(int i = snakes[snakeNum][1]-1; i >= snakes[snakeNum][1]-5; i--) {
            map[snakes[snakeNum][0]][i] = Character.forDigit(snakeNum, 10);
        }
        snakeNum++;
    }
}