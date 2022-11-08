package vdm.p1.logic.scenes;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CheckSolutionButton;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Table;
import vdm.p1.logic.objects.Text;

public class GameScene extends Scene {

    public GameScene(IEngine engine, int rows, int columns) {
        super(engine);

        Table table = new Table(rows, columns);

        // ISound s = engine.getAudio().createSound("Audio/Meadow Thoughts");
        // engine.getAudio().playSound(s);

        GameObject giveUpText = new Text("Rendirse");
        GameObject giveUpButton = new GoToStartSceneButton(getEngine()).addChild(giveUpText).setHorizontalAlignment(HorizontalAlignment.LEFT).setVerticalAlignment(VerticalAlignment.TOP);
        giveUpButton.setWidth(giveUpText.getWidth());
        giveUpButton.setHeight(giveUpText.getHeight());

        GameObject checkText = new Text("Comprobar");
        GameObject checkButton = new CheckSolutionButton(table).addChild(checkText).setHorizontalAlignment(HorizontalAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.TOP);
        checkButton.setWidth(checkText.getWidth());
        checkButton.setHeight(checkText.getHeight());

        GameObject header = new Padding(0, 0, 0.8, 0).addChild(giveUpButton).addChild(checkButton);

        GameObject padding = new Padding(0.04, 0.1).addChild(header).addChild(table);
        GameObject container = new Container(400, 600).addChild(padding).setBackgroundColor(new Color(0x95, 0x75, 0xcd));
        GameObject body = new Body(engine).addChild(container);

        addGameObject(body);
    }
}
