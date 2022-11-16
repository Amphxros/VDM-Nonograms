package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.TableSolution;
import vdm.p1.logic.objects.Text;

public class WinScene extends Scene {
	public WinScene(IEngine engine, boolean[][] solutions) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		GameObject header = new Text("ENHORABUENA!", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);
		GameObject table = new TableSolution(solutions)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject goToStartText = new Text("Volver", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);
		GameObject goToStartButton = new GoToStartSceneButton(getEngine())
				.addChild(goToStartText)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		goToStartButton.setWidth(goToStartText.getWidth());
		goToStartButton.setHeight(goToStartText.getHeight());

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(table)
				.addChild(goToStartButton);

		GameObject container = new Container(400, 600)
				.addChild(padding);

		GameObject body = new Body(engine)
				.addChild(container);

		addGameObject(body);
	}
}
