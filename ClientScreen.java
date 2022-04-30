import javax.swing.JPanel;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.*;
public class ClientScreen extends JPanel implements ActionListener {
    private Game gameboard;
	public ClientScreen() {
		setLayout(null);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        char[][] currState = new char[50][50];
        g.setColor(Color.GREEN);
        g.fillRect(20, 20, 500, 500);
        for(int i = 0; i < currState.length; i++) {
            for(int j = 0; j < currState[0].length; i++) {
                g.drawRect(20+i*(500/currState.length), 20+j*(500/currState.length), (500/currState.length), (500/currState.length));
            }
        }
	}

	public Dimension getPreferredSize() {
		return new Dimension(1000,1000);
	}

	public void poll() throws IOException{
		// String hostName = "localhost"; 
		// int portNumber = 4323;
		// Socket serverSocket = new Socket(hostName, portNumber);
        // BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        // try {
        //     while(true) {
        //         gameboard.set(in.readLine());
        //     }
        // }catch(Exception e) {
        //     System.out.println(e);
        // }
		// serverSocket.close();
	}

    public void animate() {
        while(true) {
            repaint();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
