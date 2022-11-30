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
import vdm.p1.logic.objects.CreateThemeButton;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public class ThemeSelectScene extends Scene{
	public ThemeSelectScene(IEngine engine) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);

		GameObject forestImage = new Image(engine.getGraphics().newImage("image/forest_theme.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject jungleImage = new Image(engine.getGraphics().newImage("image/jungle_theme.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject asiaImage = new Image(engine.getGraphics().newImage("image/japan_theme.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject seaImage = new Image(engine.getGraphics().newImage("image/sea_theme.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject gameImage = new Image(engine.getGraphics().newImage("image/game_theme.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject cityImage = new Image(engine.getGraphics().newImage("image/city_theme.png"))
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);




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
				.addChild(new Text("Seleccione categoria a jugar", font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.TOP));

		Grid row0 = new Grid(3, FlowDirection.HORIZONTAL);
		row0.setElement(0, new Padding(0.2)
				.addChild(forestImage)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.MIDDLE)

		);
		row0.setElement(1, new Padding(0.2)
				.addChild(new CreateThemeButton(getEngine(), "forest")
				.addChild(jungleImage)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.MIDDLE))


		);
		row0.setElement(2, new Padding(0.2)
				.addChild(new CreateLevelButton(getEngine(), font, 5, 10)
						.addChild(seaImage
								.setHorizontalAlignment(HorizontalAlignment.CENTRE)
								.setVerticalAlignment(VerticalAlignment.MIDDLE))
				)

		);

		Grid row1 = new Grid(3, FlowDirection.HORIZONTAL);
		row1.setElement(0, new Padding(0.2)
				.addChild(new CreateLevelButton(getEngine(), font, 8, 8)
						.addChild(asiaImage)
								.setHorizontalAlignment(HorizontalAlignment.CENTRE)
								.setVerticalAlignment(VerticalAlignment.MIDDLE))
				
		);
		row1.setElement(1, new Padding(0.2)
				.addChild(new CreateLevelButton(getEngine(), font, 10, 10)
						.addChild(gameImage)
								.setHorizontalAlignment(HorizontalAlignment.CENTRE)
								.setVerticalAlignment(VerticalAlignment.MIDDLE))


		);
		row1.setElement(2, new Padding(0.2)
				.addChild(new CreateLevelButton(getEngine(), font, 10, 15)
				.addChild(cityImage)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.MIDDLE))

		);

		Grid rows = new Grid(2, FlowDirection.VERTICAL);
		rows.setElement(0, row0);
		rows.setElement(1, row1);

		GameObject table = new Padding(0.4, 0, 0.1, 0).addChild(rows);

		GameObject padding = new Padding(0.04, 0.1).addChild(header).addChild(description).addChild(table);
		GameObject container = new Container(400, 600).addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);

	}
}
