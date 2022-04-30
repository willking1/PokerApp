import java.io.*;

public class Manager {
    
    private int size;

    public Manager() {

        size = 18;

        char[][] map = new char[size][size];

        try {
            BufferedReader br = new BufferedReader(new FileReader("./resources/Map1.txt"));
            String line = "";
            String buffer = " ";
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(buffer);
                for(int j=0; j<currentLine.length; j++) {
                    map[i][j] = currentLine[j].charAt(0);
                }
                i++;
            }
        } catch (IOException e) { e.printStackTrace();}

        Game game = new Game(map);

        String comp = game.compress();

        System.out.println(comp);

        game.set(comp);

        game.print();

    }

}