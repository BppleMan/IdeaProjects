package com.flappybirdmap.document;

import com.flappybirdmap.event.FileEvent;
import com.flappybirdmap.listener.PipeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;

public class DocumentController
{
    private Document document;
    private DocumentView documentView;
    private DocumentAction documentAction;
    private boolean isSaved = true;

    public DocumentController(PipeListener pipeListener)
    {
        this(null, pipeListener);
    }

    public DocumentController(File documentFile, PipeListener pipeListener)
    {
        if (documentFile != null)
        {
            try
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(documentFile));
                document = (Document) ois.readObject();
                System.out.println(document.getPipes());
                ois.close();
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            document = new Document();
        }
        initAction();
        if (pipeListener != null)
            addPipeListener(pipeListener);
        documentView = new DocumentView(document);
        documentView.setAction(documentAction);
        document.setPipeImage(documentView.getDownPipeImage());
    }

    private void initAction()
    {
        documentAction = new DocumentAction(this);
    }

    public Pipe pointContainsToPipe(Point p)
    {
        Pipe result = document.containsTo(p);
        return result;
    }

    public void addPipe(Point p)
    {
        document.addPipe(p);
        documentView.repaint();
        isSaved = false;
    }

    public void removePipe(Pipe pipe)
    {
        document.removePipe(pipe);
        documentView.repaint();
        isSaved = false;
    }

    public void repaintView()
    {
        documentView.repaint();
        isSaved = false;
    }

    public void saveDocument(FileEvent fileEvent)
    {
        File documentFile = fileEvent.getFile();
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(documentFile));
            oos.writeObject(document);
            oos.flush();
            oos.close();
            isSaved = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void openDocument(FileEvent fileEvent)
    {
        File documentFile = fileEvent.getFile();
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(documentFile));
            document.setPipes(((Document) ois.readObject()).getPipes());
            ois.close();
            documentView.repaint();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void acceptFocus()
    {
        documentView.requestFocus();
    }

    public void moveSelectedPipeToNewPoint(double x, double y) throws NullPointerException
    {
        Point2D newPoint = new Point2D.Double(x, y);
        documentAction.movePipeWithPoint(newPoint);
        isSaved = false;
    }

    public int documentWillClose()
    {
        if (!isSaved)
        {
            int index = JOptionPane.showConfirmDialog(null, "是否现在保存", "尚未保存", JOptionPane.YES_NO_CANCEL_OPTION);
            return index;
        }
        return -1;
    }

    public DocumentView getView()
    {
        return documentView;
    }

    public void addPipeListener(PipeListener l)
    {
        documentAction.addPipeListener(l);
    }

    public Document getData()
    {
        return document;
    }

    public boolean isSaved()
    {
        return isSaved;
    }

    public void setSaved(boolean saved)
    {
        isSaved = saved;
    }
}
