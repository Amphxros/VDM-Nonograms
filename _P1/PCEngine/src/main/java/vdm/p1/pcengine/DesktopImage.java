package vdm.p1.pcengine;

import vdm.p1.engine.IImage;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class DesktopImage implements IImage {

    private Image imagen;

    // Returns imagen on the path given
    public Image DImage(String path){

        try{
            this.imagen = ImageIO.read(new File(path));}
        catch(Exception e){
            e.printStackTrace();
        }

        return this.imagen;
    }

    @Override
    public int getWidth(){
        return this.imagen.getWidth(null);
    }

    @Override
    public int getHeight(){
        return this.imagen.getHeight(null);
    }
}