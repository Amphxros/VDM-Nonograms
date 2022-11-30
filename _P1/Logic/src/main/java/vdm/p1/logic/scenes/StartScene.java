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

		//partida rapida
		GameObject playText = new Text("Partida Rapida", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject playButton = new GoToLevelSelectSceneButton(getEngine())
				.addChild(playText)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);


		//modo historia
		GameObject histText = new Text("Modo Historia", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);


		GameObject histButton = new GoToHistoryButton(getEngine())
				.addChild(histText)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);

		playButton.setWidth(playText.getWidth());
		playButton.setHeight(playText.getHeight());

		histButton.setWidth(playText.getWidth());
		histButton.setHeight(playText.getHeight());



		GameObject padding = new Padding(0.04, 0.1)
				.addChild(title)
				.addChild(playButton)
				.addChild(histButton);

		GameObject container = new Container(400, 600)
				.addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);
	}
}
