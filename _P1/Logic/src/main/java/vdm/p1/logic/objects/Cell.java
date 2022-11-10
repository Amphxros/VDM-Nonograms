package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.State;
import vdm.p1.logic.objects.base.Button;

public final class Cell extends Button {
	private static final Color EMPTY_COLOR = new Color(170, 170, 170);
	private static final Color WRONG_COLOR = new Color(255, 255, 255);
	private static final Color MARKED_COLOR = new Color(0, 0, 0);
	private static final Color CORRECT_COLOR = new Color(25, 25, 255);
	private static final Color FLAGGED_COLOR = new Color(170, 30, 30);
	private final boolean isSolution;
	private State current = State.EMPTY;

	public Cell(boolean isSolution) {
		super();
		this.isSolution = isSolution;
	}

	public boolean checkSolution() {
		boolean marked = current == State.MARKED;
		current = isSolution ? State.CORRECT : State.WRONG;
		return marked;
	}

	@Override
	public void render(IGraphics graphics) {
		switch (current) {
			case EMPTY:
				graphics.setColor(EMPTY_COLOR);
				break;
			case WRONG:
				graphics.setColor(WRONG_COLOR);
				break;
			case MARKED:
				graphics.setColor(MARKED_COLOR);
				break;
			case CORRECT:
				graphics.setColor(CORRECT_COLOR);
				break;
			case FLAGGED:
				graphics.setColor(FLAGGED_COLOR);
				break;
		}

		graphics.fillRectangle(getPosition().getX() + 1, getPosition().getY() + 1, getWidth() - 2, getHeight() - 2);
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		switch (current) {
			case EMPTY:
			case FLAGGED:
				current = State.MARKED;
				break;
			case MARKED:
				current = State.EMPTY;
			case CORRECT:
			case WRONG:
				break;
		}

		return true;
	}

	@Override
	public boolean onSecondaryAction(TouchEvent event) {
		switch (current) {
			case EMPTY:
			case MARKED:
				current = State.FLAGGED;
				break;
			case FLAGGED:
				current = State.EMPTY;
				break;
			case CORRECT:
			case WRONG:
				break;
		}

		return true;
	}

	@Override
	public void handleParentScreenChange() {
		inheritParentArea();

		super.handleParentScreenChange();
	}
}
