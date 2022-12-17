package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.Table;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.scenes.WinScene;

public final class CheckSolutionButton extends Button {
	private final IEngine engine;
	private final Table table;

	public CheckSolutionButton(IEngine engine, Table table) {
		super();
		this.engine = engine;
		this.table = table;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		if (table.performSolutionShow()) {
			Logic logic = (Logic) engine.getLogic();
			logic.setScene(new WinScene(engine, table.getSolutions()));
		}

		return true;
	}
}