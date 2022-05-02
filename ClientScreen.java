import javax.swing.JPanel;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ClientScreen extends JPanel implements KeyListener {

    //game
    private Game gameboard;
    private boolean started;
    private int id;

    //snake - temp?
    private int width;

    //network
    private String hostName;
    private int portNumber;
    private Socket serverSocket;
    private BufferedReader in;
    private PrintWriter out;

	public ClientScreen() throws IOException {
        id = -1;
		setLayout(null);
        setFocusable(true);
        gameboard = new Game(50);
        started = false;
        setFocusable(true);
        addKeyListener(this);

        hostName = "localhost"; 
		portNumber = 96;
		serverSocket = new Socket(hostName, portNumber);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        out = new PrintWriter(serverSocket.getOutputStream(), true);

        width = 25; //think this is good
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

        if(started) {

            char[][] currState = gameboard.getArr();
            g.drawRect(20, 20, 18*20, 18*20); //border
            for(int i = 0; i < currState.length; i++) {
                for(int j = 0; j < currState[0].length; j++) {
                    // g.drawRect(20+i*(width), 20+j*(10), (width), (width));
                    if(currState[i][j] == 'X') {
                        g.setColor(Color.RED);
                        g.fillRect(21+i*(width), 21+j*(width), (width), (width));
                        g.setColor(Color.BLACK);
                    } 
                    if(Character.isDigit(currState[i][j])) {
                        Integer.valueOf(currState[i][j]);
                        g.setColor(Color.GREEN);
                        g.fillRect(21+i*(width), 21+j*(width), (width), (width));
                        g.setColor(Color.BLACK);
                    } 
                }
            }
            // gameboard.move(); //TODO: patch out of bounds
        }
        
	}

	public Dimension getPreferredSize() {
		return new Dimension(700,700);
	}

    public void poll() throws IOException {
        while(true) {
            //check if new board is available or dehang?
            try {
                String input = in.readLine();
                gameboard.set(input);
                if(!started) {
                    id = gameboard.addSnake(6, 6, 1); //TEMP?
                    started = true;
                }
                
            } catch (Exception e) {System.out.println(e);}

            gameboard.move();

            out.println(gameboard.compress());

            repaint();

            try { Thread.sleep(1000); } catch (Exception e) { System.out.println(e); }; //SLEEP
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) { //why does this appear delayed?
        if(!started) return;
        int keyCode = e.getKeyCode();
        if(keyCode == 39) {
            gameboard.right(id);
        }
        if(keyCode == 37) {
            gameboard.left(id);
        }
        if(keyCode == 38) {
            gameboard.up(id);
        }
        if(keyCode == 40) {
            gameboard.down(id);
        }
    } 

    public void keyReleased(KeyEvent e) {}
}