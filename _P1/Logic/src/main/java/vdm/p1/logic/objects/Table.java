package vdm.p1.logic.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import vdm.p1.engine.Color;
import vdm.p1.engine.IFont;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;

/**
 * Table with random solutions
 */
public class Table extends GameObject {
	/**
	 * An invalid ("null") time to distinguish between a stopped and a running timer.
	 */
	private static final double CHECK_NULL_TIME = -1.0;

	/**
	 * The time at which the timer starts at.
	 */
	private static final double CHECK_START_TIME = 0.0;

	/**
	 * The maximum duration at which the timer ends.
	 */
	private static final double CHECK_RESET_DURATION = 5.0;

	private final String name;
	private final Cell[][] cells;
	private final boolean[][] solutions;
	private final IFont font;
	private final int rows;
	private final int columns;
	private double elapsed = CHECK_NULL_TIME;


	private Table(IFont font, boolean[][] solutions) {
		this(font, solutions, null);
	}

	private Table(IFont font, boolean[][] solutions, String name) {
		super();
		this.font = font;
		this.rows = solutions.length;
		this.columns = solutions[0].length;
		this.solutions = solutions;
		this.name = name;

		cells = new Cell[rows][columns];
		Grid grid = new Grid(rows, FlowDirection.VERTICAL);
		for (int i = 0; i < rows; ++i) {
			Grid row = new Grid(columns, FlowDirection.HORIZONTAL);
			for (int j = 0; j < columns; ++j) {

				Cell cell = new Cell(solutions[i][j]);
				row.setElement(j, cell);
				cells[i][j] = cell;
			}

			grid.setElement(i, row);
		}

		// Calculate the percentage of the grid's space within the table:
		// — The width of the grid is always the width of the table minus 20% → 80%:
		double gridWidth = 1.0 - 0.2;
		// — The height of the grid is always the width of the grid multiplied by its ratio:
		//   Examples:
		//     • 5x5  → 80% * (5 / 5)  = 80%
		//     • 5x10 → 80% * (5 / 10) = 40%
		double gridHeight = gridWidth * (rows / (double) columns);
		// — Calculate the margin top for the horizontal and grid Padding elements, this is
		//   accomplished by calculating 100% - gridHeight.
		double scaledMarginTop = 1.0 - gridHeight;

		// The Grid's padding is calculated by doing the following operations:
		// • Top    : scaledMarginTop
		// • Right  : 0%
		// • Bottom : 0%
		// • Left   : 20%              || matches LeftHint's left padding
		addChild(new Padding(scaledMarginTop, 0, 0, 0.2)
				.addChild(grid)
				.setStrokeColor(Color.BLACK));

		// The left hint padding is calculated by doing the following operations:
		// • Top    : scaledMarginTop  || matches Grid's top padding
		// • Right  : 80%              || matches the Table's width minus 20%
		// • Bottom : 0%
		// • Left   : 0%
		Grid hintLeftGrid = new Grid(rows, FlowDirection.VERTICAL);
		setHints(hintLeftGrid, getXHints(solutions));
		addChild(new Padding(scaledMarginTop, 0.8, 0, 0)
				.addChild(hintLeftGrid)
				.setStrokeColor(Color.BLACK));

		// The top hint padding is calculated by doing the following operations:
		// • Top    : 0.8 - gridHeight || matches Grid's top padding minus 20%
		// • Right  : 0%
		// • Bottom : gridHeight       || matches Grid's top padding
		// • Left   : 20%              || matches Grid's left padding
		Grid hintTopGrid = new Grid(columns, FlowDirection.HORIZONTAL);
		setHints(hintTopGrid, getYHints(solutions));
		addChild(new Padding(0.8 - gridHeight, 0, gridHeight, 0.2)
				.addChild(hintTopGrid)
				.setStrokeColor(Color.BLACK));
	}

	public static Table fromRandom(IFont font, int rows, int columns) {
		boolean[][] solutions = new boolean[rows][columns];

		Random rng = new Random();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				boolean solution = rng.nextBoolean();
				solutions[i][j] = solution;
			}
		}

		return new Table(font, solutions);
	}

	public static Table fromFile(IFont font, String content) {

		String [] lines= content.split("\n");
		String [] line= lines[0].split(" ");

		int rows = Integer.parseInt(line[0]);
		int columns = Integer.parseInt(line[1]);
		String name = line[2];

		boolean[][] solutions = new boolean[rows][columns];
		for(int i=0;i<rows;i++){
			line=lines[i +1].split(" ");
			for (int j = 0; j < columns; ++j) {
				solutions[i][j] = line[j]=="0";
			}
		}

		return new Table(font, solutions, name);
	}


	@Override
	public void update(double delta) {
		if (elapsed != CHECK_NULL_TIME) {
			elapsed += delta;
			if (elapsed < CHECK_RESET_DURATION) return;

			elapsed = CHECK_NULL_TIME;
			performSolutionHide();
		}



		super.update(delta);
	}

	@Override
	public boolean handleInput(TouchEvent event) {
		return elapsed == CHECK_NULL_TIME && super.handleInput(event);
	}

	@Override
	public void handleParentScreenChange() {
		setWidth(getParent().getWidth());
		setHeight(getParent().getWidth());

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

	/**
	 * Gets the solutions.
	 *
	 * @return A 2D array of solutions.
	 */
	public boolean[][] getSolutions() {
		return solutions;
	}

	public boolean performSolutionShow() {
		if (elapsed != CHECK_NULL_TIME) return false;
		elapsed = CHECK_START_TIME;

		int missing = 0;
		int wrong = 0;

		for (Cell[] cells : getCells()) {
			for (Cell cell : cells) {
				if (cell.isWrong()) {
					cell.setWrong(true);
					++wrong;
				} else if (cell.isMissing()) {
					++missing;
				}
			}
		}

		if (missing == 0 && wrong == 0) {
			return true;
		}

		String text = missing > 0 ? "Faltan: " + missing : "";
		if (wrong > 0) text += (text.isEmpty() ? "" : " ") + "Incorrectos: " + wrong;

		Text headerText = (Text) new Text(text, font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);
		headerText.setColor(new Color(255, 0, 0));

		GameObject p = getParent().getChildren().get(0);
		for (GameObject child : p.getChildren()) {
			child.setEnabled(false);
		}
		p.addChild(headerText);
		headerText.handleParentScreenChange();
		return false;
	}

	public void performSolutionHide() {
		for (Cell[] cells : getCells()) {
			for (Cell cell : cells) {
				cell.setWrong(false);
			}
		}

		Vector<GameObject> children = getParent().getChildren().get(0).getChildren();
		children.removeElementAt(children.size() - 1);
		for (GameObject child : children) {
			child.setEnabled(true);
		}
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
