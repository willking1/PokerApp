import java.io.*;
import java.net.*;

public class ServerThread implements Runnable {
    
    private Socket clientSocket;
    private Manager manager;
    private PrintWriter out;
    private BufferedReader in;
    private int id;
    private boolean spawned;

    public ServerThread(Socket clientSocket, Manager manager) throws IOException {
        this.clientSocket = clientSocket;
        this.manager = manager;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run() {
        poll();
    }

    public boolean getSpawned() {
        return spawned;
    }

    public void poll() {
        while(true) {
            String input = "...";
            try {
                input = in.readLine();
                if(input == null) continue;
                String prefix = input.substring(0,4);
                input = input.substring(5, input.length());
                if(prefix.equals("clid")) {
                    id = Integer.parseInt(input);
                } else if(prefix.equals("move")) {
                    int dir = Integer.parseInt(input);
                    if(dir == 1) manager.left(id);
                    if(dir == 2) manager.right(id);
                    if(dir == 3) manager.up(id);
                    if(dir == 4) manager.down(id);
                } else if(prefix.equals("init")) {
                    String[] pos = input.split(" ");
                    id = manager.addSnake(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Integer.parseInt(pos[2])); //this line?
                    out.println(id);
                    spawned = true;
                } else if(prefix.equals("targ")) {
                    int target = Integer.valueOf(input);
                    manager.setTarget(target);
                }
            } catch (Exception e) {
               System.out.println("failed st " + e);
               break;
            }
        }
    }

    public void send(String msg) {
        out.println(msg);
        out.flush();
    }

}
