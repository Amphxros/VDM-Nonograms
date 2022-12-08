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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class ThemeScene extends Scene{
	private int numLevels;
	Map <String, Boolean> levels;
	public ThemeScene(IEngine engine, String theme) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage image = engine.getGraphics().newImage("image/"+theme+"_theme.png");


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
		for(int i=0; i<numLevels;i++) {
			row0.setElement(i, new Padding(0.2)
					.addChild(new CreateLevelButton(getEngine(), font, 4, 4)
							.addChild(new Image(image)
									.setHorizontalAlignment(HorizontalAlignment.CENTRE)
									.setVerticalAlignment(VerticalAlignment.MIDDLE))
					)
			);
		}

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				;
		GameObject container = new Container(400, 600)
				.addChild(padding);
		GameObject body = new Body(engine).addChild(container);

		addGameObject(body);


	}

	/**
	 * reads the deencripted file
	 * @param theme
	 */
	private void readDataTheme(String theme){

		String content= getEngine().getFileMngr().readFile("Assets/","levels/forest/data");

		String[] lines= content.split("/n");
		numLevels= Integer.parseInt(lines[0]);
		for(int i=0; i<numLevels;i++){

		}

	}
}
