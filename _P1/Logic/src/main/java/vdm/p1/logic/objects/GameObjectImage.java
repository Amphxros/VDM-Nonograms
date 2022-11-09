package vdm.p1.logic.objects;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;
import vdm.p1.logic.GameObject;

public class GameObjectImage extends GameObject {
    private IImage image;
    public GameObjectImage(IImage image, int x, int y, int w, int h){
        super(x,y,w,h);
        this.image=image;

    }

    public void render(IGraphics graphics) {
        graphics.drawImage(image, getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }


    }
