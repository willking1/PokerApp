import javax.swing.JFrame;
import java.io.*;

public class Client {
    public static void main(String args[]) throws IOException {
		JFrame frame = new JFrame("Client");
		ClientScreen sc = new ClientScreen();
		frame.add(sc);
		// frame.setSize(500, 500);
    	// frame.setLocation(5, 5);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		// sc.poll();
        // sc.animate();
    }
}