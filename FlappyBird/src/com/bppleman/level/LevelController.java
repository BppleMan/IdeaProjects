package com.bppleman.level;

import com.bppleman.flappybird.layout.MyFlowLayout;
import com.bppleman.flappybird.layout.OverlappingLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BppleMan on 17/5/29.
 */
public class LevelController
{
    /*
    mainView是主要view容器
    里面还包含了levelView、navigateView
     */
    private JPanel mainView;
    private HeaderView headerView;
    private JPanel bodyView;
    private JPanel levelView;
    private JPanel navigationView;
    private FlowLayout flowLayout;
    private FlowLayout overLayout;
    private List<JButton> levelButtons;
    private int page;

    private LevelAction levelAction;
    private LevelProtocol delegate;

    public LevelController(LevelProtocol delegate)
    {
        this.delegate = delegate;
        initLevelAction();
        initMainView();
        initLevelButtons();
        page = 0;
    }

    private void initLevelAction()
    {
        levelAction = new LevelAction(this);
    }

    private void initLayout()
    {
        try
        {
            BufferedImage image = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/level/dark.png"));
            flowLayout = new MyFlowLayout(new Dimension(image.getWidth(), image.getHeight()), 5, 2);
            overLayout = new OverlappingLayout();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void initMainView()
    {
        initLayout();
        initHeaderView();
        initBodyView();
        mainView = new JPanel(new BorderLayout());
        mainView.setOpaque(false);
        mainView.add(headerView, BorderLayout.NORTH);
        mainView.add(bodyView, BorderLayout.CENTER);
    }

    private void initHeaderView()
    {
        headerView = new HeaderView();
        headerView.getSettingButton().addActionListener(levelAction);
    }

    private void initBodyView()
    {
        //        初始化关卡view
        levelView = new JPanel(flowLayout);
        levelView.setOpaque(false);

        //        初始化导航view
        navigationView = new NavigationView();

        //        将关卡view、导航view加到一个容器当中
        bodyView = new JPanel(overLayout);
        bodyView.setOpaque(false);
        bodyView.add(levelView);
        bodyView.add(navigationView);
    }

    private void initLevelButtons()
    {
        levelButtons = new ArrayList<>();
        for (int i = page * 10; i < (page + 1) * 10; i++)
        {
            try
            {
                JButton button = new JButton();
                button.setName(String.format("%02d", i + 1));
                button.setBorderPainted(false);
                BufferedImage image = ImageIO.read(ClassLoader
                        .getSystemResourceAsStream(String.format("source/image/level/%02d_light.png", i + 1)));
                button.setIcon(new ImageIcon(image));
                button.setActionCommand(LevelAction.GAME);
                button.addActionListener(levelAction);
                levelButtons.add(button);
                levelView.add(button);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void settingButtonDoClick()
    {
        delegate.shouldOpenSetting();
    }

    public void levelButtonDoClick(String name)
    {
        delegate.shouldStartGameWithLevel(name);
    }

    public JPanel getView()
    {
        return mainView;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }
}
