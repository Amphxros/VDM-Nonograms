package vdm.p1.pcengine;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import vdm.p1.engine.EventType;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public final class DesktopInput extends Input implements MouseInputListener {
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1)
            addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.PRIMARY));
        else if (mouseEvent.getButton() == MouseEvent.BUTTON3)
            addEvent(new TouchEvent(mouseEvent.getX(), mouseEvent.getY(), EventType.SECONDARY));
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
