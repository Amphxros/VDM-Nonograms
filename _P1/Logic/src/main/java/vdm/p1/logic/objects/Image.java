package vdm.p1.logic.objects;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;

public class Image extends GameObject {
    private IImage image;

    public Image(IImage image){
        super();
        this.image=image;
    }
    public int getWidth(){
        return image.getWidth();
    }

    public int getHeight(){
        return image.getHeight();
    }

    public void render(IGraphics graphics) {
        super.render(graphics);
        graphics.drawImage(image, getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }
}
