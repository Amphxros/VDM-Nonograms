package vdm.p1.logic.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.Logic;
import vdm.p1.logic.State;
import vdm.p1.logic.scenes.StartScene;
import vdm.p1.logic.scenes.WinScene;

public final class Table extends GameObject {
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
	private static final double CHECK_RESET_DURATION = 1.0;

	private final GameTheme theme;
	private final String level;
	private final String name;
	private final Cell[][] cells;
	private final boolean[][] solutions;
	private final IFont font;
	private final LifeManager lifeManager;
	private final int rows;
	private final int columns;
	private double elapsed = CHECK_NULL_TIME;
	private int remaining = 0;
	private boolean pendingShuffle = false;

	private Table(IScene scene, IFont font, LifeManager lifeManager, boolean[][] solutions) {
		this(scene, font, lifeManager, solutions, null, null, null);
	}

	private Table(IScene scene, IFont font, LifeManager lifeManager, boolean[][] solutions, GameTheme theme, String level, String name) {
		super(scene);
		this.font = font;
		this.lifeManager = lifeManager;
		this.rows = solutions.length;
		this.columns = solutions[0].length;
		this.solutions = solutions;
		this.theme = theme;
		this.level = level;
		this.name = name;

		cells = new Cell[rows][columns];
	}

	public static Table fromRandom(IScene scene, IFont font, LifeManager lifeManager, int rows, int columns) {
		boolean[][] solutions = new boolean[rows][columns];

		Random rng = new Random();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				solutions[i][j] = rng.nextBoolean();
			}
		}

		return new Table(scene, font, lifeManager, solutions);
	}

	public static Table fromFile(IScene scene, IFont font, LifeManager lifeManager, GameTheme theme, String level) {
		String content = lifeManager.getEngine().getFileManager().readFile(theme.getDataPath(level));
		Scanner read = new Scanner(content);
		int rows = read.nextInt();
		int columns = read.nextInt();
		String name = read.next();
		boolean[][] solutions = new boolean[rows][columns];

		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				solutions[i][j] = read.next().equals("O");
			}
		}
		return new Table(scene, font, lifeManager, solutions, theme, level, name);
	}

	public void prepareShuffle() {
		pendingShuffle = true;
	}

	@Override
	public void init() {
		elapsed = CHECK_NULL_TIME;
		remaining = 0;
		while (lifeManager.addHeart()) ;

		final int w02 = (int) (getWidth() * 0.2);
		final int w08 = getWidth() - w02;
		final int cellSize = (int) Math.min(w08 / (double) rows, w08 / (double) columns);

		final int x = getX() + w02 + 1;
		final int y = getY() + w02 + 1;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				boolean solution = solutions[i][j];
				Cell cell = (Cell) new Cell(getScene(), this, solution)
						.setPosition(x + j * cellSize, y + i * cellSize)
						.setSize(cellSize, cellSize);
				cells[i][j] = cell;
				if (solution) remaining++;

				addChild(cell);
			}
		}

		addXHints(getXHints(solutions));
		addYHints(getYHints(solutions));

		super.init();
	}

	@Override
	public void update(double delta) {
		if (pendingShuffle) {
			pendingShuffle = false;
			shuffle();
			return;
		}

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

	/**
	 * Gets the table's name.
	 *
	 * @return The name of the table.
	 */
	public String getName() {
		return name;
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

		Text headerText = (Text) new Text(getScene(), text, font).setPosition(200, 80);
		headerText.setColor(new Color(255, 0, 0));
		addChild(headerText);

		return false;
	}

	public void performSolutionHide() {
		for (Cell[] cells : getCells()) {
			for (Cell cell : cells) {
				cell.setWrong(false);
			}
		}

		Vector<GameObject> children = getChildren();
		children.removeElementAt(children.size() - 1);
	}

	public void onCellUpdate(Cell cell, State previous) {
		if (cell.getState() == State.SELECT) {
			if (cell.isSolution()) {
				remaining--;
				if (remaining > 0) return;

				IEngine engine = lifeManager.getEngine();
				Logic logic = (Logic) engine.getLogic();
				if (theme != null && level != null) {
					if (logic.getGameManager().setCompleted(theme, level)) {
						logic.getGameManager().addMoney(30);
						logic.getGameManager().save(engine);
					}
				}
				logic.setScene(new WinScene(engine, getSolutions()));
			} else {
				cell.setWrong(true);
				if (!lifeManager.removeHeart()) {
					Logic logic = (Logic) lifeManager.getEngine().getLogic();
					logic.setScene(new StartScene(lifeManager.getEngine()));
				}
			}
		} else if (previous == State.SELECT && cell.isSolution()) {
			remaining++;
		}
	}

	private void shuffle() {
		getChildren().clear();

		Random rng = new Random();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				solutions[i][j] = rng.nextBoolean();
			}
		}

		init();
	}

	private void addXHints(List<List<Integer>> lines) {
		final int bar02 = (int) (getWidth() * 0.2);
		final int bar08 = getWidth() - bar02;

		final int cellSize = bar08 / Math.max(rows, columns);
		final int letterWidth = 12;

		GameObject hints = new Empty(getScene())
				.setStrokeColor(Color.BLACK)
				.setSize(bar02, cellSize * rows)
				.setPosition(getX(), getY() + bar02);

		final int x = hints.getX() + hints.getWidth() - 4;
		final int y = hints.getY() + (cellSize / 2) + 5;
		for (int i = 0; i < lines.size(); ++i) {
			List<Integer> line = lines.get(i);
			for (int j = 0; j < line.size(); j++) {
				hints.addChild(new Text(getScene(), line.get(line.size() - j - 1).toString(), font)
						.setPosition(x - (j * letterWidth), y + (i * cellSize)));
			}
		}

		addChild(hints);
	}

	private void addYHints(List<List<Integer>> lines) {
		final int bar02 = (int) (getWidth() * 0.2);
		final int bar08 = getWidth() - bar02;

		final int cellSize = bar08 / Math.max(rows, columns);
		final int letterHeight = 12;

		GameObject hints = new Empty(getScene())
				.setStrokeColor(Color.BLACK)
				.setSize(bar08, bar02)
				.setPosition(getX() + bar02, getY());

		final int x = hints.getX() + (cellSize / 2);
		final int y = hints.getY() + hints.getHeight() - 2;
		for (int i = 0; i < lines.size(); ++i) {
			List<Integer> line = lines.get(i);
			for (int j = 0; j < line.size(); j++) {
				hints.addChild(new Text(getScene(), line.get(line.size() - j - 1).toString(), font)
						.setPosition(x + (i * cellSize), y - (j * letterHeight)));
			}
		}

		addChild(hints);
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
