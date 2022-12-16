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

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		addGameObject(new Text("ENHORABUENA!", font).setPosition(200, 5));
		addGameObject(new TableSolution(solutions).setPosition(50, 50).setSize(new Vector2D(350, 350)));

		addGameObject(new GoToStartSceneButton(getEngine()).setPosition(100, 500).setSize(200, 50));
		addGameObject(new Text("Volver", font).setPosition(200, 500));
	}
}
