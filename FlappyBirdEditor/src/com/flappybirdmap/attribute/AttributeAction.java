/**
 *
 */
package com.flappybirdmap.attribute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author BppleMan
 */
public class AttributeAction implements MouseListener, KeyListener, FocusListener
{
    private AttributeBarController delegate;

    /**
     *
     */
    public AttributeAction(AttributeBarController delegate)
    {
        this.delegate = delegate;
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override public void focusGained(FocusEvent e)
    {
        JTextField text = (JTextField) e.getSource();
        text.setForeground(new Color(58, 58, 58, 255));
        text.setBackground(Color.WHITE);
        text.selectAll();
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override public void focusLost(FocusEvent e)
    {
        JTextField text = (JTextField) e.getSource();
        text.setForeground(Color.WHITE);
        text.setBackground(new Color(58, 58, 58, 255));
        delegate.setX(Double.valueOf(delegate.getxText().getText()));
        delegate.setY(Double.valueOf(delegate.getyText().getText()));
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override public void keyTyped(KeyEvent e)
    {
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override public void keyPressed(KeyEvent e)
    {
        delegate.updateText(e);
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override public void keyReleased(KeyEvent e)
    {
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override public void mouseClicked(MouseEvent e)
    {
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override public void mousePressed(MouseEvent e)
    {
        if (e.getSource() instanceof JPanel)
        {
            JPanel panel = (JPanel) e.getSource();
            if (panel == delegate.getView())
            {
                delegate.viewDidPressed();
            }
        }
    }

    /*
     * （非 Javadoc）
     *
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override public void mouseReleased(MouseEvent e)
    {
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override public void mouseEntered(MouseEvent e)
    {
    }

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override public void mouseExited(MouseEvent e)
    {
    }

}
