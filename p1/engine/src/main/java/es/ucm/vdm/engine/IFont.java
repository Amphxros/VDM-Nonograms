package es.ucm.vdm.engine;

public interface IFont {
    public boolean createFont(String route, int size, int color, boolean bold );
    public void render();
    public void setContent(String content);
    public void setPosition(int x, int y);
    public int getSize();
    public boolean isBold();
    
}
