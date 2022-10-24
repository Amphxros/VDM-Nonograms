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
        addEvent(t);
    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {
        TouchEvent t = new TouchEvent(0, 0, EventType.KEY_DOWN);
        addEvent(t);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        TouchEvent t= new TouchEvent(0,0,EventType.KEY_UP);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x=mouseEvent.getX();
        int y= mouseEvent.getY();

        TouchEvent t= new TouchEvent(x,y,EventType.CLICKED);
        addEvent(t);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(mouseEvent.getButton()==MouseEvent.BUTTON1) //left click
        {
            int x=mouseEvent.getX();
            int y= mouseEvent.getY();

            TouchEvent t= new TouchEvent(x,y,EventType.PRESSED_DOWN);
            addEvent(t);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(mouseEvent.getButton()==MouseEvent.BUTTON1) //left click
        {
            int x=mouseEvent.getX();
            int y= mouseEvent.getY();

            TouchEvent t= new TouchEvent(x,y,EventType.PRESSED_UP);
            addEvent(t);
        }
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
        int x=mouseEvent.getX();
        int y= mouseEvent.getY();

        TouchEvent t= new TouchEvent(x,y,EventType.MOVED);
        addEvent(t);
    }
}
