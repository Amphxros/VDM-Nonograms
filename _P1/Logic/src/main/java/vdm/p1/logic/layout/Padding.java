package vdm.p1.logic.layout;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.Vector2D;

public class Padding extends GameObject {
    private final double marginTop;
    private final double marginRight;
    private final double marginBottom;
    private final double marginLeft;

    public Padding(double margin) {
        this(margin, margin, margin, margin);
    }

    public Padding(double marginHorizontal, double marginVertical) {
        this(marginVertical, marginHorizontal, marginVertical, marginHorizontal);
    }

    public Padding(double marginTop, double marginRight, double marginBottom, double marginLeft) {
        super();
        this.marginTop = marginTop;
        this.marginRight = marginRight;
        this.marginBottom = marginBottom;
        this.marginLeft = marginLeft;
    }

    @Override
    public void handleParentScreenChange() {
        GameObject parent = getParent();
        int parentW = parent.getWidth();
        int parentH = parent.getHeight();

        int offsetL = (int) (parentW * marginLeft);
        int offsetR = (int) (parentW * marginRight);
        int offsetT = (int) (parentH * marginTop);
        int offsetB = (int) (parentH * marginBottom);

        Vector2D parentP = parent.getPosition();

        setPosition(parentP.getX() + offsetL, parentP.getY() + offsetT);
        setWidth(parentW - offsetL - offsetR);
        setHeight(parentH - offsetT - offsetB);

        super.handleParentScreenChange();
    }
}
