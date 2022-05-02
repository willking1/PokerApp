import java.io.*;

public class Game {

    private int startCount;
    private char[][] map;
    private String splitChar, splitLine;
    private CAL<Integer> dir;
    private CAL<CAL<Tail>> snakes;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
        startCount = 3;
        snakes = new CAL<CAL<Tail>>();
        dir = new CAL<Integer>();
    }

    public Game(int size) {
        map = new char[size][size];
        splitChar = " ";
        splitLine = "|";
        startCount = 3;
        snakes = new CAL<CAL<Tail>>();
        dir = new CAL<Integer>();
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

    public int addSnake(int startX, int startY, int dir) {
        int ind = snakes.size();
        snakes.add(new CAL<Tail>());
        snakes.get(ind).add(new Tail(null, dir, startX, startY));
        map[startX][startY] = Character.forDigit(ind,10);
        for(int i=1; i<startCount; i++) {
            int newX, newY;
            newX = startX;
            newY = startY;
            if(dir == 1) {
                newX = startX+i;
            } else if(dir == 2) {
                newX = startX-i;
            } else if(dir == 3) {
                newY = startY-i;
            } else {
                newY = startY+i;
            }
            map[newX][newY] = Character.forDigit(ind,10);
            snakes.get(ind).add(new Tail(snakes.get(ind).get(i-1), -1, startX, startY));
        }
        return ind; //returns client id
    }

    //Also slightly buzzin at the moment
    public void move() {
        for(int i=0; i<snakes.size(); i++) {
            //get each snake here
            for(int j=0; j<snakes.get(i).size(); j++) {
                //moves each tail node
                map[snakes.get(i).get(j).getX()][snakes.get(i).get(j).getY()] = '+';
                snakes.get(i).get(j).move();
                map[snakes.get(i).get(j).getX()][snakes.get(i).get(j).getY()] = Character.forDigit(i,10);
            }
        }
    }

    /* WHO THE FUCK ONE INDEXED THIS
    dir 1 = left
    dir 2 = right
    dir 3 = up
    dir 4 = down
    */
    public void left(int id) {
        dir.set(id, dir.get(id)!=2 ? 1 : 2);
    }

    public void right(int id) {
        dir.set(id, dir.get(id)!=1 ? 2 : 1);
    }

    public void down(int id) {
        dir.set(id, dir.get(id)!=3 ? 4 : 3);
    }

    public void up(int id) {
        dir.set(id, dir.get(id)!=4 ? 3 : 4);
    }

    public char[][] getArr() {
        return map;
    }
}