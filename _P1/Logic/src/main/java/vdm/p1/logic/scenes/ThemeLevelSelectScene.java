package vdm.p1.logic.scenes;


import java.util.Arrays;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CreateThemeLevelButton;
import vdm.p1.logic.objects.GoToThemeSelectSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public final class ThemeLevelSelectScene extends Scene {
	public ThemeLevelSelectScene(IEngine engine, GameTheme theme) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage glass = engine.getGraphics().newImage("image/glassPanel.png");
		// TODO: Use this?
		// IImage image = engine.getGraphics().newImage("image/" + theme + "_theme.png");

		GameObject goBackButton = new GoToThemeSelectSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject goBackText = new Text("Volver", font)
				.addChild(goBackButton)
				.addComponent(new InheritParentPosition());

		GameObject header = new Padding(0, 0, 0.8, 0)
				.addChild(goBackText);

		GameObject description = new Padding(0.1, 0, 0, 0)
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
				row.setElement(j, createLevelButton(font, glass, i + j, theme, span[j]));
			}

			rows.setElement(r, row);
		}

		GameObject pad = new Container(1, 1)
				.addChild(rows)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				.addChild(pad);

		GameObject container = new Container(400, 600).addChild(padding);
		getBody().addChild(container);
	}

	private GameObject createLevelButton(IFont font, IImage glass, int index, GameTheme theme, String level) {
		GameObject image = new Image(glass)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		GameObject text = new Text(Integer.toString(index), font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject button = new CreateThemeLevelButton(getEngine(), theme, level)
				.addChild(image)
				.addChild(text)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		return new Padding(0.2).addChild(button);
	}
}
