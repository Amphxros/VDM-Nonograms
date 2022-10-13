package es.ucm.vdm.pcengine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import es.ucm.vdm.engine.EventType;
import es.ucm.vdm.engine.Input;
import es.ucm.vdm.engine.TouchEvent;

public class PCInput extends Input implements MouseListener, KeyListener, MouseMotionListener {

    public PCInput() {
        mTouchEvents_ = new ArrayList<TouchEvent>();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        TouchEvent t = new TouchEvent(0, 0, EventType.KEY_DOWN);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        TouchEvent t = new TouchEvent(0, 0, EventType.KEY_DOWN);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
