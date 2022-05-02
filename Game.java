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
<<<<<<< HEAD
        snakeNum = 0;
        snakes = new CAL<CAL<Tail>>();
=======
>>>>>>> refs/remotes/origin/main
    }

    public Game(int size) {
        map = new char[size][size];
        splitChar = " ";
        splitLine = "|";
<<<<<<< HEAD
        snakeNum = 0;
        snakes = new CAL<CAL<Tail>>();
=======
>>>>>>> refs/remotes/origin/main
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

<<<<<<< HEAD
    public void addSnake() {
        snakes.add(new CAL<Tail>());
=======
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
>>>>>>> refs/remotes/origin/main
    }

    //Also slightly buzzin at the moment
    public void move() {
        
    }

    /*
    dir 1 = left
    dir 2 = right
    dir 3 = up
    dir 4 = down
    */
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