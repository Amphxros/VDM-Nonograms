package vdm.p1.logic.scenes;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CheckSolutionButton;
import vdm.p1.logic.objects.GoToStartSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Table;
import vdm.p1.logic.objects.Text;

public class GameScene extends Scene {

    public GameScene(IEngine engine, int rows, int columns) {
        super(engine);

        IFont font= engine.getGraphics().newFont("font/pico.ttf",20,true);
        IImage im= engine.getGraphics().newImage("image/glassPanel.png");
        Table table = new Table(font,rows, columns);


        // ISound s = engine.getAudio().createSound("Audio/Meadow Thoughts");
        // engine.getAudio().playSound(s);

        GameObject giveUpText = new Text("Rendirse",font).
                setHorizontalAlignment(HorizontalAlignment.RIGHT);
        Image image1= (Image) new Image(im).
                setHorizontalAlignment(HorizontalAlignment.LEFT).
                setVerticalAlignment(VerticalAlignment.MIDDLE);


        GameObject giveUpButton = new GoToStartSceneButton(getEngine()).
                addChild(giveUpText).
                addChild(image1).
                setHorizontalAlignment(HorizontalAlignment.LEFT).
                setVerticalAlignment(VerticalAlignment.TOP);

        giveUpButton.setWidth(2*giveUpText.getWidth());
        giveUpButton.setHeight(giveUpText.getHeight());

        Image image2= (Image) new Image(im).
        setHorizontalAlignment(HorizontalAlignment.LEFT).
        setVerticalAlignment(VerticalAlignment.MIDDLE);

        GameObject checkText = new Text("Comprobar",font);
        checkText.setHorizontalAlignment(HorizontalAlignment.RIGHT);

        GameObject checkButton = new CheckSolutionButton(table).
                addChild(checkText).
                addChild(image2).
                setHorizontalAlignment(HorizontalAlignment.RIGHT).
                setVerticalAlignment(VerticalAlignment.TOP);
        checkButton.setWidth(2*checkText.getWidth());
        checkButton.setHeight(checkText.getHeight());

        GameObject header = new Padding(0, 0, 0.8, 0).addChild(giveUpButton).addChild(checkButton);

        GameObject padding = new Padding(0.04, 0.1).addChild(header).addChild(table);
        GameObject container = new Container(400, 600).addChild(padding).setBackgroundColor(new Color(0x95, 0x75, 0xcd));
        GameObject body = new Body(engine).addChild(container);

        addGameObject(body);
    }
}
