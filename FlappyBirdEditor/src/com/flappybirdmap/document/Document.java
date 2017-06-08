/**
 *
 */
package com.flappybirdmap.document;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author BppleMan
 */
public class Document implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = -3214938307814701994L;
    private ArrayList<Pipe> pipes;
    private transient BufferedImage pipeImage;

    /**
     *
     */
    public Document()
    {
        pipes = new ArrayList<>();
    }

    public void addPipe(Point2D p)
    {
        Pipe pipe;
        try
        {
            pipe = PipeParamConvert.ConvertToPipe(p);
            pipes.add(pipe);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void removePipe(Pipe pipe)
    {
        pipes.remove(pipe);
    }

    public ArrayList<Pipe> getPipes()
    {
        return pipes;
    }

    public void setPipes(ArrayList<Pipe> pipes)
    {
        this.pipes = pipes;
    }

    public int size()
    {
        return pipes.size();
    }

    public Pipe get(int index)
    {
        return pipes.get(index);
    }

    public void setPipeImage(BufferedImage pipeImage)
    {
        this.pipeImage = pipeImage;
    }

    public Pipe getSelectedPipeModel(Point2D p)
    {
        for (Pipe pipe : pipes)
        {
            if (pipe.contains(new Point2D.Double(p.getX(), p.getY())))
                return pipe;
        }
        return null;
    }

    public Pipe containsTo(Point2D p)
    {
        for (Pipe pipe : pipes)
        {
            if (pipe.contains(p.getX(), p.getY()))
            {
                return pipe;
            }
        }
        return null;
    }

    public void writeObject(ObjectOutputStream oos)
    {
        try
        {
            oos.writeObject(pipes);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
