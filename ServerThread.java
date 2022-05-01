import java.io.*;
import java.net.*;

public class ServerThread implements Runnable {
    
    private Socket clientSocket;
    private Manager manager;
    private PrintWriter out;
    private BufferedReader in;

    public ServerThread(Socket clientSocket, Manager manager) throws IOException {
        this.clientSocket = clientSocket;
        this.manager = manager;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run() {
        //start listen
    }

    public void send(String msg) {
        out.println(msg);
        out.flush();
    }

}
