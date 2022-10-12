package es.ucm.vdm.aengine;

import android.graphics.Canvas;
import android.graphics.Paint;

import es.ucm.vdm.engine.Graphics;
import es.ucm.vdm.engine.IFont;
import es.ucm.vdm.engine.IImage;
import es.ucm.vdm.engine.Rect;

public class AndroidGraphics extends Graphics {

    @Override
    public IImage newImage(String name) {
        return null;
    }

    @Override
    public IFont newFont(String name, int size, boolean isBold) {
        return null;
    }

    @Override
    public void drawImage(IImage image, int x, int y) {

    }

    @Override
    public void drawImage(IImage image, int x, int y, int width, int height) {

    }

    @Override
    public void drawText(String text, int x, int y) {

    }

    @Override
    public void fillRectangle(int x, int y, int side) {

    }

    @Override
    public void fillRectangle(int x, int y, int width, int height) {

    }

    @Override
    public void drawRectangle(int x, int y, int side) {

    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) {

    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {

    }

    @Override
    public void setResolution(int width, int height) {

    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public void setFont(IFont font) {

    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void translate(int x, int y) {

    }

    @Override
    public void scale(double x, double y) {

    }

    @Override
    public void save() {

    }

    @Override
    public void restore() {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
