package vdm.p1.logic;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public interface IGameObject {
	Vector2D getPosition();

	void setPosition(int x, int y);

	int getWidth();

	int getHeight();

	void render(IGraphics graphics);

	void update(double delta);

	boolean handleInput(TouchEvent event);
}
