package es.ucm.vdm.engine;

public interface IGraphics {
    public void setCanvasSize(Rect canvas, Rect dimension);
    public Rect getCanvas();
    public boolean createFont(String route, int size, int color, boolean bold );


}