package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;
import vdm.p1.logic.components.InheritParentPosition;
import vdm.p1.logic.components.InheritParentSize;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.GoToLevelSelectSceneButton;
import vdm.p1.logic.objects.GoToShopScene;
import vdm.p1.logic.objects.GoToThemeSelectSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.ShareButton;
import vdm.p1.logic.objects.Text;
import vdm.p1.logic.objects.base.Button;

public final class StartScene extends Scene {
	public StartScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 48, false);
		IImage share = engine.getGraphics().newImage("image/cart.png");
		GameObject title = new Text("Nonogramas", font)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.TOP);

		// Quick Match
		GameObject quickMatchButton = new GoToLevelSelectSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject quickMatchText = new Text("Partida Rapida", font)
				.addChild(quickMatchButton)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		GameObject shopButton = new Padding(0.8, 0.4, 0.1, 0.4)
				.addChild(createButton(new GoToShopScene(getEngine()), share)
						.setVerticalAlignment(VerticalAlignment.TOP)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE));

		// Story Mode
		GameObject storyModeButton = new GoToThemeSelectSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject storyModeText = new Text("Modo Historia", font)
				.addChild(storyModeButton)
				.setHorizontalAlignment(HorizontalAlignment.CENTRE)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);

		GameObject padding = new Padding(0.04, 0.1, 0.3, 0.1)
				.addChild(title)
				.addChild(quickMatchText)
				.addChild(storyModeText);

		GameObject container = new Container(400, 600)
				.addChild(padding)
				.addChild(shopButton);
		getBody().addChild(container);
	}

	private GameObject createButton(Button button, IImage img) {
		GameObject image = new Image(img)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		button.addChild(image)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		return new Padding(0.02).addChild(button);
	}
}
