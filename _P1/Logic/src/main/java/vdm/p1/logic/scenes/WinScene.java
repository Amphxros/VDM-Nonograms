package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.logic.Vector2D;
import vdm.p1.logic.objects.TableSolution;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.buttons.GoToStartSceneButton;

public final class WinScene extends Scene {
	public WinScene(IEngine engine, boolean[][] solutions) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 10, true);
		addGameObject(new Text("ENHORABUENA!", font).setPosition(200, 120));
		addGameObject(new TableSolution(solutions).setPosition(50, 150).setSize(new Vector2D(300, 300)));

		addButton(new GoToStartSceneButton(getEngine()), font, "Volver", 20, 50);
	}
}
