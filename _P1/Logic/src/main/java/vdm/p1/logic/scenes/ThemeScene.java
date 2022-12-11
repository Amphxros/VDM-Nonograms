package vdm.p1.logic.scenes;


import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CreateThemeLevel;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Text;

public final class ThemeScene extends Scene {
	public ThemeScene(IEngine engine, GameTheme theme) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		// TODO: Use this?
		// IImage image = engine.getGraphics().newImage("image/" + theme + "_theme.png");

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
				.addChild(new Text(theme.getName(), font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.TOP));

		// Load level grid:
		if (!theme.loaded()) theme.load(engine);

		String[] levels = theme.getLevels();
		Grid rows = new Grid(3, FlowDirection.VERTICAL);
		for (int i = 0, r = 0; i < levels.length; i += 3, r++) {
			String[] span = Arrays.copyOfRange(levels, i, Math.min(levels.length, i + 3));
			Grid row = new Grid(3, FlowDirection.HORIZONTAL);
			for (int j = 0; j < span.length; j++) {
				String content = getEngine().getFileManager().readFile(theme.getDataPath(span[j]));
				row.setElement(j, new Padding(0.2)
						.addChild(new CreateThemeLevel(i + j, getEngine(), font, content)
								.setHorizontalAlignment(HorizontalAlignment.CENTRE)
								.setVerticalAlignment(VerticalAlignment.MIDDLE))
				);
			}

			rows.setElement(r, row);
		}

		GameObject pad = new Padding(0.4, 0.04, 0.0, 0.04)
				.addChild(rows);

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				.addChild(pad);
		GameObject container = new Container(400, 600)
				.addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);
	}
}
