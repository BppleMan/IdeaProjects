package com.flappybirdmap.event;

import java.io.File;

public class FileEvent
{
    File file = null;

    public FileEvent(File file)
    {
        this.file = file;
    }

    public FileEvent(String parent, String child)
    {
        this(new File(parent, child));
    }

    public FileEvent(File parent, String child)
    {
        this(new File(parent, child));
    }

    public FileEvent(String dir)
    {
        this(new File(dir));
    }

    public File getFile()
    {
        return file;
    }

    @Override public String toString()
    {
        return file.toString();
    }
}
