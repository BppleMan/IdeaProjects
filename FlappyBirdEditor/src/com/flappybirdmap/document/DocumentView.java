package com.flappybirdmap.document;

import com.flappybirdmap.listener.PipeListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DocumentView extends JPanel implements ChangeListener
{
    private static int SCALE = 100;
    private PipeListener pipeListener;
    private BufferedImage backGround;
    private BufferedImage downPipeImage;
    private BufferedImage upPipeImage;
    private JSlider slider;
    private Document dataSource;
    private DocumentViewComponentAdapter componentAdapter;
    private int willDrawBackCount = 6;
    private int backWidth;
    private int backHeight;
    private double convasY;

    public DocumentView(Document dataSource)
    {
        this.dataSource = dataSource;
        initImage();
        initSlider();
        initAdapter();
        addComponentListener(componentAdapter);
        this.setLayout(null);
        this.add(slider);
        slider.setFocusable(false);
        PipeParamConvert.SCALE = DocumentView.SCALE;
        PipeParamConvert.setImageWidthAndHeight(backGround.getWidth(), backGround.getHeight(), downPipeImage.getWidth(),
                downPipeImage.getHeight());
    }

    protected void initSlider()
    {
        slider = new JSlider();
        slider.setMinimum(0);
        slider.setMaximum((willDrawBackCount - 4) * backGround.getWidth() * SCALE / 200);
        slider.setValue(0);
        slider.addChangeListener(this);
    }

    protected void initImage()
    {
        try
        {
            backGround = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/bg_day.png"));
            downPipeImage = ImageIO
                    .read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/pipe_down.png"));
            upPipeImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("source/image/flappybird/pipe_up.png"));
            MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(backGround, 0);
            mediaTracker.addImage(downPipeImage, 1);
            mediaTracker.addImage(upPipeImage, 2);
            mediaTracker.waitForAll();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    protected void initAdapter()
    {
        componentAdapter = new DocumentViewComponentAdapter(this);
    }

    @Override protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintBackGround(g);
        paintPipe(g);
        paintCover(g);
    }

    public void paintBackGround(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        backWidth = backGround.getWidth() * SCALE / 200;
        backHeight = backGround.getHeight() * SCALE / 200;
        setConvasY((getHeight() - backHeight) / 2.0);
        for (int i = 0; i < willDrawBackCount; i++)
        {
            g2d.drawImage(backGround, -slider.getValue() + i * (backWidth), (int) convasY, backWidth, backHeight, this);
        }
    }

    public void paintPipe(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < dataSource.size(); i++)
        {
            Pipe pipe = dataSource.get(i);
            Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(pipe);
            g2d.drawImage(downPipeImage, (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(),
                    (int) rect.getHeight(), this);
            Rectangle2D rect2 = PipeParamConvert.DeconvertFromeRectangle(rect);
            g2d.drawImage(upPipeImage, (int) rect2.getX(), (int) rect2.getY(), (int) rect2.getWidth(),
                    (int) rect2.getHeight(), this);
        }
    }

    // 绘制遮罩图层
    public void paintCover(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(40, 40, 40, 255));
        g2d.fillRect(0, 0, getWidth(), (int) convasY);
        g2d.fillRect(0, (int) convasY + backHeight, getWidth(), getHeight() - (int) (convasY + backHeight));
    }

    public int getSliderValue()
    {
        return slider.getValue();
    }

    public void setConvasY(double convasY)
    {
        this.convasY = convasY;
        PipeParamConvert.CONVASY = convasY;
    }

    public double getConvasY()
    {
        return convasY;
    }

    public PipeListener getPipeMoveListener()
    {
        return pipeListener;
    }

    public void setPipeMoveListener(PipeListener pipeListener)
    {
        this.pipeListener = pipeListener;
    }

    public void setAction(DocumentAction documentAction)
    {
        this.addMouseListener(documentAction);
        this.addKeyListener(documentAction);
        this.addMouseMotionListener(documentAction);
    }

    public void updateSliderBounds()
    {
        slider.setBounds(0, getHeight() - 30, getWidth(), 40);
    }

    public static void setSCALE(int sCALE)
    {
        PipeParamConvert.SCALE = sCALE;
        SCALE = sCALE;
    }

    public static int getSCALE()
    {
        return SCALE;
    }

    public BufferedImage getDownPipeImage()
    {
        return downPipeImage;
    }

    /*
     * （非 Javadoc）
     *
     * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.
     * ChangeEvent)
     */
    @Override public void stateChanged(ChangeEvent e)
    {
        PipeParamConvert.SLIDER = getSliderValue();
        repaint();
    }
}