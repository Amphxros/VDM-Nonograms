package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.State;
import vdm.p1.logic.objects.base.Button;

public final class Cell extends Button {
	private final Table table;
	private final boolean isSolution;
	private State current = State.EMPTY;

	public Cell(IScene scene, Table table, boolean isSolution) {
		super(scene);
		this.table = table;
		this.isSolution = isSolution;
	}

	public boolean isSolution() {
		return isSolution;
	}

	public State getState() {
		return current;
	}

	public boolean isMissing() {
		return current != State.SELECT && isSolution;
	}

	public boolean isWrong() {
		return current == State.SELECT && !isSolution;
	}

	public void setWrong(boolean wrong) {
		if (wrong) {
			current = State.WRONG;
		} else if (current == State.WRONG) {
			current = State.SELECT;
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
				graphics.setColor(new Color(200, 200, 200));
				break;
			case WRONG:
				graphics.setColor(new Color(255, 0, 0));
				break;
			case SELECT:
				graphics.setColor(new Color(0, 0, 255));
				break;
			case FLAGGED:
				graphics.setColor(new Color(0, 0, 0));
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
				current = State.SELECT;
				break;
			case SELECT:
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
