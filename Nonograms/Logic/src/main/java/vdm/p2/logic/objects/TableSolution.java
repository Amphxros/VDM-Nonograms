package vdm.p2.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.Padding;

public class TableSolution extends GameObject {
	private static final Color MARKED_COLOR = new Color(0, 0, 255);

	public TableSolution(boolean[][] solutions) {
		int rows = solutions.length;
		int columns = solutions[0].length;

		Grid rowGrid = new Grid(rows, FlowDirection.VERTICAL);
		for (int i = 0; i < rows; i++) {
			Grid columnGrid = new Grid(columns, FlowDirection.HORIZONTAL);
			for (int j = 0; j < columns; j++) {
				if (solutions[i][j]) {
					columnGrid.setElement(j, new BackgroundColor(MARKED_COLOR));
				}
			}

			rowGrid.setElement(i, columnGrid);
		}

		if (rows != columns) {
			double halfMargin = (rows / (double) columns) / 2;
			addChild(new Padding(0, halfMargin).addChild(rowGrid));
		} else {
			addChild(rowGrid);
		}
	}

	@Override
	public void handleParentScreenChange() {
		setWidth(getParent().getWidth());
		setHeight(getParent().getWidth());

		super.handleParentScreenChange();
	}
}
