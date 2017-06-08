package com.bppleman.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameReady extends JPanel
{
    private BufferedImage image;
    private GameView gameView;

    public GameReady(GameView gameView)
    {
        this.gameView = gameView;
        try
        {
            image = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/text_ready.png"));
            setOpaque(false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = (int) (image.getWidth() * gameView.getBackGroundScale());
        int h = (int) (image.getHeight() * gameView.getBackGroundScale());
        int x = (int) ((getWidth() - w) / 2.0);
        int y = (int) ((getHeight() - h) / 2.0);
        g2d.drawImage(image, x, y, w, h, this);
    }

}
