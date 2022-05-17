import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Sound extends JPanel implements MouseListener {

    public Sound() {
        addMouseListener(this);
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(400, 400);
    }

    public void paintComponent(Graphics g) {
        //Set a color
        Color Brown = new Color(139, 69, 19);
        g.setColor(Brown);

        //Click on brown box to play sound
        g.fillRect(50, 50, 100, 100);
    }

    public void playSound() {

        try {
            URL url = this.getClass().getClassLoader().getResource("sound/cannon.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public void mousePressed(MouseEvent e) {
        //Check if mouse pressed position is in the brown box
        if (e.getX() >= 50 && e.getX() <= 150 && e.getY() >= 50 && e.getY() <= 150) {
            this.playSound();
        }

    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}



}