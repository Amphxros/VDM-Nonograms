package vdm.p1.logic.scenes;

import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameManager;
import vdm.p1.logic.GameObject;
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
import vdm.p1.logic.objects.GoToThemeSelectSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public class ShopScene extends Scene{
	private final GameManager gameManager;
	private final IImage locked;
	public ShopScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		gameManager = ((Logic) engine.getLogic()).getGameManager();


		GameObject goBackButton = new GoToThemeSelectSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject goBackText = new Text("Volver", font)
				.addChild(goBackButton)
				.addComponent(new InheritParentPosition());

		GameObject header = new Padding(0, 0, 0.8, 0)
				.addChild(goBackText);

		if(!gameManager.loadedPalettes()) gameManager.loadPalettes(engine);

		GameObject description = new Padding(0.1, 0, 0, 0)
				.addChild(new Text("Tienda", font)
						.setHorizontalAlignment(HorizontalAlignment.CENTRE)
						.setVerticalAlignment(VerticalAlignment.TOP));

		locked = gameManager.getLastUnlockedLevel() >= gameManager.getPalettes().length ? null : engine.getGraphics().newImage("image/lock.png");
		Grid row = new Grid(gameManager.getPalettes().length, FlowDirection.VERTICAL);
		row.setElement(0,  createGridElem(font,0));

		GameObject pad = new Container(1, 1)
				.addChild(row)
				.setVerticalAlignment(VerticalAlignment.BOTTOM);
		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(description)
				.addChild(pad);
		GameObject container = new Container(400, 600)
		.addChild(padding);
		getBody().addChild(container);


	}
	private GameObject createGridElem(IFont font, int elem){
		GameObject text;
		if(gameManager.getLastUnlockedPalette() > elem){
			text= new Text(gameManager.getPalette(elem).getName(),font)
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.BOTTOM);

			return new Padding(0.05)
					.addChild(new Padding(0, 0, 0.2, 0)
					.addChild(text)
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.MIDDLE));


		}
		else {
			text = new Text("BLOQUEADO", font)
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.BOTTOM);

			GameObject icon= new Image(locked)
					.addComponent(new HandleParentResize(1, 1))
					.setHorizontalAlignment(HorizontalAlignment.LEFT)
					.setVerticalAlignment(VerticalAlignment.MIDDLE);

			return new Padding(0.05)
					.addChild(new Padding(0, 0, 0.2, 0)
					.addChild(text)
					.addChild(icon)
					.setHorizontalAlignment(HorizontalAlignment.CENTRE)
					.setVerticalAlignment(VerticalAlignment.MIDDLE));


		}
	}


}
