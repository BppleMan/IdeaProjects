package com.flappybirdmap.attribute;

import com.flappybirdmap.document.Pipe;
import com.flappybirdmap.event.PipeEvent;
import com.flappybirdmap.listener.AttributeListener;
import com.flappybirdmap.listener.PipeListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

public class AttributeBarController implements PipeListener
{
    private JPanel attributeView;
    private JLabel xLabel;
    private JLabel yLabel;
    private JTextField xText;
    private JTextField yText;
    private Vector<Component> order;
    private AttributeAction attributeAction;
    private AttributeBarDelegate delegate;
    private ArrayList<AttributeListener> listenerList;
    private final int hgap = 10;
    private final int vgap = 10;

    private double x = 0.0;
    private double y = 0.0;

    public AttributeBarController(AttributeBarDelegate delegate)
    {
        this.delegate = delegate;
        initView();
        initAction();
        setAction();
    }

    private void initView()
    {
        attributeView = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("属性栏");
        titledBorder.setTitleColor(Color.WHITE);
        attributeView.setBorder(titledBorder);
        attributeView.setLayout(null);
        attributeView.setBackground(new Color(83, 83, 83, 255));

        int left = hgap;
        xLabel = new JLabel("X:");
        xLabel.setBounds(left, vgap, 40, 40);
        xLabel.setHorizontalAlignment(JLabel.CENTER);
        xLabel.setFont(new Font("Menlo", 0, 24));
        xLabel.setForeground(Color.WHITE);
        left += xLabel.getWidth();
        attributeView.add(xLabel);

        xText = new JTextField(String.format("%.2f", x));
        xText.setBounds(left, vgap, 120, 40);
        xText.setFont(new Font("Menlo", 0, 22));
        xText.setForeground(Color.WHITE);
        xText.setBackground(new Color(58, 58, 58, 255));
        xText.setNextFocusableComponent(yText);
        left += xText.getWidth();
        attributeView.add(xText);

        yLabel = new JLabel("Y:");
        yLabel.setBounds(left + hgap, vgap, 40, 40);
        yLabel.setHorizontalAlignment(JLabel.CENTER);
        yLabel.setFont(new Font("Menlo", 0, 24));
        yLabel.setForeground(Color.WHITE);
        left += yLabel.getWidth();
        attributeView.add(yLabel);

        yText = new JTextField(String.format("%.2f", y));
        yText.setBounds(left, vgap, 120, 40);
        yText.setFont(new Font("Menlo", 0, 22));
        yText.setForeground(Color.WHITE);
        yText.setBackground(new Color(58, 58, 58, 255));
        yText.setNextFocusableComponent(xText);
        attributeView.add(yText);

        //设置焦点循环遍历策略
        order = new Vector<>();
        order.add(xText);
        order.add(yText);
        attributeView.setFocusTraversalPolicy(new MyOwnFocusTraversalPolicy(order));
    }

    private void initAction()
    {
        attributeAction = new AttributeAction(this);
    }

    private void initListenerList()
    {
        listenerList = new ArrayList<>();
    }

    private void setAction()
    {
        // 设置所有组件的鼠标监听
        attributeView.addMouseListener(attributeAction);
        xLabel.addMouseListener(attributeAction);
        xText.addMouseListener(attributeAction);
        yLabel.addMouseListener(attributeAction);
        yText.addMouseListener(attributeAction);

        // 设置所有组件的键盘监听
        xLabel.addKeyListener(attributeAction);
        xText.addKeyListener(attributeAction);
        yLabel.addKeyListener(attributeAction);
        yText.addKeyListener(attributeAction);

        // 设置所有组件的焦点监听
        xText.addFocusListener(attributeAction);
        yText.addFocusListener(attributeAction);
    }

    public JPanel getView()
    {
        return attributeView;
    }

    /**
     * @param e
     * @return 返回值表示更新Text是否成功
     */
    public void updateText(KeyEvent e)
    {
        double s = 1;
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
            {
                if (xText.isFocusOwner())
                {
                    if (e.isShiftDown())
                        s = 10;
                    double x = Double.valueOf(xText.getText());
                    xText.setText(String.valueOf(x - s));
                }
                else if (yText.isFocusOwner())
                {
                    if (e.isShiftDown())
                        s = 10;
                    double y = Double.valueOf(yText.getText());
                    yText.setText(String.valueOf(y + s));
                }
                selectedPipeAttributeChanged();
                break;
            }
            case KeyEvent.VK_DOWN:
            {
                if (xText.isFocusOwner())
                {
                    if (e.isShiftDown())
                        s = 10;
                    double x = Double.valueOf(xText.getText());
                    xText.setText(String.valueOf(x + s));
                }
                else if (yText.isFocusOwner())
                {
                    if (e.isShiftDown())
                        s = 10;
                    double y = Double.valueOf(yText.getText());
                    yText.setText(String.valueOf(y - s));
                }
                selectedPipeAttributeChanged();
                break;
            }
            case KeyEvent.VK_ENTER:
            {
                endedEditText();
                break;
            }
        }
    }

    /**
     * 通过x、y值更新输入框
     */
    public void updateText()
    {
        xText.setText(String.format("%.2f", x));
        yText.setText(String.format("%.2f", y));
    }

    /**
     * 当点击空白区域的时候
     */
    public void viewDidPressed()
    {
        delegate.shouldRemoveFocus(this);
        selectedPipeAttributeChanged();
    }

    /**
     * 当结束输入框编辑的时候
     */
    public void endedEditText()
    {
        delegate.shouldRemoveFocus(this);
        selectedPipeAttributeChanged();
    }

    public void selectedPipeAttributeChanged()
    {
        try
        {
            x = Double.valueOf(xText.getText());
            y = Double.valueOf(yText.getText());
            delegate.shouldRepaintPipe(x, y);
        }
        catch (NullPointerException e)
        {
            x = 0.0;
            y = 0.0;
            updateText();
        }
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public JTextField getxText()
    {
        return xText;
    }

    public JTextField getyText()
    {
        return yText;
    }

    /*
     * （非 Javadoc）
     *
     * @see
     * com.flappybirdmap.listener.PipeListener#pipeDidSeleted(com.flappybirdmap.
     * event.PipeEvent)
     */
    @Override public void didSelectedPipeChanged(PipeEvent e)
    {
        Pipe pipe = e.getSource();
        x = pipe.getX();
        y = pipe.getY();
        updateText();
    }

    /*
     * （非 Javadoc）
     *
     * @see
     * com.flappybirdmap.listener.PipeListener#didSelectedPipeMoved(com.flappybirdmap.event.
     * PipeEvent)
     */
    @Override public void didSelectedPipeMoved(PipeEvent e)
    {
        Pipe pipe = e.getSource();
        x = pipe.getX();
        y = pipe.getY();
        updateText();
    }

    @Override public void cannotSelectedPipeMoveTo(PipeEvent e)
    {
        JOptionPane.showMessageDialog(null, "y值不在范围内", "出错了", JOptionPane.ERROR_MESSAGE);
        didSelectedPipeChanged(e);
    }

    @Override public void notSelectedPipe()
    {
        JOptionPane.showMessageDialog(null, "尚未选择Pipe", "出错了", JOptionPane.ERROR_MESSAGE);
    }
}
