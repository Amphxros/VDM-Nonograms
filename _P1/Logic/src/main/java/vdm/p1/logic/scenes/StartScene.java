package vdm.p1.logic.scenes;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.CheckSolutionButton;
import vdm.p1.logic.objects.GoToLevelSelectSceneButton;
import vdm.p1.logic.objects.Text;

public final class StartScene extends Scene {
    public StartScene(IEngine engine) {
        super(engine);
        IFont font= engine.getGraphics().newFont("font/pico.tff",20,true);
        GameObject title = new Text("Nonogramas",font).setHorizontalAlignment(HorizontalAlignment.CENTRE).setVerticalAlignment(VerticalAlignment.TOP);

        GameObject playText = new Text("Jugar",font);
        GameObject playButton = new GoToLevelSelectSceneButton(getEngine()).addChild(playText).setHorizontalAlignment(HorizontalAlignment.CENTRE).setVerticalAlignment(VerticalAlignment.MIDDLE);
        playButton.setWidth(playText.getWidth());
        playButton.setHeight(playText.getHeight());

        GameObject padding = new Padding(0.04, 0.1).addChild(title).addChild(playButton);
        GameObject container = new Container(400, 600).addChild(padding).setBackgroundColor(new Color(0x95, 0x75, 0xcd));
        GameObject body = new Body(engine).addChild(container);

        addGameObject(body);
    }
}