package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.ISound;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CheckSolutionButton;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Table;
import vdm.p1.logic.objects.Text;

public final class GameScene extends Scene {
	private final ISound sound;

	public GameScene(IEngine engine, int rows, int columns) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		Table table = new Table(font, rows, columns);

		sound = engine.getAudio().createSound("audio/meadow_thoughts");
		engine.getAudio().playSound(sound);

		GameObject giveUpImage = new Image(engine.getGraphics().newImage("image/grey_boxCross.png"))
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.TOP);
		GameObject giveUpText = new Text("Rendirse", font)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		GameObject giveUpButton = new GoToStartSceneButton(getEngine())
				.addChild(giveUpText)
				.addChild(giveUpImage)
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.TOP);
		giveUpImage.setWidth(giveUpText.getHeight());
		giveUpImage.setHeight(giveUpText.getHeight());
		giveUpButton.setWidth(giveUpImage.getWidth() + 5 + giveUpText.getWidth());
		giveUpButton.setHeight(giveUpText.getHeight());

		GameObject checkImage = new Image(engine.getGraphics().newImage("image/grey_boxCheckmark.png"))
				.setHorizontalAlignment(HorizontalAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);
		GameObject checkText = new Text("Comprobar", font)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		GameObject checkButton = new CheckSolutionButton(table)
				.addChild(checkText)
				.addChild(checkImage)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.TOP);
		checkImage.setWidth(checkText.getHeight());
		checkImage.setHeight(checkText.getHeight());
		checkButton.setWidth(checkImage.getWidth() + 5 + checkText.getWidth());
		checkButton.setHeight(checkText.getHeight());

		GameObject header = new Padding(0, 0, 0.8, 0).addChild(giveUpButton).addChild(checkButton);

		GameObject padding = new Padding(0.04, 0.1).addChild(header).addChild(table);
		GameObject container = new Container(400, 600).addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);
	}

	@Override
	public void dispose() {
		super.dispose();
		getEngine().getAudio().stopSound(sound);
	}
}
