package vdm.p1.logic.scenes;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
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
import vdm.p1.logic.objects.Text;

public final class LevelSelectScene extends Scene {
    public LevelSelectScene(IEngine engine) {
        super(engine);

        GameObject goBackText = new Text("Volver");
        GameObject goBackButton = new GoToStartSceneButton(getEngine()).addChild(goBackText).setHorizontalAlignment(HorizontalAlignment.LEFT).setVerticalAlignment(VerticalAlignment.TOP);
        goBackButton.setWidth(goBackText.getWidth());
        goBackButton.setHeight(goBackText.getHeight());
        GameObject header = new Padding(0, 0, 0.8, 0).addChild(goBackButton);

        GameObject description = new Padding(0.3, 0, 0, 0).addChild(new Text("Selecciona el tamaño del puzzle").setHorizontalAlignment(HorizontalAlignment.CENTRE).setVerticalAlignment(VerticalAlignment.TOP));

        Grid row0 = new Grid(3, FlowDirection.HORIZONTAL);
        row0.setElement(0, new Padding(0.2).addChild(new CreateLevelButton(getEngine(), 4, 4)));
        row0.setElement(1, new Padding(0.2).addChild(new CreateLevelButton(getEngine(), 5, 5)));
        row0.setElement(2, new Padding(0.2).addChild(new CreateLevelButton(getEngine(), 5, 10)));

        Grid row1 = new Grid(3, FlowDirection.HORIZONTAL);
        row1.setElement(0, new Padding(0.2).addChild(new CreateLevelButton(getEngine(), 8, 8)));
        row1.setElement(1, new Padding(0.2).addChild(new CreateLevelButton(getEngine(), 10, 10)));
        row1.setElement(2, new Padding(0.2).addChild(new CreateLevelButton(getEngine(), 10, 15)));

        Grid rows = new Grid(2, FlowDirection.VERTICAL);
        rows.setElement(0, row0);
        rows.setElement(1, row1);

        GameObject table = new Padding(0.4, 0, 0.1, 0).addChild(rows);

        GameObject padding = new Padding(0.04, 0.1).addChild(header).addChild(description).addChild(table);
        GameObject container = new Container(400, 600).addChild(padding).setBackgroundColor(new Color(0x95, 0x75, 0xcd));
        GameObject body = new Body(engine).addChild(container);

        addGameObject(body);
    }
}
