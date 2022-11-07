package vdm.p1.pcengine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;

public class DesktopGraphics implements IGraphics {

    public JFrame window;
    public BufferStrategy buffer;
    public Graphics2D canvas;

    public int logicWidth;
    public int logicHeight;

    public DesktopGraphics(JFrame window){

        this.window = window;
        int attempts = 10;

        while (attempts > 0) {
            try {
                this.window.createBufferStrategy(2);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            attempts--;
        }

        this.buffer = this.window.getBufferStrategy();
        this.canvas = (Graphics2D) this.buffer.getDrawGraphics();

        this.logicHeight = window.getHeight();
        this.logicWidth = window.getWidth();
    }

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
        canvas.drawString(text, x, y);
    }

    @Override
    public void fillRectangle(int x, int y, int side) {
        fillRectangle(x, y, side, side);
    }

    @Override
    public void fillRectangle(int x, int y, int width, int height) {
        canvas.fillRect(x, y, width, height);
    }

    @Override
    public void drawRectangle(int x, int y, int side) {
        drawRectangle(x, y, side, side);
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        canvas.drawRect(x, y, width, height);
    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {
        canvas.drawLine(initX, initY, endX, endY);
    }

    @Override
    public void setResolution(int width, int height) {
        window.setSize(width, height);
    }

    @Override
    public void setColor(int color) {
        canvas.setColor(new Color(color));
    }

    @Override
    public void setFont(IFont font) {

    }

    @Override
    public void present() {
        // Dispose the current canvas, replace with an updated one, and show the buffer.
        canvas.dispose();
        canvas = (Graphics2D) buffer.getDrawGraphics();
        buffer.show();
    }

    @Override
    public void clear(int color) {
        setColor(color);
        //this.canvas.setColor((DesktopColor)color.getColor());
        //this.canvas.fillRect(0, 0, this.getAncho(), this.getAlto());
        this.canvas.setPaintMode();
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
        return this.window.getWidth();
    }

    @Override
    public int getHeight() {
        return this.window.getHeight();
    }
}
