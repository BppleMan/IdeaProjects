package com.flappybirdmap.listener;

import com.flappybirdmap.event.PipeEvent;

public interface PipeListener
{
    public void didSelectedPipeChanged(PipeEvent e);

    public void didSelectedPipeMoved(PipeEvent e);

    public void cannotSelectedPipeMoveTo(PipeEvent e);

    public void notSelectedPipe();
}
