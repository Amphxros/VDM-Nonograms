package vdm.p2.logic.objects.base;

import vdm.p1.engine.EventType;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;

public abstract class Button extends GameObject {
	public boolean handleInput(TouchEvent event) {
		if (event.getType() != EventType.PRIMARY && event.getType() != EventType.SECONDARY) {
			return false;
		}

		int x = event.getX();
		int px = getPosition().getX();
		if (x < px || x > px + getWidth()) return false;

		int y = event.getY();
		int py = getPosition().getY();
		if (y < py || y > py + getHeight()) return false;

		return onAction(event);
	}

	public boolean onAction(TouchEvent event) {
		if (event.getType() == EventType.PRIMARY) return onPrimaryAction(event);
		if (event.getType() == EventType.SECONDARY) return onSecondaryAction(event);
		return false;
	}

	public boolean onPrimaryAction(TouchEvent event) {
		return false;
	}

	public boolean onSecondaryAction(TouchEvent event) {
		return false;
	}
}
