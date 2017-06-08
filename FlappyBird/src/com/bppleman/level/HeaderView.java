package com.bppleman.level;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by BppleMan on 17/6/2.
 */
public class HeaderView extends JPanel
{
    private BufferedImage levelSelectImage;
    private BufferedImage settingImage;
    private JButton settingButton;

    public HeaderView()
    {
        try
        {
            levelSelectImage = ImageIO
                    .read(ClassLoader.getSystemResourceAsStream("source/image/level/levelselect.png"));
            settingImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/level/setting.png"));
            settingButton = new JButton(new ImageIcon(settingImage));
            settingButton.setBorderPainted(false);
            settingButton.setActionCommand(LevelAction.SETTING);
            add(settingButton);
            setLayout(null);
            setPreferredSize(new Dimension(levelSelectImage.getWidth(), levelSelectImage.getHeight()));
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
        int w = levelSelectImage.getWidth();
        int h = levelSelectImage.getHeight();
        int x = (int) ((getWidth() - w) / 2.0);
        int y = 0;
        g2d.drawImage(levelSelectImage, x, y, w, h, null);
        x = getWidth() - settingImage.getWidth();
        y = 0;
        w = settingImage.getWidth();
        h = settingImage.getHeight();
        settingButton.setBounds(x, y, w, h);
    }

    public JButton getSettingButton()
    {
        return settingButton;
    }
}
