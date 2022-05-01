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

    //snake - temp?
    private int width;

    //network
    private String hostName;
    private int portNumber;
    private Socket serverSocket;
    private BufferedReader in;
    private PrintWriter out;

	public ClientScreen() throws IOException {
		setLayout(null);
        setFocusable(true);
        gameboard = new Game(50);
        started = false;
        // gameboard.addSnake();
        // for(int i = 0; i < gameboard.getArr().length; i++) {
        //     for(int j = 0; j < gameboard.getArr()[0].length; j++) {
        //         System.out.print(gameboard.getArr()[i][j] + " ");
        //     } System.out.println();
        // }
        setFocusable(true);
        addKeyListener(this);

        hostName = "localhost"; 
		portNumber = 96;
		serverSocket = new Socket(hostName, portNumber);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        out = new PrintWriter(serverSocket.getOutputStream(), true);

        width = 50;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

        if(started) {

            char[][] currState = gameboard.getArr();
            g.drawRect(20, 20, 18*20, 18*20);
            for(int i = 0; i < currState.length; i++) { //STOP SCALING THIS WAY
                for(int j = 0; j < currState[0].length; j++) {
                    g.drawRect(20+i*(18*20/currState.length), 20+j*(18*20/currState.length), (18*20/currState.length), (18*20/currState.length));
                    if(currState[i][j] == 'X') {
                        g.setColor(Color.RED);
                        g.fillRect(21+i*(18*20/currState.length), 21+j*(18*20/currState.length), (18*20/currState.length), (18*20/currState.length));
                        g.setColor(Color.BLACK);
                    } 
                    if(Character.isDigit(currState[i][j])) {
                        Integer.valueOf(currState[i][j]);
                        g.setColor(Color.GREEN);
                        g.fillRect(21+i*(18*20/currState.length), 21+j*(18*20/currState.length), (18*20/currState.length), (18*20/currState.length));
                        g.setColor(Color.BLACK);
                    } 
                }
            }
            gameboard.move(); //TODO: patch out of bounds
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
                    gameboard.addSnake(); //TEMP?
                    started = true;
                }
                
            } catch (Exception e) {System.out.println(e);}

            out.println(gameboard.compress());

            repaint();

            try { Thread.sleep(100); } catch (Exception e) { System.out.println(e); }; //SLEEP
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) { //why does this appear delayed?
        if(!started) return;
        int keyCode = e.getKeyCode();
        if(keyCode == 39) {
            gameboard.right();
        }
        if(keyCode == 37) {
            gameboard.left();
        }
        if(keyCode == 38) {
            gameboard.up();
        }
        if(keyCode == 40) {
            gameboard.down();
        }
    } 

    public void keyReleased(KeyEvent e) {}
}