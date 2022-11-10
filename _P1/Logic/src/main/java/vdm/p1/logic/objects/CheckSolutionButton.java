package vdm.p1.logic.objects;

import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.objects.base.Button;

public final class CheckSolutionButton extends Button {
	private final Table table;

	public CheckSolutionButton(Table table) {
		super();
		this.table = table;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		table.performSolutionShow();
		return true;
	}
}
