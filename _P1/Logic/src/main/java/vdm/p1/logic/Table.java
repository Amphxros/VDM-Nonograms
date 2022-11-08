package vdm.p1.logic;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public final class Table extends GameObject {
    private static final Color TABLE_BACKGROUND_COLOR = new Color(0xDE, 0xDE, 0xFF);
    private static final Color HINT_BACKGROUND_COLOR = Color.BLACK;
    private static final Color HINT_FOREGROUND_COLOR = new Color(0xDE, 0xDE, 0xFF);

    Cell[][] mCasillas_;
    Hint[] solutions;
    int[] pistas_fila;
    int[] pistas_columna;
    int nRows_;
    int mCols_;
    boolean isChecked = false;
    int numErrors_;
    int fondow = 0;
    int fondoh = 0;

    public Table(int nRows, int mCols, int x, int y, int w, int h) {
        super(x, y, w, h);
        this.nRows_ = nRows;
        this.mCols_ = mCols;
        this.numErrors_ = 0;

        solutions = new Hint[nRows];
        for (int i = 0; i < nRows; i++)
            solutions[i] = new Hint(mCols);

        pistas_fila = new int[nRows];
        pistas_columna = new int[mCols];


        mCasillas_ = new Cell[nRows][mCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < mCols; j++) {
                if (solutions[i].getSolutionOnRow()[j]) {
                    pistas_fila[j]++;
                    pistas_columna[i]++;
                    mCasillas_[i][j] = new Cell(State.Marked, getPosition().getX() + (i + 2) * height / mCols_, getPosition().getY() + (j + 2) * height / mCols, height / mCols, height / mCols);
                } else {
                    pistas_fila[j] *= 10;
                    pistas_columna[i] *= 10;
                    mCasillas_[i][j] = new Cell(State.Empty, getPosition().getX() + (i + 2) * height / mCols_, getPosition().getY() + (j + 2) * height / mCols, height / mCols, height / mCols);
                }
            }
        }

        fondow = (nRows_ + 2) * w / mCols;
        fondoh = (mCols_ + 2) * h / mCols;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(TABLE_BACKGROUND_COLOR);
        graphics.fillRectangle(position.getX(), position.getY(), fondow, fondoh);

        for (int i = 0; i < pistas_fila.length; i++) {
            graphics.setColor(HINT_BACKGROUND_COLOR);
            graphics.fillRectangle(getPosition().getX(), getPosition().getY() + (i + 2) * height / mCols_, 2 * (height) / mCols_, (height - 20) / mCols_);
            graphics.setColor(HINT_FOREGROUND_COLOR);
            String s = Integer.toString(pistas_fila[i]).replaceAll("0+", "").replaceAll("", " ");
            graphics.drawText(s, getPosition().getX() + (height / mCols_) / 2, getPosition().getY() + (i + 3) * height / mCols_);
        }

        for (int i = 0; i < pistas_columna.length; i++) {
            graphics.setColor(HINT_BACKGROUND_COLOR);
            graphics.fillRectangle(getPosition().getX() + (i + 2) * height / mCols_, getPosition().getY(), (height - 20) / mCols_, 2 * height / mCols_);
            graphics.setColor(HINT_FOREGROUND_COLOR);
            String s = Integer.toString(pistas_columna[i]).replaceAll("0+", "").replaceAll("", " ");
            graphics.drawText(s, getPosition().getX() + (i + 2) * height / mCols_, getPosition().getY() + (height / mCols_) / 2);
        }

        for (int i = 0; i < nRows_; i++) {
            for (int j = 0; j < mCols_; j++) {
                mCasillas_[i][j].render(graphics);
            }
        }
    }

    public boolean handleInput(TouchEvent ev) {
        for (int i = 0; i < nRows_; i++) {
            for (int j = 0; j < mCols_; j++) {
                if (mCasillas_[i][j].handleInput(ev)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkSolutions() {
        if (!this.isChecked) {
            for (int i = 0; i < nRows_; i++) {
                for (int j = 0; j < mCols_; j++) {
                    if (!mCasillas_[i][j].checkSolution()) {
                        numErrors_++;
                    }
                }
            }
        }

        this.isChecked = true;
        return numErrors_ == 0;
    }

}
