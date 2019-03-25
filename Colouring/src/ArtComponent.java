import java.awt.*;
import javax.swing.*;
/**
 *
 * @Emmanuel Blay
 * @24/03/2019
 */
public class ArtComponent extends JComponent
{
    private int initialHeight, initialWidth, startX, startY,
            heightLimit, widthLimit;
    private JFrame frame;
    public ArtComponent(JFrame f)
    {
        initialHeight = f.getHeight();
        initialWidth =  f.getWidth();
        startX = 0;
        startY = 0;
        heightLimit = 50;
        widthLimit = (int)(heightLimit *1.5);
        frame = f;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        initialWidth  = frame.getWidth();
        initialHeight = frame.getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(startX,startY,initialWidth, initialHeight);
        g.setColor(Color.BLACK);
        g.drawRect(startX,startY,initialWidth, initialHeight);

        split(startX,startY,initialWidth, initialHeight,g);
    }

    /**
     * Spliit function allows for breaking bisecting the component into region ina random manner while adding colours
     * @param x ~Left corner xCoordinate
     * @param y ~Left Corner yCoordinate
     * @param width ~xCoordinate of top right corner
     * @param height ~yCoordinate of bottom right corner
     * @param g Graphics component
     */
    public void split(int x, int y, int width, int height, Graphics g)
    {
        g.setColor(Color.BLACK);
        if (((width-x) > initialWidth/2) && ((height-y) > initialHeight/2))
        {
            int mWidth = width-x;
            int mHeight = height -y;
            int maxWidth = (int)(mWidth*0.67) ;
            int minWidth = (int)(mWidth*0.33) ;
            int maxHeight = (int)(mHeight*0.67);
            int minHeight = (int) (mHeight*0.33);
            int randomX = (int) (Math.random() * minWidth )+ (maxWidth-minWidth)+x; //vertical split position
            int randomY = (int) (Math.random() * minHeight) + (maxHeight-minHeight)+y; //horizontal split position

            g.drawLine(x,randomY,width,randomY); //horizontal line
            g.drawLine(randomX,y,randomX,height);//vertical line

            split(x,y,randomX,randomY,g);
            split(randomX,y,width,randomY,g);
            split(x,randomY,randomX,height,g);
            split(randomX,randomY,width,height,g);
        }

        else if ((width-x) > (initialWidth/2))
        {
            int mWidth = width-x;
            int maxWidth = (int)(mWidth*0.67) ;
            int minWidth = (int)(mWidth*0.33) ;
            int randomX = (int) (Math.random() * minWidth )+ (maxWidth-minWidth)+x; //vertical split position

            g.drawLine(randomX,y,randomX,height);

            split(x,y,randomX,height,g);
            split(randomX,y,width,height,g);

        }

        else if (((height-y) > initialHeight/2) )
        {
            int mHeight = height -y;
            int maxHeight = (int)(mHeight*0.67);
            int minHeight = (int) (mHeight*0.33);
            int randomY = (int) (Math.random() * minHeight) + (maxHeight-minHeight)+y; //horizontal split position

            g.drawLine(x,randomY,width,randomY);

            split(x,y,width,randomY,g);
            split(x,randomY,width,height,g);
        }

        else if (((height-y) > heightLimit)&& ((width-x) > widthLimit))
        {
            int r = (int)(Math.random()*heightLimit + widthLimit);
            if  (r < width)
            {
                int mWidth = width-x;
                int mHeight = height -y;
                int maxWidth = (int)(mWidth*0.67) ;
                int minWidth = (int)(mWidth*0.33) ;
                int maxHeight = (int)(mHeight*0.67);
                int minHeight = (int) (mHeight*0.33);
                int randomX = (int) (Math.random() * minWidth )+ (maxWidth-minWidth)+x; //vertical split position
                int randomY = (int) (Math.random() * minHeight) + (maxHeight-minHeight)+y; //horizontal split position

                g.drawLine(x,randomY,width,randomY); //horizontal line
                g.drawLine(randomX,y,randomX,height);//vertical line

                split(x,y,randomX,randomY,g);
                split(randomX,y,width,randomY,g);
                split(x,randomY,randomX,height,g);
                split(randomX,randomY,width,height,g);
            }
            else{applyColour(x,y,width,height,g);return;}

        }
        else if (height-y > heightLimit)
        {
            int r = (int)(Math.random()*heightLimit) + (height-y);
            if  (r < height)
            {
                int mHeight = height-y;
                int maxHeight = (int)(mHeight*0.67);
                int minHeight = (int) (mHeight*0.33);
                int randomY = (int) (Math.random() * minHeight) + (maxHeight-minHeight)+y; //horizontal split position

                g.drawLine(x,randomY,width,randomY);

                split(x,y,width,randomY,g);
                split(x,randomY,width,height,g);
            }else{applyColour(x,y,width,height,g);return;}
        }

        else if (width-x > widthLimit)
        {
            int r = (int)(Math.random()*widthLimit) + (width-x);
            if  (r < width)
            {
                int mWidth = width-x;
                int maxWidth = (int)(mWidth*0.67) ;
                int minWidth = (int)(mWidth*0.33) ;
                int randomX = (int) (Math.random() * minWidth )+ (maxWidth-minWidth)+x; //vertical split position

                g.drawLine(randomX,y,randomX,height);

                split(x,y,randomX,height,g);
                split(randomX,y,width,height,g);
            }
            else{applyColour(x,y,width,height,g);return;}
        }

        else
        {
            applyColour(x,y,width,height,g);
        }

    }

    public void applyColour(int x, int y, int width,int height,Graphics g)
    {

        g.setColor(new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1));
        g.fillRect(x,y,width-x,height-y);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width-x,height-y);
    }
}

