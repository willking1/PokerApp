import javax.swing.JPanel;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.*;
@SuppressWarnings("serial")
public class ClientScreen extends JPanel implements ActionListener {
    private Game gameboard;
	public ClientScreen() {
		setLayout(null);
        gameboard = new Game(50, 50);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        
	}

	public Dimension getPreferredSize() {
		return new Dimension(800,600);
	}

	public void poll() throws IOException{
		String hostName = "localhost"; 
		int portNumber = 4323;
		Socket serverSocket = new Socket(hostName, portNumber);
		serverSocket.close();
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
