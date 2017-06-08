package com.bppleman.setting;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by BppleMan on 17/6/2.
 */
public class SettingView extends JPanel
{
    private JButton loginBtn;
    private JButton registBtn;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameText;
    private JTextField passwordText;
    private JButton closeBtn;
    private BufferedImage closeImage;

    public SettingView(SettingAction settingAction)
    {
        initComponent(settingAction);
        addComponent();
    }

    private void initComponent(SettingAction settingAction)
    {
        initImage();
        Font labelFont = new Font("Wawati SC", Font.PLAIN, 72);
        Font textFont = new Font("Skranji", Font.PLAIN, 72);

        usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(labelFont);

        passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(labelFont);

        usernameText = new JTextField();
        usernameText.setFont(textFont);
        usernameText.setBackground(new Color(214, 167, 112, 255));

        passwordText = new JPasswordField();
        passwordText.setFont(textFont);
        passwordText.setBackground(new Color(214, 167, 112, 255));

        loginBtn = new JButton("登  录");
        loginBtn.setFont(labelFont);
        loginBtn.setActionCommand(SettingAction.LOGIN);
        loginBtn.addActionListener(settingAction);

        registBtn = new JButton("注  册");
        registBtn.setFont(labelFont);
        registBtn.addActionListener(settingAction);
        registBtn.setActionCommand(SettingAction.REGISTER);

        closeBtn = new JButton(new ImageIcon(closeImage));
        closeBtn.setBorderPainted(false);
        closeBtn.addActionListener(settingAction);
        closeBtn.setActionCommand(SettingAction.EXIT);
    }

    private void initImage()
    {
        try
        {
            closeImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/level/exit.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addComponent()
    {
        add(usernameLabel);
        add(usernameText);
        add(passwordLabel);
        add(passwordText);
        add(loginBtn);
        add(registBtn);
        add(closeBtn);
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        layoutComponent();
    }

    private void layoutComponent()
    {
        int hgap = 50;
        int vgap = 100;
        int width = getWidth();
        int height = getHeight();
        int labelw = 300;
        int textw = 700;
        int btnw = (labelw + textw - hgap) / 2;
        int h = 100;
        int left = (width - labelw - textw - hgap) / 2;
        int top = (height - h * 3 - vgap * 2) / 2;
        usernameLabel.setBounds(left, top, labelw, h);
        left += hgap + labelw;
        usernameText.setBounds(left, top, textw, h);
        left = (width - labelw - textw - hgap) / 2;
        top += h + vgap;
        passwordLabel.setBounds(left, top, labelw, h);
        left += hgap + labelw;
        passwordText.setBounds(left, top, textw, h);
        left = (width - btnw - btnw - hgap) / 2;
        top += h + vgap;
        loginBtn.setBounds(left, top, btnw, h);
        left += hgap + btnw;
        registBtn.setBounds(left, top, btnw, h);

        left = getWidth() - closeImage.getWidth();
        top = 0;
        closeBtn.setBounds(left, top, closeImage.getWidth(), closeImage.getHeight());
    }
}
