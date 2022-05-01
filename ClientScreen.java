import javax.swing.JPanel;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.*;
public class ClientScreen extends JPanel implements KeyListener {
    private Game gameboard;
	public ClientScreen() {
		setLayout(null);
        gameboard = new Game(new char[][]{
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', '+', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        });
        gameboard.addSnake();
        for(int i = 0; i < gameboard.getArr().length; i++) {
            for(int j = 0; j < gameboard.getArr()[0].length; j++) {
                System.out.print(gameboard.getArr()[i][j] + " ");
            } System.out.println();
        }
        addKeyListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
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

	public Dimension getPreferredSize() {
		return new Dimension(700,700);
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
            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }    
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 39) {
            System.out.println("ss");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 39) {
            System.out.println("ss");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == 39) {
            System.out.println("ss");
        }
    }
}
