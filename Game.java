import java.io.*;

public class Game {

    private char[][] map;

    public Game(int sizeX, int sizeY) {

        map = new char[sizeX][sizeY];

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
        }
        catch (IOException e)
        {
        e.printStackTrace();
        }

    }

    public void printMap() {
        for(int i=0; i<map.length; i++) {
            System.out.println();
            for(int j=0; j<map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
    }

}