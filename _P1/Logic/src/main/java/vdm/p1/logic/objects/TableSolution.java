package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IScene;
import vdm.p1.engine.Palette;
import vdm.p1.logic.GameObject;

public final class TableSolution extends GameObject {
	private final boolean[][] solutions;

	public TableSolution(IScene scene, boolean[][] solutions) {
		super(scene);
		this.solutions = solutions;
	}

	@Override
	public void init() {
		int rows = solutions.length;
		int columns = solutions[0].length;

		final int cellSize = getWidth() / Math.max(rows, columns);

		Color color = getPalette().getColor(Palette.SELECT);
		for (int i = 0; i < rows; ++i) {
			int y = getY() + (i * cellSize);
			for (int j = 0; j < columns; ++j) {
				if (solutions[i][j]) {
					int x = getX() + (j * cellSize);
					addChild(new BackgroundColor(getScene(), color).setPosition(x, y).setSize(cellSize, cellSize));
				}
			}
		}

		super.init();
	}
}
