import javax.swing.JPanel;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ClientScreen extends JPanel implements KeyListener {

    private Game gameboard;
    private boolean started;

	public ClientScreen() throws IOException {
		setLayout(null);
        setFocusable(true);
        gameboard = new Game(18);
        started = false;
        // gameboard.addSnake();
        // for(int i = 0; i < gameboard.getArr().length; i++) {
        //     for(int j = 0; j < gameboard.getArr()[0].length; j++) {
        //         System.out.print(gameboard.getArr()[i][j] + " ");
        //     } System.out.println();
        // }
        setFocusable(true);
        addKeyListener(this);

        // poll();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

        if(started) {
            char[][] currState = gameboard.getArr();
            g.drawRect(20, 20, 18*20, 18*20);
            for(int i = 0; i < currState.length; i++) {
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
            gameboard.move();
        }
        
	}

	public Dimension getPreferredSize() {
		return new Dimension(700,700);
	}

	public void poll() throws IOException{
		String hostName = "localhost"; 
		int portNumber = 96;
		Socket serverSocket = new Socket(hostName, portNumber);
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        try {
            while(true) { // hangs on loop not read
                String input = in.readLine();
                System.out.println("Input: " + input);
                gameboard.set(input);
                gameboard.addSnake(); //TEMP
                started = true;
                // repaint(); //necessary?
            }
        }catch(Exception e) {
            System.out.println(e);
        }
		serverSocket.close();
	}

    public void animate() { //not calling
        while(true) {
            repaint();
            // try {
            //     Thread.sleep(200);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }    
            if(false) break;
        }
        
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
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