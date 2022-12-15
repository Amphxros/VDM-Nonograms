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
import vdm.p1.logic.objects.CreateThemeLevelButton;
import vdm.p1.logic.objects.GoToThemeSelectSceneButton;
import vdm.p1.logic.objects.Image;
import vdm.p1.logic.objects.Text;

public class ShopScene extends Scene{


	public ShopScene(IEngine engine) {
		super(engine);
		IFont font = engine.getGraphics().newFont("font/pico.ttf", 20, true);
		IImage coin= engine.getGraphics().newImage("image/coin.png");



		GameObject goBackButton = new GoToThemeSelectSceneButton(getEngine())
				.addComponent(new InheritParentSize())
				.addComponent(new InheritParentPosition());
		GameObject goBackText = new Text("Volver", font)
				.addChild(goBackButton)
				.addComponent(new InheritParentPosition());

		GameObject coins= createLevelButton(font, coin,"0");





		GameObject header = new Padding(0, 0, 0.8, 0)
				.addChild(goBackText);

		GameObject coinhead = new Padding(0.15, 0.1, 0.8, 0.75)
				.addChild(coins);

		GameObject padding = new Padding(0.04, 0.1)
				.addChild(header)
				.addChild(coinhead)
				;
		GameObject container = new Container(400, 600)
		.addChild(padding);
		getBody().addChild(container);


	}

	private GameObject createLevelButton(IFont font, IImage glass, String tittle) {


		GameObject image = new Image(glass)
				.addComponent(new InheritParentPosition())
				.addComponent(new InheritParentSize());

		GameObject text = new Text(tittle, font)
				.setHorizontalAlignment(HorizontalAlignment.RIGHT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE);

		Grid grid= new Grid(2,FlowDirection.HORIZONTAL);
		grid.setElement(0,image);
		grid.setElement(1,text);

		return new Padding(0.2).addChild(grid);


	}


}
