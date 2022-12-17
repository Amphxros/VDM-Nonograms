package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.logic.GameObject;

public final class TableSolution extends GameObject {
	private static final Color MARKED_COLOR = new Color(0, 0, 255);
	private final boolean[][] solutions;

	public TableSolution(boolean[][] solutions) {
		this.solutions = solutions;
	}

	@Override
	public void init() {
		int rows = solutions.length;
		int columns = solutions[0].length;

		final int cellSize = getWidth() / Math.max(rows, columns);

		for (int i = 0; i < rows; ++i) {
			int y = getY() + (i * cellSize);
			for (int j = 0; j < columns; ++j) {
				if (solutions[i][j]) {
					int x = getX() + (j * cellSize);
					addChild(new BackgroundColor(MARKED_COLOR).setPosition(x, y).setSize(cellSize, cellSize));
				}
			}
		}

		super.init();
	}
}
