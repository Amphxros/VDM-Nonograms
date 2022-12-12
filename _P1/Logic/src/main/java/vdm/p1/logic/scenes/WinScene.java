package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.TableSolution;
import vdm.p1.logic.objects.Text;

public final class WinScene extends Scene {
	public WinScene(IEngine engine, boolean[][] solutions) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		GameObject header = new Text("ENHORABUENA!", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);
		GameObject table = new TableSolution(solutions)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject goToStartButton = new GoToStartSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject goToStartText = new Text("Volver", font)
				.addChild(goToStartButton)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(table)
				.addChild(goToStartText);

		GameObject container = new Container(400, 600).addChild(padding);
		getBody().addChild(container);
	}

	@Override
	public void handleClosingNotifications() {

	}

	@Override
	public void handleOpeningNotifications() {

	}
}
