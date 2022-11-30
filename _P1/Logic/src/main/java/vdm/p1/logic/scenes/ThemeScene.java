package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CreateLevelButton;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public class ThemeScene extends Scene{
	public ThemeScene(IEngine engine, String theme) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage image = engine.getGraphics().newImage("image/glassPanel.png");

		GameObject goBackText = new Text("Volver", font)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject goBackButton = new GoToStartSceneButton(getEngine())
				.addChild(goBackText)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.TOP);

		goBackButton.setWidth(goBackText.getWidth());
		goBackButton.setHeight(goBackText.getHeight());

		GameObject header = new Padding(0, 0, 0.8, 0)
				.addChild(goBackButton);

		GameObject description = new Padding(0.3, 0, 0, 0)
				.addChild(new Text(theme, font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.TOP));


	}
}
