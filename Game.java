import java.io.*;

public class Game {

    private int startCount;
    private char[][] map;
    private String splitChar, splitLine;
    private CAL<Integer> dirs;
    private CAL<CAL<Tail>> snakes;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
        startCount = 4;
        snakes = new CAL<CAL<Tail>>();
        dirs = new CAL<Integer>();
    }

    public Game(int size) {
        map = new char[size][size];
        splitChar = " ";
        splitLine = "|";
        startCount = 4;
        snakes = new CAL<CAL<Tail>>();
        dirs = new CAL<Integer>();
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
            snakes.get(ind).add(new Tail(snakes.get(ind).get(i-1), -1, newX, newY));
        }
        dirs.add(dir);
        return ind; //returns client id
    }

    //Also slightly buzzin at the moment
    public void move() {
        for(int i=0; i<snakes.size(); i++) {
            //get each snake here
            snakes.get(i).get(0).setDir(dirs.get(i));
            map[snakes.get(i).get(snakes.get(i).size()-1).getX()][snakes.get(i).get(snakes.get(i).size()-1).getY()] = '+';
            for(int j=snakes.get(i).size()-1; j>=0; j--) {
                //moves each tail node
                
                snakes.get(i).get(j).move();
            }
            map[snakes.get(i).get(0).getX()][snakes.get(i).get(0).getY()] = Character.forDigit(i,10);
        }
    }

    /* WHO THE FUCK ONE INDEXED THIS
    dir 1 = left
    dir 2 = right
    dir 3 = up
    dir 4 = down
    */
    public void left(int id) {
        dirs.set(id, dirs.get(id)!=2 ? 1 : 2);
    }

    public void right(int id) {
        dirs.set(id, dirs.get(id)!=1 ? 2 : 1);
    }

    public void down(int id) {
        dirs.set(id, dirs.get(id)!=3 ? 4 : 3);
    }

    public void up(int id) {
        dirs.set(id, dirs.get(id)!=4 ? 3 : 4);
    }

    public char[][] getArr() {
        return map;
    }
}