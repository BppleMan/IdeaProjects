package com.bppleman.game;

import com.bppleman.module.Bird;
import com.bppleman.module.PipeParamConvert;
import com.flappybirdmap.document.Document;
import com.flappybirdmap.document.Pipe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

;

public class GameView extends JPanel
{
    private Document dataSource;
    private Bird bird;
    private BufferedImage backGround;
    private BufferedImage downPipeImage;
    private BufferedImage upPipeImage;
    private double backGroundWidth;
    private double backGroundHeight;
    private double backGroundScale;

    //    这是地图所移动的距离
    private double bgS = 0;

    public GameView(Bird bird)
    {
        backGroundScale = 0;
        this.bird = bird;
        setOpaque(false);
        setLayout(null);
        try
        {
            backGround = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/bg_day.png"));
            downPipeImage = ImageIO
                    .read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/pipe_down.png"));
            upPipeImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/pipe_up.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setDataSource(Document dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintBackGround(g);
        paintBird(g);
        paintPipe(g);
    }

    private void paintBackGround(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        backGroundHeight = getHeight();
        if (getBackGroundScale() - 1 < 0)
            setBackGroundScale(backGroundHeight / (double) backGround.getHeight());
        backGroundWidth = (double) backGround.getWidth() * backGroundScale;
        for (double i = 0; i < 2; i++)
        {
            g2d.drawImage(backGround, (int) (i * backGroundWidth - bgS % backGroundWidth), 0, (int) backGroundWidth,
                    (int) backGroundHeight, this);
        }
    }

    private void paintBird(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        bird.setLocation((getWidth() / 4.0), bird.getY());
        g2d.drawImage(bird.getBirdImage(), (int) bird.getX(), (int) bird.getY(), (int) bird.getWidth(),
                (int) bird.getHeight(), null);
    }

    private void paintPipe(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < dataSource.size(); i++)
        {
            Pipe pipe = dataSource.get(i);
            Rectangle2D rect = PipeParamConvert
                    .DeConvertFromPipe(pipe, backGroundWidth, backGroundHeight, getBackGroundScale(), getBgS());
            if (rect.getX() <= backGroundWidth && rect.getX() > -rect.getWidth())
            {
                g2d.drawImage(downPipeImage, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(),
                        (int) rect.getHeight(), this);
                Rectangle2D rect2 = PipeParamConvert.DeconvertFromeRectangle(rect, getBackGroundScale());
                g2d.drawImage(upPipeImage, (int) rect2.getX(), (int) rect2.getY(), (int) rect2.getWidth(),
                        (int) rect2.getHeight(), this);
            }
        }
    }

    public double getBackGroundScale()
    {
        return backGroundScale;
    }

    public void setBackGroundScale(double backGroundScale)
    {
        this.backGroundScale = backGroundScale;
        bird.setSize(this.backGroundScale);
    }

    public BufferedImage getBackGround()
    {
        return backGround;
    }

    public double getBgS()
    {
        return bgS;
    }

    public void setBgS(double bgS)
    {
        this.bgS = bgS;
    }

    public double getBackGroundWidth()
    {
        return backGroundWidth;
    }

    public double getBackGroundHeight()
    {
        return backGroundHeight;
    }
}
