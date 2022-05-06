import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ClientScreen extends JPanel implements KeyListener, ActionListener {

    //game
    private Game gameboard;
    private boolean started;
    private int id;
    private int screen;
    private int target;

    //snake - temp?
    private int width;

    //network
    private String hostName;
    private int portNumber;
    private Socket serverSocket;
    private BufferedReader in;
    private PrintWriter out;

    //Graphical Components
    private JLabel titleLabel;
    private JLabel playerSelect;
    private JButton twoPlayerB;
    private JButton threePlayerB;
    private JButton fourPlayerB;
    private JLabel waitingLabel;

	public ClientScreen() throws IOException {
        id = -1;
		setLayout(null);
        setFocusable(true);
        gameboard = new Game(50);
        started = false;
        setFocusable(true);
        addKeyListener(this);

        hostName = "10.210.97.91"; 
		portNumber = 96;
		serverSocket = new Socket(hostName, portNumber);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        out = new PrintWriter(serverSocket.getOutputStream(), true);

        width = 18; 
        //Graphical Componenets
        screen = 2;
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 67));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setBounds(276, 34, 450, 85);
        titleLabel.setText("Snake Royale");
        this.add(titleLabel);
        playerSelect = new JLabel();
        playerSelect.setFont(new Font("Arial", Font.BOLD, 20));
        playerSelect.setHorizontalAlignment(SwingConstants.LEFT);
        playerSelect.setBounds(359, 232, 263, 30);
        playerSelect.setText("Select number of players:");
        this.add(playerSelect);
        twoPlayerB = new JButton();
        twoPlayerB.setFont(new Font("Arial", Font.BOLD, 20));
        twoPlayerB.setHorizontalAlignment(SwingConstants.CENTER);
        twoPlayerB.setBounds(396, 305, 200, 150);
        twoPlayerB.setText("Two Players");
        this.add(twoPlayerB);
        twoPlayerB.addActionListener(this);   
        threePlayerB = new JButton();
        threePlayerB.setFont(new Font("Arial", Font.BOLD, 20));
        threePlayerB.setHorizontalAlignment(SwingConstants.CENTER);
        threePlayerB.setBounds(248, 542, 200, 150);
        threePlayerB.setText("Three Players");
        this.add(threePlayerB);
        threePlayerB.addActionListener(this);
        fourPlayerB = new JButton();
        fourPlayerB.setFont(new Font("Arial", Font.BOLD, 20));
        fourPlayerB.setHorizontalAlignment(SwingConstants.CENTER);
        fourPlayerB.setBounds(545, 542, 200, 150);
        fourPlayerB.setText("Four Players");
        this.add(fourPlayerB);
        fourPlayerB.addActionListener(this);
        fourPlayerB.addActionListener(this);
        waitingLabel = new JLabel();
        waitingLabel.setFont(new Font("Arial", Font.BOLD, 31));
        waitingLabel.setHorizontalAlignment(SwingConstants.LEFT);
        waitingLabel.setBounds(238, 47, 600, 30);
        waitingLabel.setText("Waiting for all snakes to connect...");
        this.add(waitingLabel);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        /*Screens
        0 = game screen
        1 = menu screen
        2 = waiting screen
        */
        if(screen == 0) {
            titleLabel.setVisible(false);
            playerSelect.setVisible(false);
            twoPlayerB.setVisible(false);
            threePlayerB.setVisible(false);
            fourPlayerB.setVisible(false);
            waitingLabel.setVisible(false);
            if(started) {
                char[][] currState = gameboard.getArr();
                // g.drawRect(20, 20, 18*20, 18*20); //border
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
                        if(currState[i][j] == 'G') {
                            g.setColor(Color.GREEN);
                            g.fillRect(21+i*(width), 21+j*(width), (width), (width));
                            g.setColor(Color.BLACK);
                        }
                        if(currState[i][j] == 'R') {
                            g.setColor(Color.RED);
                            g.fillRect(21+i*(width), 21+j*(width), (width), (width));
                            g.setColor(Color.BLACK);
                        }
                        if(currState[i][j] == 'B') {
                            g.setColor(Color.BLUE);
                            g.fillRect(21+i*(width), 21+j*(width), (width), (width));
                            g.setColor(Color.BLACK);
                        }
                        if(currState[i][j] == 'P') {
                            g.setColor(Color.MAGENTA);
                            g.fillRect(21+i*(width), 21+j*(width), (width), (width));
                            g.setColor(Color.BLACK);
                        }
                        if(currState[i][j] == '#') {
                            g.setColor(Color.BLACK);
                            g.fillRect(21+i*(width), 21+j*(width), (width), (width));
                        }
                    } 
                }
            }
        }
        if(screen == 1) {
            titleLabel.setVisible(true);
            playerSelect.setVisible(true);
            twoPlayerB.setVisible(true);
            threePlayerB.setVisible(true);
            fourPlayerB.setVisible(true);
            waitingLabel.setVisible(false);
        }
        if(screen == 2) {
            titleLabel.setVisible(false);
            playerSelect.setVisible(false);
            twoPlayerB.setVisible(false);
            threePlayerB.setVisible(false);
            fourPlayerB.setVisible(false);
            waitingLabel.setVisible(true);
            try {
                BufferedImage img = ImageIO.read(new URL("https://media.australian.museum/media/dd/images/Some_image.width-800.139634f.jpg"));
                g.drawImage(img, 100, 120, null);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
	}

    public void drawGame(Graphics g) {
        CAL<CAL<Tail>> snakes = gameboard.getSnakes();
        if(snakes.get(id).size() == 0) return;
        int x = snakes.get(id).get(0).getX();
        int y = snakes.get(id).get(0).getY();
        char[][] currState = gameboard.getArr();
        for(int i=x; i<x+30; i++) {
            for(int j=y; j<y+30; j++) {
                
            }
        }
    }

	public Dimension getPreferredSize() {
		return new Dimension(1000,1000);
	}

    public void poll() throws IOException {
        //every 15 loops it will add a new block
        while(true) {
            //check if new board is available or dehang?
            try {
                String input = in.readLine();
                gameboard.set(input); //not error
                if(!started) {
                    target = Integer.valueOf(in.readLine());
                    int size = Integer.valueOf(in.readLine().substring(1, 2));
                    int startLoc = size-1;
                    screen = 1;
                    //Checks if the current client is the first client to connect and puts it in the menu screen if it is
                    if(size == 1) {
                        repaint();
                        //waits for the client to select number of players, which will change the target number
                        while(target == -1) {
                            String temp = in.readLine();
                            String targetStr = temp.charAt(0)=='T' ? temp.substring(1,2) : "-1";
                            target = Integer.valueOf(targetStr);
                        }
                    }
                    screen = 2;
                    repaint();
                    //waits for all the clients to connect before calling addSnake
                    while(size < target) {
                        String sizeStr = "";
                        while(sizeStr.length() == 0 || sizeStr.charAt(0) != 'S') {
                            System.out.println("Size: " + sizeStr);
                            sizeStr = in.readLine();
                        }
                        size = Integer.valueOf(sizeStr.substring(1, 2));
                    }
                    screen = 0;
                    //Prints the init statement to the serverthread which uses startLoc to know where to spawn the snake
                    addSnake(startLoc);
                    String idStr = in.readLine();
                    while(!Character.isDigit(idStr.charAt(0))) {
                        idStr = in.readLine();
                    }
                    id = Integer.valueOf(idStr);
                    started = true;
                }
            } catch (Exception e) {System.out.println("check"); System.out.println(e.getMessage()); break;} 
           repaint();
        }
    }

    public void addSnake(int loc) {
        int x, y, dir;
        if(loc == 0) {
            x = 4;
            y = 4;
            dir = 2;
        } else if(loc == 1) {
            x = 20;
            y = 4;
            dir = 2;
        } else if(loc == 2) {
            x = 4;
            y = 20;
            dir = 2;
        } else {
            x = 30;
            y = 30;
            dir = 1;
        }
        out.println("init " + x + " " + y + " " + dir); 
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) { //why does this appear delayed?
        if(!started) return;
        int keyCode = e.getKeyCode();
        if(keyCode == 39) {
            out.println("move 4");
        }
        if(keyCode == 37) {
            out.println("move 3");
        }
        if(keyCode == 38) {
            out.println("move 1");
        }
        if(keyCode == 40) {
            out.println("move 2");
        }
        out.flush();
    } 

    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == twoPlayerB) {
            out.println("targ 2");
        }
        if(e.getSource() == threePlayerB) {
            out.println("targ 3");
        }
        if(e.getSource() == fourPlayerB) {
            out.println("targ 4");
        }
    }
}