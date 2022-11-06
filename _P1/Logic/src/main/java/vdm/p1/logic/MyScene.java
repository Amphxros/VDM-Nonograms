package vdm.p1.logic;

import vdm.p1.engine.IGraphics;

/**
//Clase interna que representa la escena que queremos pintar
class MyScene extends Scene {
    private int x;
    private int y;
    private int radius;
    private int speed;

    private IGraphics mGraphics_;

    public MyScene(){
        this.x=100;
        this.y=0;
        this.radius = 100;
        this.speed = 150;
    }

    public void init(IGraphics renderClass){
        this.mGraphics_ = renderClass;
    }

    public void update(double deltaTime){
        int maxX = this.mGraphics_.getWidth()-this.radius;

        this.x += this.speed * deltaTime;
        this.y += 1;
        while(this.x < this.radius || this.x > maxX) {
            // Vamos a pintar fuera de la pantalla. Rectificamos.
            if (this.x < this.radius) {
                // Nos salimos por la izquierda. Rebotamos.
                this.x = this.radius;
                this.speed *= -1;
            } else if (this.x > maxX) {
                // Nos salimos por la derecha. Rebotamos
                this.x = 2 * maxX - this.x;
                this.speed *= -1;
            }
        }
    }

    public void render(){
        mGraphics_.renderCircle(this.x, this.y, this.radius);
    }
}*/
