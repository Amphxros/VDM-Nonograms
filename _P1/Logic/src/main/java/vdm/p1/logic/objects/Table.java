package vdm.p1.logic.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import vdm.p1.engine.Color;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.State;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

public final class Table extends GameObject {
    private final Cell[][] cells;
    private boolean checked = false;

    public Table(int length) {
        this(length, length);
    }

    public Table(int x, int y) {
        super();

        cells = new Cell[x][y];
        boolean[][] solutions = new boolean[x][y];
        Random rng = new Random();
        Grid grid = new Grid(x, FlowDirection.VERTICAL);
        for (int i = 0; i < x; ++i) {
            Grid row = new Grid(y, FlowDirection.HORIZONTAL);
            for (int j = 0; j < y; ++j) {
                boolean solution = rng.nextBoolean();
                Cell cell = new Cell(solution);
                row.setElement(j, cell);
                cells[i][j] = cell;
                solutions[i][j] = solution;
            }

            grid.setElement(i, row);
        }
        addChild(new Padding(0.2, 0, 0, 0.2).addChild(grid));

        Grid hintTopGrid = new Grid(x, FlowDirection.HORIZONTAL);
        setHints(hintTopGrid, getYHints(solutions, x, y));
        addChild(new Padding(0, 0, 0.8, 0.2).addChild(hintTopGrid).setBackgroundColor(new Color(0x7e, 0x57, 0xc2)));

        Grid hintLeftGrid = new Grid(x, FlowDirection.VERTICAL);
        setHints(hintLeftGrid, getXHints(solutions, x, y));
        addChild(new Padding(0.2, 0.8, 0, 0).addChild(hintLeftGrid).setBackgroundColor(new Color(0x7e, 0x57, 0xc2)));
    }

    /**
     * Gets the cells from this {@link Table}.
     * @return A 2D array of {@link Cell}s.
     */
    public Cell[][] getCells() {
        return cells;
    }

    public int checkSolutions() {
        if (checked) return -1;

        checked = true;
        int errors = 0;

        for (Cell[] cells : getCells()) {
            for (Cell cell : cells) {
                if (!cell.checkSolution()) {
                    ++errors;
                }
            }
        }

        return errors;
    }

    private static void setHints(Grid grid, List<List<Integer>> lines) {
        FlowDirection hintDirection = grid.getDirection() == FlowDirection.HORIZONTAL ? FlowDirection.VERTICAL : FlowDirection.HORIZONTAL;

        for (int i = 0; i < lines.size(); ++i) {
            List<Integer> line = lines.get(i);
            int size = Math.max(5, line.size());
            Grid lineGrid = new Grid(size, hintDirection);
            for (int j = 0; j < line.size(); j++) {
                GameObject text = new Text(line.get(line.size() - j - 1).toString())
                        .setHorizontalAlignment(HorizontalAlignment.CENTRE)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE);
                lineGrid.setElement(size - j - 1, text);
            }

            grid.setElement(i, lineGrid);
        }
    }

    private static List<List<Integer>> getXHints(boolean[][] solutions, int x, int y) {
        // [x][y]
        // ---

        List<List<Integer>> lines = new ArrayList<>(x);
        for (int i = 0; i < x; ++i) {
            Vector<Integer> line = new Vector<>();
            int count = 0;
            for (int j = 0; j < y; ++j) {
                if (solutions[i][j]) {
                    ++count;
                } else if (count != 0) {
                    line.add(count);
                    count = 0;
                }
            }

            if (count != 0) line.add(count);
            lines.add(line);
        }

        return lines;
    }

    private static List<List<Integer>> getYHints(boolean[][] solutions, int x, int y) {
        // [x][y]
        //    ---

        List<List<Integer>> lines = new ArrayList<>(y);
        for (int j = 0; j < y; ++j) {
            Vector<Integer> line = new Vector<>();
            int count = 0;
            for (int i = 0; i < x; ++i) {
                if (solutions[i][j]) {
                    ++count;
                } else if (count != 0) {
                    line.add(count);
                    count = 0;
                }
            }

            if (count != 0) line.add(count);
            lines.add(line);
        }

        return lines;
    }

    @Override
    public void handleParentScreenChange() {
        setWidth(getParent().getWidth());
        setHeight(getWidth());

        setPosition(getParent().getPosition().getX(), getParent().getPosition().getY() + getParent().getHeight() - getWidth());

        super.handleParentScreenChange();
    }
}
