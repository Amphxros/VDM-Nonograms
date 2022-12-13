package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
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

public final class LevelSelectScene extends Scene {
	public LevelSelectScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage glass = engine.getGraphics().newImage("image/glassPanel.png");

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
				.addChild(new Text("Selecciona el tamaño del puzzle", font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.TOP));

		Grid row0 = new Grid(3, FlowDirection.HORIZONTAL);
		row0.setElement(0, createLevelButton(font, glass, 4, 4));
		row0.setElement(1, createLevelButton(font, glass, 5, 5));
		row0.setElement(2, createLevelButton(font, glass, 5, 10));

		Grid row1 = new Grid(3, FlowDirection.HORIZONTAL);
		row1.setElement(0, createLevelButton(font, glass, 8, 8));
		row1.setElement(1, createLevelButton(font, glass, 10, 10));
		row1.setElement(2, createLevelButton(font, glass, 10, 15));

		Grid rows = new Grid(2, FlowDirection.VERTICAL);
		rows.setElement(0, row0);
		rows.setElement(1, row1);

		GameObject table = new Padding(0.4, 0, 0.1, 0)
				.addChild(rows);

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				.addChild(table);

		GameObject container = new Container(400, 600).addChild(padding);
		getBody().addChild(container);
	}

	private GameObject createLevelButton(IFont font, IImage glass, int rows, int columns) {
		GameObject image = new Image(glass)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		GameObject text = new Text(rows + "x" + columns, font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject button = new CreateLevelButton(getEngine(), rows, columns)
				.addChild(image)
				.addChild(text)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		return new Padding(0.2).addChild(button);
	}
}
