/**
 *
 */
package com.flappybirdmap.document;

import com.flappybirdmap.event.PipeEvent;
import com.flappybirdmap.listener.PipeListener;
import com.flappybirdmap.tool.ToolBarController;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * @author BppleMan
 */
public class DocumentAction implements MouseListener, KeyListener, MouseMotionListener
{
    private DocumentController delegate;
    private Pipe selectedPipe;
    private ArrayList<PipeListener> listenerList;
    private PipeLocationValidate validate;
    private double movePipeX;
    private double movePipeY;

    enum Direction
    {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     *
     */
    public DocumentAction(DocumentController delegate)
    {
        this.delegate = delegate;
        listenerList = new ArrayList<>();
        validate = new PipeLocationValidate(delegate.getData());
    }

    public synchronized void addPipeListener(PipeListener l)
    {
        if (l == null)
            throw new NullPointerException();
        listenerList.add(l);
    }

    public synchronized void removePipeListener(PipeListener l)
    {
        if (l == null)
            throw new NullPointerException();
        listenerList.remove(l);
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
        if (e.isMetaDown() && e.getKeyChar() == '=')
        {
            DocumentView.setSCALE(DocumentView.getSCALE() + 10);
            delegate.getView().repaint();
        }
        else if (e.isMetaDown() && e.getKeyChar() == '-')
        {
            DocumentView.setSCALE(DocumentView.getSCALE() - 10);
            delegate.getView().repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            if (selectedPipe != null)
            {
                if (e.isShiftDown())
                    movePipeWithDirectionAndS(Direction.UP, 10);
                else
                    movePipeWithDirectionAndS(Direction.UP, 1);
                delegate.setSaved(false);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            if (selectedPipe != null)
            {
                if (e.isShiftDown())
                    movePipeWithDirectionAndS(Direction.DOWN, 10);
                else
                    movePipeWithDirectionAndS(Direction.DOWN, 1);
                delegate.setSaved(false);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if (selectedPipe != null)
            {
                if (e.isShiftDown())
                    movePipeWithDirectionAndS(Direction.LEFT, 10);
                else
                    movePipeWithDirectionAndS(Direction.LEFT, 1);
                delegate.setSaved(false);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if (selectedPipe != null)
            {
                if (e.isShiftDown())
                    movePipeWithDirectionAndS(Direction.RIGHT, 10);
                else
                    movePipeWithDirectionAndS(Direction.RIGHT, 1);
                delegate.setSaved(false);
            }
        }
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
        delegate.acceptFocus();
        if (ToolBarController.BUTTONTYPE == ToolBarController.PIPE)
        {
            if (validate.canAdd(e.getPoint()))
                delegate.addPipe(e.getPoint());
        }
        else if (ToolBarController.BUTTONTYPE == ToolBarController.SELECT)
        {
            Point p = e.getPoint();
            Pipe pipe = delegate.pointContainsToPipe(p);
            if (pipe != null)
            {
                changeSelectedPipe(e.getPoint(), pipe);
                delegate.setSaved(false);
            }
        }
        else if (ToolBarController.BUTTONTYPE == ToolBarController.REMOVE)
        {
            Point p = e.getPoint();
            Pipe pipe = delegate.pointContainsToPipe(p);
            if (pipe != null)
            {
                delegate.removePipe(pipe);
                delegate.setSaved(false);
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

    /*
     * （非 Javadoc）
     *
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override public void mouseDragged(MouseEvent e)
    {
        if (ToolBarController.BUTTONTYPE == ToolBarController.SELECT)
        {
            if (selectedPipe != null)
            {
                delegate.setSaved(false);
                movePipeWithMouseEvent(e);
            }
        }
    }

    /*
     * （非 Javadoc）
     *
     * @see
     * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override public void mouseMoved(MouseEvent e)
    {

    }

    /**
     * 利用一个Pipe来替换selectedPipe
     *
     * @param p    是当前鼠标所在的点
     * @param pipe 用于替换的pipe
     */
    private void changeSelectedPipe(Point p, Pipe pipe)
    {
        selectedPipe = pipe;
        Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(selectedPipe);
        movePipeX = p.getX() - rect.getX();
        movePipeY = p.getY() - rect.getY();
        for (PipeListener pipeListener : listenerList)
            pipeListener.didSelectedPipeChanged(new PipeEvent(selectedPipe));
    }

    /**
     * 选中的Pipe移动后的事件源发生器
     */
    public void selectedPipeMovePerform()
    {
        delegate.repaintView();
        for (PipeListener pipeListener : listenerList)
        {
            PipeEvent pipeEvent = new PipeEvent(selectedPipe);
            pipeListener.didSelectedPipeMoved(pipeEvent);
        }
    }

    /**
     * 通过鼠标事件移动selectedPipe
     *
     * @param e 鼠标事件
     */
    private void movePipeWithMouseEvent(MouseEvent e)
    {
        try
        {
            Point2D newPoint = new Point2D.Double(e.getPoint().getX() - movePipeX, e.getPoint().getY() - movePipeY);
            Pipe copy = PipeParamConvert.GetCopyPipeWithPoint(selectedPipe, newPoint);
            if (validate.canMove(selectedPipe, copy))
            {
                selectedPipe.setFrame(copy);
                selectedPipe.setPercent(copy.getPercent());
                PipeParamConvert.ConvertPipeWithNewPoint(selectedPipe, newPoint);
                selectedPipeMovePerform();
            }
            else
            {
                Rectangle2D rect = PipeParamConvert.DeConvertFromPipe(selectedPipe);
                movePipeX = e.getPoint().getX() - rect.getX();
                movePipeY = e.getPoint().getY() - rect.getY();
            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    /**
     * 通过方向和距离移动selectedPipe
     *
     * @param d 方向
     * @param s 距离
     */
    private void movePipeWithDirectionAndS(Direction d, double s)
    {
        Pipe copy = (Pipe) selectedPipe.clone();
        double x = copy.getX();
        double y = copy.getY();
        double w = copy.getWidth();
        double h = copy.getHeight();
        switch (d)
        {
            case UP:
                copy.setFrame(x, y + s, w, h);
                break;
            case DOWN:
                copy.setFrame(x, y - s, w, h);
                break;
            case LEFT:
                copy.setFrame(x - s, y, w, h);
                break;
            case RIGHT:
                copy.setFrame(x + s, y, w, h);
                break;
        }
        try
        {
            PipeParamConvert.ConvertPipe(copy);
            /*
            一旦copy的移动成立
            就把copy的参数转移到selectedPipe上
             */
            if (validate.canMove(selectedPipe, copy))
            {
                selectedPipe.setFrame(copy);
                selectedPipe.setPercent(copy.getPercent());
                PipeParamConvert.ConvertPipe(selectedPipe);
                selectedPipeMovePerform();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void movePipeWithPoint(Point2D point) throws NullPointerException
    {
        if (selectedPipe == null)
            throw new NullPointerException("SelectedPipe is NULL");
        Pipe copy = (Pipe) selectedPipe.clone();
        try
        {
            copy.setFrame(point.getX(), point.getY(), copy.getWidth(), copy.getHeight());
            PipeParamConvert.ConvertPipe(copy);
            if (validate.canMove(selectedPipe, copy))
            {
                selectedPipe.setFrame(copy);
                selectedPipe.setPercent(copy.getPercent());
                delegate.repaintView();
            }
            else
            {
                for (PipeListener pipeListener : listenerList)
                {
                    PipeEvent pipeEvent = new PipeEvent(selectedPipe);
                    pipeEvent.setToPoint(point);
                    pipeListener.cannotSelectedPipeMoveTo(pipeEvent);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Pipe getSelectedPipe()
    {
        return selectedPipe;
    }
}
