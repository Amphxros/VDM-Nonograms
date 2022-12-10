package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CreateThemeButton;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public final class ThemeSelectScene extends Scene {
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;

	public ThemeSelectScene(IEngine engine) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);

		Grid row0 = new Grid(3, FlowDirection.HORIZONTAL);
		row0.setElement(0, createLevelButton(font, "Bosque", "forest_theme"));
		row0.setElement(1, createLevelButton(font, "Jungla", "jungle_theme"));
		row0.setElement(2, createLevelButton(font, "Asia", "japan_theme"));
		Grid row1 = new Grid(3, FlowDirection.HORIZONTAL);
		row1.setElement(0, createLevelButton(font, "Mar", "sea_theme"));
		row1.setElement(1, createLevelButton(font, "Juegos", "game_theme"));
		row1.setElement(2, createLevelButton(font, "Urbano", "city_theme"));

		Grid rows = new Grid(2, FlowDirection.VERTICAL);
		rows.setElement(0, row0);
		rows.setElement(1, row1);

		GameObject table = new Padding(0.4, 0, 0.1, 0)
				.addChild(rows);

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

		GameObject description = new Padding(0.1, 0, 0, 0)
				.addChild(new Text("Seleccione categoria a jugar", font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.TOP));

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				.addChild(table);
		GameObject container = new Container(400, 600)
				.addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);
	}

	private GameObject createLevelButton(IFont font, String name, String file) {
		GameObject image = new Image(getEngine().getGraphics().newImage("image/" + file + ".png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);
		image.setWidth(IMAGE_WIDTH);
		image.setHeight(IMAGE_HEIGHT);

		GameObject text = new Text(name, font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);

		GameObject button = new CreateThemeButton(getEngine(), name)
				.addChild(image)
				.addChild(text);
		button.setWidth(image.getWidth());
		button.setWidth(image.getHeight());

		return new Padding(0.2)
				.addChild(button)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);
	}
}
