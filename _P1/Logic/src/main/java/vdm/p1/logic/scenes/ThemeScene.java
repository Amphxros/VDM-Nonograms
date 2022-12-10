package vdm.p1.logic.scenes;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
import vdm.p1.logic.objects.CreateThemeLevel;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public class ThemeScene extends Scene {
	Map<String, Boolean> levels;
	private int numLevels;

	public ThemeScene(IEngine engine, String theme) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage image = engine.getGraphics().newImage("image/" + theme + "_theme.png");

		levels= new TreeMap<>();

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

		//grid con niveles aqui
		readDataTheme(theme);

		Grid row0 = new Grid(numLevels, FlowDirection.HORIZONTAL);
		int i=0;
		for (Map.Entry<String, Boolean> pair : levels.entrySet()) {

			String contentlevel= getEngine().getFileMngr().readFile("Assets/", "levels/forest/" + pair.getKey());
			row0.setElement(i, new Padding(0.2)
						.addChild(new CreateThemeLevel(i+1,getEngine(),font,contentlevel)
								.setHorizontalAlignment(HorizontalAlignment.CENTRE)
								.setVerticalAlignment(VerticalAlignment.MIDDLE))
				);
				i++;
			}

		GameObject pad = new Padding(0.04, 0.4)
				.addChild(row0)
				;

		System.out.println("Grid creada");
		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				.addChild(pad);
		GameObject container = new Container(400, 600)
				.addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);


	}

	/**
	 * reads the deencripted file
	 *
	 * @param theme
	 */
	private void readDataTheme(String theme) {
		String content = getEngine().getFileMngr().readFile("Assets/", "levels/forest/data");
		String[] lines = content.split("\n");
		numLevels = lines.length-1;

		for (int i = 1; i < lines.length; i++) {
			levels.put(lines[i],false);
		}
	}
}
