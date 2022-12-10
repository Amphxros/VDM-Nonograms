package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.GoToHistoryButton;
import vdm.p1.logic.objects.GoToLevelSelectSceneButton;
import vdm.p1.logic.objects.Text;

public final class StartScene extends Scene {
	public StartScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 48, false);

		GameObject title = new Text("Nonogramas", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);

		// Quick Match
		GameObject quickMatchText = new Text("Partida Rapida", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);
		GameObject quickMatchButton = new GoToLevelSelectSceneButton(getEngine())
				.addChild(quickMatchText)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);
		quickMatchButton.setWidth(quickMatchText.getWidth());
		quickMatchButton.setHeight(quickMatchText.getHeight());

		// Story Mode
		GameObject storyModeText = new Text("Modo Historia", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);
		GameObject storyModeButton = new GoToHistoryButton(getEngine())
				.addChild(storyModeText)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		storyModeButton.setWidth(storyModeText.getWidth());
		storyModeButton.setHeight(storyModeText.getHeight());

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(title)
				.addChild(quickMatchButton)
				.addChild(storyModeButton);

		GameObject container = new Container(400, 600)
				.addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);
	}
}
