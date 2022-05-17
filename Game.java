public class Game {

    private int startCount;
    private char[][] map;
    private char[][] wallMap;
    private String splitChar, splitLine;
    private CAL<String> dirs;
    private CAL<CAL<Tail>> snakes;
    private CAL<PowerUp> blocks;
    private CAL<Projectile> projectiles;

    public Game(char[][] map) {
        this.map = map;
        splitChar = " ";
        splitLine = "|";
        startCount = 4;
        snakes = new CAL<CAL<Tail>>();
        dirs = new CAL<String>();
        blocks = new CAL<PowerUp>();
        projectiles = new CAL<Projectile>();
        cloneMap();
    }

    private void cloneMap() {
        wallMap = new char[map.length][map[0].length];
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map.length; j++) {
                wallMap[i][j] = map[i][j];
            }
        }
    }

    public String getPosition(int id) {
        String s = "";
        if(snakes.size() == 0) {
            s +=  "-1000 -1000";
        } else if(snakes.get(id).size() == 0) {
            s +=  "-1000 -1000";
        } else {
            s += snakes.get(id).get(0).getX() + " " + snakes.get(id).get(0).getY();
        }
        s += "/";
        for(int i=0; i<snakes.size(); i++) {
            if(i == id || snakes.get(i).size() == 0) continue;
            s += snakes.get(i).get(0).getX() + " " + snakes.get(i).get(0).getY() + "/";
        }
        return s;
    }

    public Game(int size) {
        map = new char[size][size];
        splitChar = " ";
        splitLine = "|";
        startCount = 4;
        snakes = new CAL<CAL<Tail>>();
        dirs = new CAL<String>();
        blocks = new CAL<PowerUp>();
        cloneMap();
    }

    public CAL<CAL<Tail>> getSnakes() {
        return snakes;
    }

    public void set(String comp) {
        int x = 0;
        int y = 0;
        for(int i=0; i<comp.length(); i++) {
            char c = comp.charAt(i);
            if((c+"").equals(splitChar)) continue;
            if((c+"").equals(splitLine)) {
                y++;
                x = 0;
            } else {
                map[x][y] = c;
                x++;
            }
        }
    }

    public void addBlock() {
        int x = (int)(Math.random()*map.length); ;
        int y = (int)(Math.random()*map[0].length); ;
        while(map[x][y] != '+') {
            x = (int)(Math.random()*map.length); 
            y = (int)(Math.random()*map[0].length); 
        }
        blocks.add(new PowerUp(x, y));
        map[x][y] = 'X';
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

    public void addSnakeBlock(CAL<Tail> snake, PowerUp p, int index) {
        Tail tail = snake.get(snake.size()-1);
        //bases where the block gets added by the position of the block after the tail
        if(tail.next().getX()==tail.getX()+1) {
            snake.add(new Tail(tail, -1, tail.getX()-1, tail.getY(), map, p.getColor(), p.getType()));
            map[tail.getX()-1][tail.getY()] = p.getColor();
        } else if(tail.next().getX()==tail.getX()-1) {
            snake.add(new Tail(tail, -1, tail.getX()+1, tail.getY(), map, p.getColor(), p.getType()));
            map[tail.getX()+1][tail.getY()] = p.getColor();
        } else if(tail.next().getY()==tail.getY()+1) {
            snake.add(new Tail(tail, -1, tail.getX(), tail.getY()-1, map, p.getColor(), p.getType()));
            map[tail.getX()][tail.getY()-1] = p.getColor();
        } else {
            snake.add(new Tail(tail, -1, tail.getX(), tail.getY()+1, map, p.getColor(), p.getType()));
            map[tail.getX()][tail.getY()+1] = p.getColor();
        }
    }

    public void shoot(int id, int targetX, int targetY) {
        Tail last = snakes.get(id).get(snakes.get(id).size()-1); //gets last block in snake
        int powerUp = last.getPowerUp();
        System.out.println(powerUp);
        if(powerUp == -1) return; //check if last block is indeed powerup
        projectiles.add(new Projectile(powerUp, snakes.get(id).get(0).getX(), snakes.get(id).get(0).getY(), snakes.get(id).get(0).getDir(), targetX, targetY));
        for(int i = 0; i < projectiles.size(); i++) {
            System.out.println(projectiles.get(i).getX() + " " + projectiles.get(i).getY());
        }
        map[last.getX()][last.getY()] = '+'; //visual deletion
        snakes.get(id).remove(snakes.get(id).size()-1); //actual deletion
    }

    public int addSnake(int startX, int startY, String d) {
        int dir = Integer.parseInt(d);
        int ind = snakes.size();
        snakes.add(new CAL<Tail>());
        snakes.get(ind).add(new Tail(null, dir, startX, startY, map));
        map[startX][startY] = Character.forDigit(ind, 10);
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
            map[newX][newY] = 'G';
            snakes.get(ind).add(new Tail(snakes.get(ind).get(i-1), -1, newX, newY, map));
        }
        dirs.add(d);
        return ind; //returns client id
    }

    public void moveProjectiles() {
        for(int i=0; i<projectiles.size(); i++) {
            map[projectiles.get(i).getX()][projectiles.get(i).getY()] = '+';
            projectiles.get(i).move();

            //check collision here?

            map[projectiles.get(i).getX()][projectiles.get(i).getY()] = 'X';
        }
    }

    public String getProjectilePositions() {
        if(projectiles.size() == 0) return "";
        String s = "x";
        for(int i=0; i<projectiles.size(); i++) {
            //account for diff. projectile types here too
            s += projectiles.get(i).getX() + " " + projectiles.get(i).getY() + " " + projectiles.get(i).getType() + "/";
        }
        return s.substring(0, s.length()-1);
    }

    //Also slightly buzzin at the moment
    public void move() {
        //move the snakes
        for(int i=0; i<snakes.size(); i++) {
            //get each snake here 
            if(snakes.get(i).size() == 0) continue;
            snakes.get(i).get(0).setDir(Integer.parseInt(dirs.get(i))); //set direction of head - dirs is 0
            map[snakes.get(i).get(snakes.get(i).size()-1).getX()][snakes.get(i).get(snakes.get(i).size()-1).getY()] = '+';
            for(int j = snakes.get(i).size()-1; j >= 0; j--) {    
                snakes.get(i).get(j).move();
                if(j == 0) {
                    //Check to see if the new head position is on one of the food blocks
                    for(int k = 0; k < blocks.size(); k++)  {
                        if(blocks.get(k).getX() == snakes.get(i).get(j).getX() && blocks.get(k).getY() == snakes.get(i).get(j).getY()) {
                            addSnakeBlock(snakes.get(i), blocks.get(k), i);
                        }
                    }
                }
                map[snakes.get(i).get(j).getX()][snakes.get(i).get(j).getY()] = snakes.get(i).get(j).getColor();
            }
        }

        //check for collision
        for(int i=0; i<snakes.size(); i++) {
            if(snakes.get(i).size() != 0 && checkCollision(i)) {
                kill(i);
            }
        }
    }

    public String getLocs(int id) {
        String s = "";
        for(int i=0; i<snakes.size(); i++) {
            if( i == id ) continue;
            s += snakes.get(i).get(0).getX() + "/" + snakes.get(i).get(0).getY() + " ";
        }
        return s.substring(0, s.length()-1);
    }

    public void kill(int i) {
        for(int j=1; j<snakes.get(i).size(); j++) {
            map[snakes.get(i).get(j).getX()][snakes.get(i).get(j).getY()] = '+';
        }
        snakes.set(i, new CAL<Tail>());
    }

    public boolean checkCollision(int index) {
        Tail head = snakes.get(index).get(0);

        if(wallMap[head.getX()][head.getY()] == '#') {
            map[head.getX()][head.getY()] = '#';
            return true;
        }

        for(int i=0; i<snakes.size(); i++) {
            
            for(int j=0; j<snakes.get(i).size(); j++) {
                if(i == index && j == 0) continue;
                if((head.getX() == snakes.get(i).get(j).getX() && head.getY() == snakes.get(i).get(j).getY())) {
                    //collision detected
                    return true;
                }
            }
        }
        return false;
    }
    /* 
    dir 1 = left
    dir 2 = right
    dir 3 = up
    dir 4 = down
    */
    public void left(int id) {
        Tail head = snakes.get(id).get(0);
        if(head.getX()-1 != snakes.get(id).get(1).getX()) dirs.set(id, Integer.parseInt(dirs.get(id))!=2 ? 1+"" : 2+"");
    }

    public void right(int id) {
        Tail head = snakes.get(id).get(0);
        if(head.getX()+1 != snakes.get(id).get(1).getX()) dirs.set(id, Integer.parseInt(dirs.get(id))!=1 ? 2+"" : 1+"");
    }

    public void down(int id) {
        Tail head = snakes.get(id).get(0);
        if(head.getY()+1 != snakes.get(id).get(1).getY()) dirs.set(id, Integer.parseInt(dirs.get(id))!=3 ? 4+"" : 3+"");
    }

    public void up(int id) {
        Tail head = snakes.get(id).get(0);
        if(head.getY()-1 != snakes.get(id).get(1).getY()) dirs.set(id, Integer.parseInt(dirs.get(id))!=4 ? 3+"" : 4+"");
    }

    public char[][] getArr() {
        return map;
    }
}