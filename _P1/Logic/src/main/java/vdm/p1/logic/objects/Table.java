package vdm.p1.logic.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import vdm.p1.engine.Color;
import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

public final class Table extends GameObject {
	private final Cell[][] cells;
	private final IFont font;
	private final int rows;
	private final int columns;
	private boolean checked = false;

	public Table(IFont font, int rows, int columns) {
		super();
		this.font = font;
		this.rows = rows;
		this.columns = columns;

		cells = new Cell[rows][columns];
		boolean[][] solutions = new boolean[rows][columns];

		Random rng = new Random();
		Grid grid = new Grid(rows, FlowDirection.VERTICAL);
		for (int i = 0; i < rows; ++i) {
			Grid row = new Grid(columns, FlowDirection.HORIZONTAL);
			for (int j = 0; j < columns; ++j) {

				boolean solution = rng.nextBoolean();
				Cell cell = new Cell(solution);
				row.setElement(j, cell);
				cells[i][j] = cell;
				solutions[i][j] = solution;
			}

			grid.setElement(i, row);
		}
		addChild(new Padding(0.2, 0, 0, 0.2)
				.addChild(grid)
				.setStrokeColor(Color.BLACK));

		Grid hintTopGrid = new Grid(columns, FlowDirection.HORIZONTAL);
		setHints(hintTopGrid, getYHints(solutions));
		addChild(new Padding(0, 0, 0.8, 0.2)
				.addChild(hintTopGrid)
				.setStrokeColor(Color.BLACK));

		Grid hintLeftGrid = new Grid(rows, FlowDirection.VERTICAL);
		setHints(hintLeftGrid, getXHints(solutions));
		addChild(new Padding(0.2, 0.8, 0, 0)
				.addChild(hintLeftGrid)
				.setStrokeColor(Color.BLACK));
	}

	@Override
	public void handleParentScreenChange() {
		setWidth(getParent().getWidth());
		setHeight(getWidth());

		setPosition(getParent().getPosition().getX(), getParent().getPosition().getY() + getParent().getHeight() - getWidth());

		super.handleParentScreenChange();
	}

	/**
	 * Gets the cells from this {@link Table}.
	 *
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

	private void setHints(Grid grid, List<List<Integer>> lines) {
		FlowDirection hintDirection = grid.getDirection() == FlowDirection.HORIZONTAL ? FlowDirection.VERTICAL : FlowDirection.HORIZONTAL;

		for (int i = 0; i < lines.size(); ++i) {
			List<Integer> line = lines.get(i);
			int size = Math.max(4, line.size());
			Grid lineGrid = new Grid(size, hintDirection);
			for (int j = 0; j < line.size(); j++) {
				GameObject text = new Text(line.get(line.size() - j - 1).toString(), font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.MIDDLE);
				lineGrid.setElement(size - j - 1, text);
			}

			grid.setElement(i, lineGrid);
		}
	}

	private List<List<Integer>> getXHints(boolean[][] solutions) {
		// [x][y]
		// ---

		List<List<Integer>> lines = new ArrayList<>(rows);
		for (int i = 0; i < rows; ++i) {
			Vector<Integer> line = new Vector<>();
			int count = 0;
			for (int j = 0; j < columns; ++j) {
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

	private List<List<Integer>> getYHints(boolean[][] solutions) {
		// [x][y]
		//    ---

		List<List<Integer>> lines = new ArrayList<>(columns);
		for (int j = 0; j < columns; ++j) {
			Vector<Integer> line = new Vector<>();
			int count = 0;
			for (int i = 0; i < rows; ++i) {
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
}
