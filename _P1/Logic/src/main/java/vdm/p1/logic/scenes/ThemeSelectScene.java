package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.GameTheme;
import vdm.p1.logic.Logic;
import vdm.p1.logic.components.HandleParentResize;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
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
	private final GameManager gameManager;
	private final IImage locked;

	public ThemeSelectScene(IEngine engine) {
		super(engine);

		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		gameManager = ((Logic) engine.getLogic()).getGameManager();
		if (!gameManager.loaded()) gameManager.loadThemes(engine);

		locked = gameManager.getLastUnlockedLevel() >= 5 ? null : engine.getGraphics().newImage("image/lock.png");

		GameTheme[] themes = gameManager.getThemes();
		Grid row0 = new Grid(3, FlowDirection.HORIZONTAL);
		row0.setElement(0, createLevelButton(font, themes[0]));
		row0.setElement(1, createLevelButton(font, themes[1]));
		row0.setElement(2, createLevelButton(font, themes[2]));
		Grid row1 = new Grid(3, FlowDirection.HORIZONTAL);
		row1.setElement(0, createLevelButton(font, themes[3]));
		row1.setElement(1, createLevelButton(font, themes[4]));
		row1.setElement(2, createLevelButton(font, themes[5]));

		Grid rows = new Grid(2, FlowDirection.VERTICAL);
		rows.setElement(0, row0);
		rows.setElement(1, row1);

		GameObject table = new Padding(0.4, 0, 0.1, 0)
				.addChild(rows);

		GameObject goBackButton = new GoToStartSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject goBackText = new Text("Volver", font)
				.addChild(goBackButton)
				.addComponent(new InheritParentPosition());

		GameObject header = new Padding(0, 0, 0.8, 0)
				.addChild(goBackText);

		GameObject description = new Padding(0.1, 0, 0, 0)
				.addChild(new Text("Seleccione categoria a jugar", font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.TOP));

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				.addChild(table);

		GameObject container = new Container(400, 600).addChild(padding);
		getBody().addChild(container);
	}

	private GameObject createLevelButton(IFont font, GameTheme theme) {
		GameObject text;
		GameObject icon;

		if (gameManager.getLastUnlockedTheme() < theme.getIndex()) {
			text = new Text("BLOQUEADO", font)
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.BOTTOM);

			icon = new Image(locked)
					.addComponent(new HandleParentResize(1, 1))
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.TOP);
		} else {
			text = new Text(theme.getName(), font)
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.BOTTOM);

			GameObject image = new Image(getEngine().getGraphics().newImage(theme.getImagePath()))
					.addComponent(new InheritParentSize())
					.addComponent(new InheritParentPosition());

			icon = new CreateThemeButton(getEngine(), theme)
					.addComponent(new HandleParentResize(1, 1))
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.TOP)
					.addChild(image);
		}

		return new Padding(0.05)
				.addChild(new Padding(0, 0, 0.2, 0).addChild(icon))
				.addChild(text)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);
	}
}
