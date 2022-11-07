package vdm.p1.pcengine;

import javax.swing.JFrame;

import vdm.p1.engine.Engine;

public final class DesktopEngine extends Engine {
    private final JFrame frame;

    public DesktopEngine(JFrame frame) {
        this.frame = frame;

        // setAudio(new DesktopAudio());
        setGraphics(new DesktopGraphics(frame));
        // setInput(new DesktopInput());
    }

    @Override
    public int getWidth() {
        return frame.getWidth();
    }

    @Override
    public int getHeight() {
        return frame.getHeight();
    }
}
