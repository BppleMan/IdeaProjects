package com.bppleman.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by BppleMan on 17/6/2.
 */
public class GameAction implements ActionListener, KeyListener
{
    private GameController delegate;

    public GameAction(GameController delegate)
    {
        this.delegate = delegate;
    }

    @Override public void actionPerformed(ActionEvent e)
    {

    }

    @Override public void keyTyped(KeyEvent e)
    {

    }

    @Override public void keyPressed(KeyEvent e)
    {
        if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE)
        {
            if (delegate.isSpaceKeyPressed() == false)
            {
                delegate.setSpaceKeyPressed(true);
                delegate.setBirdParam(GameController.Direction.UP, 200, 0, delegate.getBird().getY());
            }
        }
    }

    @Override public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            delegate.setSpaceKeyPressed(false);
        }
    }
}
