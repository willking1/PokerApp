import java.io.*;
import java.net.*;

public class Server {

    private static Manager manager;

    public static void main(String[] args) throws IOException{

        manager = new Manager();

        int portNumber = 96;
        ServerSocket serverSocket = new ServerSocket(portNumber);

        while(true) {

            //wait for connection
            Socket clientSocket = serverSocket.accept();

            ServerThread sT = new ServerThread(clientSocket, manager);
            Thread thread = new Thread(sT);
            thread.start();
            manager.add(sT);

            System.out.println("\nCurrent clients: " + manager.size());

            // manager.broadcast("test");
            
            manager.checkStart(); //temp

        }


    }
}