import java.io.*;

public class Manager {
    
    private ServerThread[] clients;
    private int mapSize;

    public Manager() {

        clients = new ServerThread[0];
        mapSize = 18;

        char[][] map = new char[mapSize][mapSize];

        try {
            BufferedReader br = new BufferedReader(new FileReader("./resources/Map1.txt"));
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
        } catch (IOException e) { e.printStackTrace();}
    }

    public void add(ServerThread sT) {
        ServerThread[] newClients = new ServerThread[clients.length+1];
        for(int i=0; i<clients.length; i++) {
            newClients[i] = clients[i];
        }
        newClients[newClients.length-1] = sT;
        clients = newClients;
    }

    public void broadcast(String msg) {
        for(int i=0; i<clients.length; i++) {
            clients[i].send(msg);
        }
    }

    public int size() {
        return clients.length;
    }

}