package vdm.p1.logic.layout;

import vdm.p1.logic.GameObject;
import vdm.p1.logic.components.HandleParentResize;

public final class Container extends GameObject {
	public Container(int innerW, int innerH) {
		this(innerW, innerH, VerticalAlignment.MIDDLE);
	}

	public Container(int innerW, int innerH, VerticalAlignment alignment) {
		super();

		this.addComponent(new HandleParentResize(innerW, innerH));
		this.setHorizontalAlignment(HorizontalAlignment.CENTRE);
		this.setVerticalAlignment(alignment);
	}
}
