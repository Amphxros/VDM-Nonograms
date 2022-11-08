package vdm.p1.logic.objects;

import vdm.p1.logic.Cell;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.State;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;

public final class Table extends GameObject {
    public Table(int length) {
        this(length, length);
    }

    public Table(int x, int y) {
        super();
        Grid grid = new Grid(x, FlowDirection.VERTICAL);

        for (int i = 0; i < x; ++i) {
            Grid row = new Grid(y, FlowDirection.HORIZONTAL);
            for (int j = 0; j < y; ++j) {
                row.setElement(j, new Cell(State.Correct, 0, 0, 0, 0));
            }

            grid.setElement(i, row);
        }

        addChild(grid);
    }

    @Override
    public void handleParentScreenChange() {
        setWidth(getParent().getWidth());
        setHeight(getWidth());

        setPosition(getParent().getPosition().getX(), getParent().getPosition().getY() + getParent().getHeight() - getWidth());

        super.handleParentScreenChange();
    }
}
