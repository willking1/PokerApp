import java.io.*;

public class Manager {
    
    private CAL<ServerThread> clients;
    private int mapSize;
    private String mapID;
    private Game game;
    private int target;

    public Manager() {

        clients = new CAL<ServerThread>();
        mapSize = 50;
        mapID = "Map1";
        target = -1;

    }

    private void startGame() {

        char[][] map = new char[mapSize][mapSize];

        try {
            BufferedReader br = new BufferedReader(new FileReader("./resources/" + mapID + ".txt"));
            String line = "";
            String buffer = " ";
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(buffer);
                for(int j=0; j<currentLine.length; j++) {
                    map[i][j] = currentLine[j].charAt(0);
                }
                i++;
            }
            br.close();
            game = new Game(map);
            broadcast(game.compress());
            broadcast(target+"");
            broadcast("S"+clients.size());
            start();

        } catch (IOException e) { e.printStackTrace();}
    }

    public void add(ServerThread sT) {
        clients.add(sT);
    }

    public int addSnake(int x, int y, int dir) {
        return game.addSnake(x, y, dir+"");
    }

    public void broadcast(String msg) {
        for(int i=0; i<clients.size(); i++) {
            clients.get(i).send(msg);
        }
    }

    public void start() {
        if(clients.size() == target) {
            play();
        }
    }

    public int size() {
        return clients.size();
    }

    public void checkStart() { //temp
        if(clients.size() >= 1) {
            startGame();
        }
    }

    public void left(int id) {
        game.left(id);
    }

    public void right(int id) {
        game.right(id);
    }

    public void down(int id) {
        game.down(id);
    }

    public void up(int id) {
        game.up(id);
    }

    public void setTarget(int target) {
        this.target = target;
        broadcast("T" + target);
    }

    public void play() {
        int counter = 0;
        while(true) {
            counter++;
            game.move();
            if(counter == 5) {
                game.addBlock();
                counter = 0;
            }
            broadcast(game.compress());
            try { Thread.sleep(1000); } catch (Exception e) { System.out.println(e); }; //SLEEP
        }
    }

}