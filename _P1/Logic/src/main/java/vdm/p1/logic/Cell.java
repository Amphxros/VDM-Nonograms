package vdm.p1.logic;

import vdm.p1.engine.Color;
import vdm.p1.engine.EventType;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public final class Cell extends GameObject {
    private static final Color EMPTY_COLOR = new Color(170, 170, 170);
    private static final Color WRONG_COLOR = new Color(255, 255, 255);
    private static final Color MARKED_COLOR = new Color(0, 0, 0);
    private static final Color CORRECT_COLOR = new Color(25, 25, 255);

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

        graphics.fillRectangle(getPosition().getX() - 1, getPosition().getY() - 1, width - 2, height - 2);
    }

    public boolean handleInput(TouchEvent event) {
        if(event.getType()== EventType.PRESSED_DOWN ||event.getType()== EventType.CLICKED ){
            if (event.getX() >= position.getX() && event.getX() <= position.getX() + width && event.getY() >= position.getY() && event.getY() <= position.getY() + height) {
                if (current == State.Empty) {
                    current = State.Marked;
                }
                else if (current==State.Marked){
                    current = State.Empty;
                }
                return true;
            }
        }

        else if(event.getType()== EventType.MOVED){
            if (event.getX() >= position.getX() && event.getX() <= position.getX() + width && event.getY() >= position.getY() && event.getY() <= position.getY() + height) {
                if (current == State.Empty) {
                    current = State.Marked;
                }
                return true;
            }
        }
        return false;
    }
}
