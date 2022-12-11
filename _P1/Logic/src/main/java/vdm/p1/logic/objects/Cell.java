package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.State;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
import vdm.p1.logic.objects.base.Button;

public final class Cell extends Button {
	private static final Color EMPTY_COLOR = new Color(170, 170, 170);
	private static final Color MARKED_COLOR = new Color(0, 0, 255);
	private static final Color FLAGGED_COLOR = Color.BLACK;
	private static final Color WRONG_COLOR = new Color(255, 0, 0);
	private final Table table;
	private final boolean isSolution;
	private State current = State.EMPTY;

	public Cell(Table table, boolean isSolution) {
		super();
		this.table = table;
		this.isSolution = isSolution;
		this.addComponent(new InheritParentSize());
		this.addComponent(new InheritParentPosition());
	}

	public boolean isSolution() {
		return isSolution;
	}

	public State getState() {
		return current;
	}

	public boolean isMissing() {
		return current != State.MARKED && isSolution;
	}

	public boolean isWrong() {
		return current == State.MARKED && !isSolution;
	}

	public void setWrong(boolean wrong) {
		if (wrong) {
			current = State.WRONG;
		} else if (current == State.WRONG) {
			current = State.MARKED;
		}
	}

	@Override
	public void render(IGraphics graphics) {
		int x = getPosition().getX() + 1;
		int y = getPosition().getY() + 1;
		int w = getWidth() - 2;
		int h = getHeight() - 2;

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
			case FLAGGED:
				graphics.setColor(FLAGGED_COLOR);
				graphics.drawRectangle(x, y, w, h);
				graphics.drawLine(x, y, x + w, y + h);
				return;
		}

		graphics.fillRectangle(x, y, w, h);
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		State previous = current;
		switch (previous) {
			case EMPTY:
				current = State.MARKED;
				break;
			case MARKED:
				current = State.FLAGGED;
				break;
			case FLAGGED:
				current = State.EMPTY;
				break;
		}

		table.onCellUpdate(this, previous);
		return true;
	}

	@Override
	public boolean onSecondaryAction(TouchEvent event) {
		if (current != State.WRONG) {
			State previous = current;
			current = previous == State.FLAGGED ? State.EMPTY : State.FLAGGED;
			table.onCellUpdate(this, previous);
		}
		return true;
	}
}
