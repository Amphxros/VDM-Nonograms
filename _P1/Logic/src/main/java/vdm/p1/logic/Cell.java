package vdm.p1.logic;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public final class Cell extends GameObject {
    private static final Color EMPTY_COLOR = new Color(170, 170, 170);
    private static final Color WRONG_COLOR = new Color(200, 100, 222);
    private static final Color MARKED_COLOR = new Color(0, 0, 0);
    private static final Color CORRECT_COLOR = new Color(255, 255, 255);

    State current = State.Empty;
    State solution;

    public Cell(State st, int x, int y, int w, int h) {
        super(x, y, w, h);
        solution = st;
    }

    public boolean checkSolution() {
        if (current == solution || current == State.Correct) {
            current = State.Correct;
            return true;
        }
        else{
            current=State.Wrong;
        }

        return false;
    }

    @Override
    public void render(IGraphics graphics) {
        switch (current) {
            case Empty:
                graphics.setColor(EMPTY_COLOR);
                break;
            case Wrong:
                graphics.setColor(WRONG_COLOR);
                break;
            case Marked:
                graphics.setColor(MARKED_COLOR);
                break;
            case Correct:
                graphics.setColor(CORRECT_COLOR);
                break;
        }

        graphics.fillRectangle(getPosition().getX() - 1, getPosition().getY() - 1, mWidth_ - 2, mHeight_ - 2);
    }

    public boolean handleInput(TouchEvent event) {
        if (event.getX() >= mPosition_.getX() && event.getX() <= mPosition_.getX() + mWidth_ &&
                event.getY() >= mPosition_.getY() && event.getY() <= mPosition_.getY() + mHeight_) {
            if (current == State.Empty) {
                current = State.Marked;
                System.out.println(" touch");
                return true;
            }
        }

        return false;
    }
}
