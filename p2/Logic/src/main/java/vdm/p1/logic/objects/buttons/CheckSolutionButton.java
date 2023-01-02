package vdm.p1.logic.objects.buttons;

import vdm.p1.engine.IScene;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.Logic;
import vdm.p1.logic.objects.Table;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.scenes.WinScene;

public final class CheckSolutionButton extends Button {
	private final Table table;

	public CheckSolutionButton(IScene scene, Table table) {
		super(scene);
		this.table = table;
	}

	@Override
	public boolean onPrimaryAction(TouchEvent event) {
		if (table.performSolutionShow()) {
			Logic logic = (Logic) getEngine().getLogic();
			logic.setScene(new WinScene(getEngine(), table.getSolutions()));
		}

		return true;
	}
}
