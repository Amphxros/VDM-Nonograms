package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.logic.GameObject;

public class TableSolution extends GameObject {
	private static final Color MARKED_COLOR = new Color(0, 0, 255);
	private boolean[][] solutions;

	public TableSolution(boolean[][] solutions) {
		this.solutions = solutions;
	}

	@Override
	public void init() {
		int rows = solutions.length;
		int columns = solutions[0].length;

		// TODO: Calculate size (20) dynamically based on rows and columns
		for (int i = 0; i < rows; ++i) {
			int y = getY() + (i * 20);
			for (int j = 0; j < columns; ++j) {
				if (solutions[i][j]) {
					int x = getX() + (j * 20);
					addChild(new BackgroundColor(MARKED_COLOR).setPosition(x, y).setSize(20, 20));
				}
			}
		}

		super.init();
	}
}
