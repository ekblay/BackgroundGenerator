import javax.swing.*;

/**
 *
 * @Emmanuel Blay
 * @24/03/2019
 */
public class Driver
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Art Work");

        JComponent art = new ArtComponent(frame);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(art);
        frame.pack();
        frame.setSize(1500,900);
        frame.setVisible(true);

    }
}
