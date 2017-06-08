package com.bppleman.module;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bird extends Rectangle2D.Double
{
    /**
     * 时间
     */
    public double t = 0;
    /**
     * 初速度
     */
    public double v = 0;
    /**
     * 末速度
     */
    public double vt = 0;
    /**
     *
     */
    public double s_down = 0;
    /**
     *
     */
    public double s_up = 0;
    private BufferedImage birdImage;

    public Bird()
    {
        try
        {
            birdImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/bird0_0.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setSize(double scale)
    {
        width = birdImage.getWidth() * scale;
        height = birdImage.getHeight() * scale;
    }

    public BufferedImage getBirdImage()
    {
        return birdImage;
    }

    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
}
