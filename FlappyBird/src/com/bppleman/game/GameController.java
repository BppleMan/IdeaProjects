package com.bppleman.game;

import com.bppleman.flappybird.layout.OverlappingLayout;
import com.bppleman.module.Bird;
import com.flappybirdmap.document.Document;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Timer;
import java.util.TimerTask;

public class GameController
{
    private GameProtocol delegate;
    private Document document;
    private GameView mainView;
    private GameReady readyView;
    private boolean spaceKeyPressed = false;
    private GameAction gameAction;
    private JPanel containtPane;

    private Timer timer = new Timer();
    private TimerTask birdTimerTask;
    private TimerTask bgTimerTask;
    private TimerTask readTimerTask;
    private Bird bird;
    private int pipeIndex;

    private final double gravity = 100;

    enum Direction
    {
        UP, DOWN
    }

    Direction birdDirect = Direction.DOWN;

    public GameController(GameProtocol delegate)
    {
        this.delegate = delegate;
        initBird();
        initAction();
        initMainView();
    }

    private void initMainView()
    {
        mainView = new GameView(bird);
        mainView.addKeyListener(gameAction);
        readyView = new GameReady(mainView);
        containtPane = new JPanel(new OverlappingLayout());
        containtPane.add(readyView);
        containtPane.add(mainView);
    }

    public void loadDocument(String level)
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(
                    ClassLoader.getSystemResourceAsStream(String.format("source/map/%s.map", level)));
            document = (Document) ois.readObject();
            ois.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void initBird()
    {
        bird = new Bird();
    }

    public void setBirdParam(Direction d, double v, double t, double s_up)
    {
        this.birdDirect = d;
        this.bird.v = v;
        this.bird.t = t;
        this.bird.s_up = s_up;
    }

    private void initAction()
    {
        gameAction = new GameAction(this);
    }

    public void initGame(String level)
    {
        loadDocument(level);
        mainView.setDataSource(document);
        mainView.requestFocus();
        bird.setLocation((mainView.getWidth() / 4.0), (mainView.getHeight() - bird.getHeight()) / 2.0);
        bird.s_down = mainView.getHeight() / 2;
        pipeIndex = 0;
        birdTimerTask = new TimerTask()
        {
            @Override public void run()
            {
                if (readyView != null)
                {
                    containtPane.remove(readyView);
                    readyView = null;
                }
                if (CollisionValidate.isPassed(bird, document.get(pipeIndex), mainView.getBackGroundWidth(),
                        mainView.getBackGroundHeight(), mainView.getBackGroundScale(), mainView.getBgS()))
                {
                    if (pipeIndex < document.getPipes().size() - 1)
                    {
                        pipeIndex++;
                    }
                    else
                    {
                        birdTimerTask.cancel();
                        bgTimerTask.cancel();
                        delegate.didFinishedGame();
                    }
                }
                if (birdDirect == Direction.DOWN)
                {
                    bird.t += 0.05;
                    double s = gravity * Math.pow(bird.t, 2) / 2;
                    if (bird.s_down + s + bird.getHeight() >= mainView.getHeight())
                    {
                        birdTimerTask.cancel();
                        bgTimerTask.cancel();
                    }
                    bird.setLocation(bird.getX(), bird.s_down + s);
                    bird.s_up = bird.getY();
                }
                else
                {
                    bird.t += 0.05;
                    bird.vt = bird.v - gravity * bird.t;
                    double s = bird.v * bird.t - gravity * Math.pow(bird.t, 2) / 2;
                    if (bird.s_up - s <= 0)
                    {
                        birdTimerTask.cancel();
                        bgTimerTask.cancel();
                    }
                    if (bird.vt <= 0)
                    {
                        birdDirect = Direction.DOWN;
                        bird.s_down = bird.getY();
                        bird.t = 0;
                    }
                    bird.setLocation(bird.getX(), bird.s_up - s);
                }
                mainView.repaint();
            }
        };

        bgTimerTask = new TimerTask()
        {
            @Override public void run()
            {
                mainView.setBgS((mainView.getBgS() + 1));
            }
        };
    }

    public void startGame()
    {
        timer.schedule(birdTimerTask, 1000, 10);
        timer.schedule(bgTimerTask, 1000, 2);
    }

    public Bird getBird()
    {
        return bird;
    }

    public JPanel getView()
    {
        return containtPane;
    }

    public boolean isSpaceKeyPressed()
    {
        return spaceKeyPressed;
    }

    public void setSpaceKeyPressed(boolean spaceKeyPressed)
    {
        this.spaceKeyPressed = spaceKeyPressed;
    }

    public Dimension getBackGroundImageSize()
    {
        Dimension result = new Dimension((int) mainView.getBackGround().getWidth(),
                (int) mainView.getBackGround().getHeight());
        return result;
    }
}
