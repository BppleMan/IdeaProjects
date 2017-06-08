package com.flappybirdmap.tool;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class ToolButton extends JRadioButton
{
    private String buttonType;

    public ToolButton(String buttonType)
    {
        super();
        this.buttonType = buttonType;
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (isSelected())
        {
            g2d.setColor(new Color(40, 40, 40, 255));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
        else
        {
            g2d.setColor(new Color(83, 83, 83, 255));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    @Override protected void paintBorder(Graphics g)
    {
        super.paintBorder(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(40, 40, 40, 255));
        g2d.drawRect(0, 0, getWidth(), getHeight());
        if (buttonType == ToolBarController.PIPE)
        {
            drawPipeButton(g);
        }
        else if (buttonType == ToolBarController.SELECT)
        {
            drawSelectButton(g);
        }
        else if (buttonType == ToolBarController.REMOVE)
        {
            drawRemoveButton(g);
        }
    }

    private void drawSelectButton(Graphics g)
    {
        double w = getWidth();
        double h = getHeight();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1f));
        g2d.setColor(Color.white);
        g2d.drawLine((int) w * 30 / 100, (int) h * 5 / 100, (int) w * 30 / 100, (int) h * 80 / 100);
        g2d.drawLine((int) w * 30 / 100, (int) h * 5 / 100, (int) w * 83 / 100, (int) h * 58 / 100);
        g2d.drawLine((int) w * 82 / 100, (int) h * 58 / 100, (int) w * 57 / 100, (int) h * 58 / 100);
        g2d.drawLine((int) w * 30 / 100, (int) h * 80 / 100, (int) w * 47 / 100, (int) h * 63 / 100);
        g2d.drawLine((int) w * 47 / 100, (int) h * 63 / 100, (int) w * 60 / 100, (int) h * 90 / 100);
        g2d.drawLine((int) w * 57 / 100, (int) h * 58 / 100, (int) w * 69 / 100, (int) h * 84 / 100);
        g2d.drawLine((int) w * 60 / 100, (int) h * 90 / 100, (int) w * 69 / 100, (int) h * 84 / 100);
    }

    private void drawPipeButton(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1f));
        g2d.setColor(Color.white);
        double w = getWidth();
        double h = getHeight();
        g2d.drawLine((int) w * 10 / 100, (int) h * 95 / 100, (int) w * 90 / 100, (int) h * 95 / 100);
        g2d.drawLine((int) w * 10 / 100, (int) h * 75 / 100, (int) w * 10 / 100, (int) h * 95 / 100);
        g2d.drawLine((int) w * 90 / 100, (int) h * 75 / 100, (int) w * 90 / 100, (int) h * 95 / 100);
        g2d.drawLine((int) w * 20 / 100, 0, (int) w * 20 / 100, (int) h * 65 / 100);
        g2d.drawLine((int) w * 80 / 100, 0, (int) w * 80 / 100, (int) h * 65 / 100);
        g2d.drawLine((int) w * 20 / 100, (int) h * 65 / 100, (int) w * 10 / 100, (int) h * 75 / 100);
        g2d.drawLine((int) w * 80 / 100, (int) h * 65 / 100, (int) w * 90 / 100, (int) h * 75 / 100);
    }

    private void drawRemoveButton(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(1f));
        g2d.setColor(Color.white);
        double w = getWidth();
        double h = getHeight();
        Arc2D arc2d = new Arc2D.Double(w * 41 / 100, h / 100, w * 16 / 100, h * 16 / 100, 0, 360, Arc2D.OPEN);
        g2d.draw(arc2d);
        arc2d.setArc(w * 26 / 100, h * 45 / 100, w * 10 / 100, h * 35 / 100, 0, 360, Arc2D.OPEN);
        g2d.draw(arc2d);
        arc2d.setArc(w * 44 / 100, h * 41 / 100, w * 12 / 100, h * 42 / 100, 0, 360, Arc2D.OPEN);
        g2d.draw(arc2d);
        arc2d.setArc(w * 65 / 100, h * 45 / 100, w * 10 / 100, h * 35 / 100, 0, 360, Arc2D.OPEN);
        g2d.draw(arc2d);

        g2d.drawLine((int) w * 5 / 100, (int) h * 20 / 100, (int) w * 95 / 100, (int) h * 20 / 100);
        g2d.drawLine((int) w * 10 / 100, (int) h * 35 / 100, (int) w * 90 / 100, (int) h * 35 / 100);
        g2d.drawLine((int) w * 5 / 100, (int) h * 20 / 100, (int) w * 10 / 100, (int) h * 35 / 100);
        g2d.drawLine((int) w * 95 / 100, (int) h * 20 / 100, (int) w * 90 / 100, (int) h * 35 / 100);

        g2d.drawLine((int) w * 20 / 100, (int) h * 35 / 100, (int) w * 20 / 100, (int) h * 95 / 100);
        g2d.drawLine((int) w * 80 / 100, (int) h * 35 / 100, (int) w * 80 / 100, (int) h * 95 / 100);
        g2d.drawLine((int) w * 80 / 100, (int) h * 95 / 100, (int) w * 20 / 100, (int) h * 95 / 100);
    }
}
