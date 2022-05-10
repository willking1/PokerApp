import javax.imageio.*;
import javax.imageio.spi.IIOServiceProvider;
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

    // game
    private Game gameboard;
    private boolean started;
    private int id;
    private int screen;
    private int target;
    private boolean spawned;
    private int positionX;
    private int positionY;
    private int gameSize;
    private CAL<Position> posList;

    // snake - temp?
    private int width;

    // network
    private String hostName;
    private int portNumber;
    private Socket serverSocket;
    private BufferedReader in;
    private PrintWriter out;

    // Graphical Components
    private int dimensionX;
    private int dimensionY;
    private JLabel titleLabel;
    private JLabel playerSelect;
    private JButton twoPlayerB;
    private JButton threePlayerB;
    private JButton fourPlayerB;
    private JLabel waitingLabel;

    public ClientScreen() throws IOException {

        dimensionX = 1000;
        dimensionY = 1000;

        spawned = false;

        id = -1;
        setLayout(null);
        setFocusable(true);
        gameSize = 104;
        gameboard = new Game(gameSize);

        started = false;
        setFocusable(true);
        addKeyListener(this);

        hostName = "localhost";
        portNumber = 96;
        serverSocket = new Socket(hostName, portNumber);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        out = new PrintWriter(serverSocket.getOutputStream(), true);

        width = 18;
        // Graphical Componenets
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*
         * Screens
         * 0 = game screen
         * 1 = menu screen
         * 2 = waiting screen
         */
        if (screen == 0) {
            titleLabel.setVisible(false);
            playerSelect.setVisible(false);
            twoPlayerB.setVisible(false);
            threePlayerB.setVisible(false);
            fourPlayerB.setVisible(false);
            waitingLabel.setVisible(false);
            if (started) {
                drawGame(g);
                drawMiniMap(g);
            }
        }
        if (screen == 1) {
            titleLabel.setVisible(true);
            playerSelect.setVisible(true);
            twoPlayerB.setVisible(true);
            threePlayerB.setVisible(true);
            fourPlayerB.setVisible(true);
            waitingLabel.setVisible(false);
        }
        if (screen == 2) {
            titleLabel.setVisible(false);
            playerSelect.setVisible(false);
            twoPlayerB.setVisible(false);
            threePlayerB.setVisible(false);
            fourPlayerB.setVisible(false);
            waitingLabel.setVisible(true);
            try {
                BufferedImage img = ImageIO.read(
                        new URL("https://media.australian.museum/media/dd/images/Some_image.width-800.139634f.jpg"));
                g.drawImage(img, 100, 120, null);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void drawGame(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, dimensionX, dimensionY);
        int x = positionY;
        int y = positionX;
        int buffer = 30;
        char[][] currState = gameboard.getArr();
        for (int i = x - buffer; i < x + buffer; i++) {
            if (i < 0 || i >= gameSize)
                continue;
            for (int j = y - buffer; j < y + buffer; j++) {
                if (j < 0 || j >= gameSize)
                    continue;

                int calcX = 500-(width/2) + (i - x) * (width);
                int calcY = 450-(width/2) + (j - y) * (width);
                //how to fix margin issues?

                if (calcX < 0 || calcX > dimensionX || calcY < 0 || calcY > dimensionY)
                    continue;

                if (currState[i][j] == '+') {
                    g.setColor(Color.lightGray);
                    g.fillRect(calcX, calcY, (width), (width));
                    g.setColor(Color.BLACK);
                }

                if (currState[i][j] == 'X') {
                    g.setColor(Color.RED);
                    g.fillRect(calcX, calcY, (width), (width));
                    g.setColor(Color.BLACK);
                }
                if (Character.isDigit(currState[i][j])) {
                    Integer.valueOf(currState[i][j]);
                    g.setColor(Color.GREEN);
                    g.fillRect(calcX, calcY, (width), (width));
                    g.setColor(Color.BLACK);
                }
                if (currState[i][j] == 'G') {
                    g.setColor(Color.GREEN);
                    g.fillRect(calcX, calcY, (width), (width));
                    g.setColor(Color.BLACK);
                }
                if (currState[i][j] == 'R') {
                    g.setColor(Color.RED);
                    g.fillRect(calcX, calcY, (width), (width));
                    g.setColor(Color.BLACK);
                }
                if (currState[i][j] == 'B') {
                    g.setColor(Color.BLUE);
                    g.fillRect(calcX, calcY, (width), (width));
                    g.setColor(Color.BLACK);
                }
                if (currState[i][j] == 'P') {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(calcX, calcY, (width), (width));
                    g.setColor(Color.BLACK);
                }
                if (currState[i][j] == '#') {
                    g.setColor(Color.BLACK);
                    g.fillRect(calcX, calcY, (width), (width));
                }
            }
        }
    }

    public void drawMiniMap(Graphics g){
        int x = 15;
        int y = 15;
        int width = (gameSize*3)/2; //current multipler is 3/2, possible change later
        int cellSize = 10;
        int buffer = 4 * -1;
        Color bg = new Color(70, 70, 70, 70);
        g.setColor(bg);
        g.fillRect(x, y, width, width);
        //posList size is only 1?
        for(int i=0; i<posList.size(); i++) {
            if(i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.red);
            }
            int calcX = buffer + y + (posList.get(i).getY()*3)/2;
            int calcY = buffer + x + (posList.get(i).getX()*3)/2;
            //line below is unnecessary?
            if(posList.get(i).getX() < 0 || posList.get(i).getX() > gameSize || posList.get(i).getY() < 0 || posList.get(i).getY() > gameSize);
            g.fillRect(calcX, calcY, cellSize, cellSize);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(dimensionX, dimensionY);
    }

    public void poll() throws IOException {
        // every 15 loops it will add a new block
        while (true) {
            // check if new board is available or dehang?
            try {
                String input = in.readLine();
                gameboard.set(input);
                if (spawned) {
                    //read in positions of all snakes
                    String[] positions = in.readLine().split("/"); //why is this size 1?
                    posList = new CAL<Position>();
                    for(int i=0; i<positions.length; i++) {
                        posList.add(new Position(Integer.parseInt(positions[i].split(" ")[0]), Integer.parseInt(positions[i].split(" ")[1])));
                    }
                    positionX = posList.get(0).getX();
                    positionY = posList.get(0).getY();
                }
                if (!started) {
                    target = Integer.valueOf(in.readLine());
                    int size = Integer.valueOf(in.readLine().substring(1, 2));
                    int startLoc = size - 1;
                    screen = 1;
                    // Checks if the current client is the first client to connect and puts it in
                    // the menu screen if it is
                    if (size == 1) {
                        repaint();
                        // waits for the client to select number of players, which will change the
                        // target number
                        while (target == -1) {
                            String temp = in.readLine();
                            String targetStr = temp.charAt(0) == 'T' ? temp.substring(1, 2) : "-1";
                            target = Integer.valueOf(targetStr);
                        }
                    }
                    screen = 2;
                    repaint();
                    // waits for all the clients to connect before calling addSnake
                    while (size < target) {
                        String sizeStr = "";
                        while (sizeStr.length() == 0 || sizeStr.charAt(0) != 'S') {
                            sizeStr = in.readLine();
                        }
                        size = Integer.valueOf(sizeStr.substring(1, 2));
                    }
                    screen = 0;
                    // Prints the init statement to the serverthread which uses startLoc to know
                    // where to spawn the snake
                    addSnake(startLoc);
                    spawned = true;
                    String idStr = in.readLine();
                    while (!Character.isDigit(idStr.charAt(0))) {
                        idStr = in.readLine();
                    }
                    id = Integer.valueOf(idStr);
                    System.out.println("Spawned :" + id);
                    started = true;
                }
            } catch (Exception e) {
                System.out.println("check");
                System.out.println(e.getMessage());
                break;
            }
            repaint();
        }
    }

    public void addSnake(int loc) {
        int x, y, dir;
        if (loc == 0) {
            x = 15;
            y = 15;
            dir = 2;
        } else if (loc == 1) {
            x = 15;
            y = 85;
            dir = 2;
        } else if (loc == 2) {
            x = 85;
            y = 15;
            dir = 1;
        } else {
            x = 85;
            y = 85;
            dir = 1;
        }
        out.println("init " + x + " " + y + " " + dir);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) { // why does this appear delayed?
        if (!started)
            return;
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
        if(keyCode == 32) {
            out.println("shot s");
        }
        out.flush();
    }

    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == twoPlayerB) {
            out.println("targ 2");
        }
        if (e.getSource() == threePlayerB) {
            out.println("targ 3");
        }
        if (e.getSource() == fourPlayerB) {
            out.println("targ 4");
        }
    }
}